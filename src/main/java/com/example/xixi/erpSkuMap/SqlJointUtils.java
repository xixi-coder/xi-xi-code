package com.example.xixi.erpSkuMap;
/**
 * @author : xi-xi
 */

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

public class SqlJointUtils {
    private static final String UNDERLINE = "_";
    private static final String SINGLE_QUOTES = "'";
    private static final String COMMA = ",";
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";
    private static final String BACKQUOTE = "`";

    /**
     * * 批量插入
     * <p>
     * *
     * <p>
     * * @param list      对象集合
     * <p>
     * * @param tableName 表名
     * <p>
     * * @param <T>      实体类类型
     * <p>
     * * @return java.lang.String
     */
    public static <T> String jointBatchInsert(List<T> list, String tableName) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        T t = list.get(0);
        String jointSqlColumnName = jointSqlColumnName(t.getClass(), tableName);
        StringBuilder sb = new StringBuilder(jointSqlColumnName);
        for (T value : list) {
            sb.append(LEFT_BRACKET);
            Class<?> aClass = value.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                try {
//                    if ("id".equals (field.getName())) {
//                          continue;
//                      }
                    field.setAccessible(true);
                    Object o = field.get(value);
                    String name = field.getType().getSimpleName();
                    switch (name) {
                        case "String":
                            String s = (String) o;
                            if (StringUtils.isEmpty(s)) {
                                sb.append((String) null).append(COMMA);
                            } else {
                                sb.append(SINGLE_QUOTES).append(s).append(SINGLE_QUOTES).append(COMMA);
                            }
                            break;
                        case "Integer":
                        case "int":
                            Integer integer = (Integer) o;
                            if (integer == null) {
                                sb.append((String) null).append(COMMA);
                            } else {
                                sb.append(integer).append(COMMA);
                            }
                            break;
                        case "Date":
                            sb.append("now(),");
                            break;
                        case "Long":
                        case "long":
                            Long l = (Long) o;
                            if (l == null) {
                                sb.append((String) null).append(COMMA);
                            } else {
                                sb.append(l).append(COMMA);
                            }
                        default:
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            sb.replace(sb.length() - 1, sb.length() - 1, RIGHT_BRACKET);
        }
        //去掉会后一个“,”
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * * 拼接sql插入字段名
     * <p>
     * *
     * <p>
     * * @param c        实体类类型
     * <p>
     * * @param tableName 表名
     * <p>
     * * @return java.lang.String
     */
    public static String jointSqlColumnName(Class c, String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ").append(BACKQUOTE).append(tableName).append(BACKQUOTE).append(LEFT_BRACKET);
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
//            if ("id".equals(name)) {
//                continue;
//            }
//            String s = fieldConvertColumn(name);
            sb.append(BACKQUOTE).append(name).append(BACKQUOTE).append(COMMA);
        }
        return sb.substring(0, sb.length() - 1) + ") values";
    }

    static String fieldConvertColumn(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
            }
            sb.append(c);
        }
        return sb.toString().toLowerCase();
    }
}
  