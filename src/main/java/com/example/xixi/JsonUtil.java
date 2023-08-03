package com.example.xixi;

import jodd.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Iterator;

/**
 * Json工具类 -
 *
 * @author <a href="rongkz@zjport.gov.cn">KeithRong</a>
 * @date 2020/4/16 20:40
 */
public class JsonUtil {

    public static final String STRING_KEY = "String";

    public static final String JSONOBJECT_KEY = "JSONObject";

    public static final String JSONARRAY_KEY = "JSONArray";

    /**
     * JSONObject的key转换大小写
     * @param jsonObject Json对象
     * @param transferMode true:小写, false:大写
     * @return
     */
    public static JSONObject transferJsonKey(JSONObject jsonObject, boolean transferMode) {
        JSONObject object = new JSONObject();
        Iterator iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String jsonKey = (String) iterator.next();
            Object valueObject = jsonObject.get(jsonKey);
            if (transferMode) {
                jsonKey = jsonKey.toLowerCase();
            } else {
                jsonKey = jsonKey.toUpperCase();
            }
            if (valueObject.getClass().toString().endsWith(STRING_KEY)) {
                object.accumulate(jsonKey, valueObject);
            } else if (valueObject.getClass().toString().endsWith(JSONOBJECT_KEY)) {
                JSONObject checkObject = JSONObject.fromObject(valueObject);
                // 当值为null时，valueObject还是JSONObject对象，判空是不成立的，要判断是否是nullObject
                if (!checkObject.isNullObject()) {
                    object.accumulate(jsonKey, transferJsonKey((JSONObject) valueObject, transferMode));
                } else {
                    object.accumulate(jsonKey, null);
                }
            } else if (valueObject.getClass().toString().endsWith(JSONARRAY_KEY)) {
                object.accumulate(jsonKey, transferJsonArray(jsonObject.getJSONArray(jsonKey), transferMode));
            }
        }
        return object;
    }

    /**
     * JSONArray的key转换大小写
     * @param jsonArray Json数组
     * @param transferMode true:小写, false:大写
     * @return
     */
    public static JSONArray transferJsonArray(JSONArray jsonArray, boolean transferMode) {
        JSONArray array = new JSONArray();
        if (null != jsonArray && jsonArray.size() > 0) {
            for (Object object : jsonArray) {
                if (object.getClass().toString().endsWith(JSONOBJECT_KEY)) {
                    array.add(transferJsonKey((JSONObject) object, transferMode));
                } else if (object.getClass().toString().endsWith(JSONARRAY_KEY)) {
                    array.add(transferJsonArray((JSONArray) object, transferMode));
                }
            }
        }
        return array;
    }

    /**
     * 获取Json对象的key值(忽略空值)
     * @param object
     * @param key
     * @return
     */
    public static String getJsonString(JSONObject object, String key) {
        if (StringUtil.isBlank(key)) {
            return "";
        }
        String value = null;
        try {
            value = object.getString(key);
        } catch (Exception e){
            return "";
        }
        if (StringUtils.equals(value, "{}") || StringUtils.equals(value, "null") || value == null) {
            return "";
        }
        return value;
    }
}
