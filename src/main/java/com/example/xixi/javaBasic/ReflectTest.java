package com.example.xixi.javaBasic;


import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : xi-xi
 */
public class ReflectTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
//        Class<?> c = MethodClass.class;
//        Object object = c.newInstance();
//        Method[] methods = c.getMethods();
//        Method[] declaredMethods = c.getDeclaredMethods();
//        //获取methodClass类的add方法
//        Method method = c.getMethod("add", int.class, int.class);
//        //getMethods()方法获取的所有方法
//        System.out.println("getMethods获取的方法：");
//        for (Method m : methods)
//            System.out.println(m);
//        //getDeclaredMethods()方法获取的所有方法
//        System.out.println("getDeclaredMethods获取的方法：");
//        for (Method m : declaredMethods)
//            System.out.println(m);

//        testArray();
        long interval = 1L;
        String dateFormatter = "yyyy-MM-dd HH:mm:ss";
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(interval);
        System.out.println(localDateTime);
        String format = LocalDateTime.now().minusMinutes(interval).format(DateTimeFormatter.ofPattern(dateFormatter));
        System.out.println(format);
//        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern(dateFormatter)));

    }
    public static void testArray() throws ClassNotFoundException {
        Class<?> cls = Class.forName("java.lang.String");
        Object array = Array.newInstance(cls,25);
        //往数组里添加内容
        Array.set(array,0,"hello");
        Array.set(array,1,"Java");
        Array.set(array,2,"fuck");
        Array.set(array,3,"Scala");
        Array.set(array,4,"Clojure");
        //获取某一项的内容
        System.out.println(Array.get(array,3));
    }

}
