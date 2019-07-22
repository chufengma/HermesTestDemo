package ctrip.wireless.android.crn.utils;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lxw on 16/5/19.
 */
public class ReactNativeJson {

    public static WritableNativeMap convertJsonToMap(JSONObject jsonObject) throws JSONException {
        WritableNativeMap map = new WritableNativeMap();

        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                map.putMap(key, convertJsonToMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                map.putArray(key, convertJsonToArray((JSONArray) value));
            } else if (value instanceof Boolean) {
                map.putBoolean(key, (Boolean) value);
            } else if (value instanceof Integer) {
                map.putInt(key, (Integer) value);
            } else if (value instanceof Double) {
                map.putDouble(key, (Double) value);
            } else if (value instanceof String) {
                map.putString(key, (String) value);
            } else {
                map.putString(key, value.toString());
            }
        }
        return map;
    }

    public static ArrayList<Object> toArrayList(ReadableArray arrayObj) {
        ArrayList<Object> arrayList = new ArrayList<>();

        for (int i = 0; i < arrayObj.size(); i++) {
            switch (arrayObj.getType(i)) {
                case Null:
                    arrayList.add(null);
                    break;
                case Boolean:
                    arrayList.add(arrayObj.getBoolean(i));
                    break;
                case Number:
                    arrayList.add(arrayObj.getDouble(i));
                    break;
                case String:
                    arrayList.add(arrayObj.getString(i));
                    break;
                case Map:
                    arrayList.add(toHashMap(arrayObj.getMap(i)));
                    break;
                case Array:
                    arrayList.add(toArrayList(arrayObj.getArray(i)));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object at index: " + i + ".");
            }
        }
        return arrayList;
    }

    public static HashMap<String, Object> toHashMap(ReadableMap map) {
        if (map == null) return null;
        ReadableMapKeySetIterator iterator = map.keySetIterator();
        HashMap<String, Object> hashMap = new HashMap<>();

        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (map.getType(key)) {
                case Null:
                    hashMap.put(key, null);
                    break;
                case Boolean:
                    hashMap.put(key, map.getBoolean(key));
                    break;
                case Number:
                    hashMap.put(key, map.getDouble(key));
                    break;
                case String:
                    hashMap.put(key, map.getString(key));
                    break;
                case Map:
                    hashMap.put(key, toHashMap(map.getMap(key)));
                    break;
                case Array:
                    hashMap.put(key, toArrayList(map.getArray(key)));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + key + ".");
            }
        }
        return hashMap;
    }


    public static HashMap<String, String> toStringHashMap(ReadableMap map) {
        if (map == null) return null;
        ReadableMapKeySetIterator iterator = map.keySetIterator();
        HashMap<String, String> hashMap = new HashMap<>();

        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (map.getType(key)) {
                case Null:
                    hashMap.put(key, null);
                    break;
                case String:
                    hashMap.put(key, map.getString(key));
                    break;
                default:
                    Log.e("JS Wrong key: ", key);
                    break;
                //throw new IllegalArgumentException("Could not convert object with key: " + key + ".");
            }
        }
        return hashMap;
    }

    public static WritableArray convertJsonToArray(JSONArray jsonArray) throws JSONException {
        WritableArray array = new WritableNativeArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof JSONObject) {
                array.pushMap(convertJsonToMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                array.pushArray(convertJsonToArray((JSONArray) value));
            } else if (value instanceof Boolean) {
                array.pushBoolean((Boolean) value);
            } else if (value instanceof Integer) {
                array.pushInt((Integer) value);
            } else if (value instanceof Double) {
                array.pushDouble((Double) value);
            } else if (value instanceof String) {
                array.pushString((String) value);
            } else {
                array.pushString(value.toString());
            }
        }
        return array;
    }

    public static JSONObject convertMapToJson(ReadableMap readableMap) throws JSONException {
        JSONObject object = new JSONObject();
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Null:
                    object.put(key, JSONObject.NULL);
                    break;
                case Boolean:
                    object.put(key, readableMap.getBoolean(key));
                    break;
                case Number:
                    object.put(key, readableMap.getDouble(key));
                    break;
                case String:
                    object.put(key, readableMap.getString(key));
                    break;
                case Map:
                    object.put(key, convertMapToJson(readableMap.getMap(key)));
                    break;
                case Array:
                    object.put(key, convertArrayToJson(readableMap.getArray(key)));
                    break;
            }
        }
        return object;
    }

    public static <T> T convertToPOJO(ReadableMap readableMap, Class<T> tClass) {
        try {
            JSONObject orgJson = convertMapToJson(readableMap);
            return JSON.parseObject(orgJson.toString(), tClass);
        } catch (JSONException e) {
            LogUtil.e("error when convertToPOJO", e);
            return null;
        }
    }

    private static JSONArray convertArrayToJson(ReadableArray readableArray) throws JSONException {
        JSONArray array = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    break;
                case Boolean:
                    array.put(readableArray.getBoolean(i));
                    break;
                case Number:
                    array.put(readableArray.getDouble(i));
                    break;
                case String:
                    array.put(readableArray.getString(i));
                    break;
                case Map:
                    array.put(convertMapToJson(readableArray.getMap(i)));
                    break;
                case Array:
                    array.put(convertArrayToJson(readableArray.getArray(i)));
                    break;
            }
        }
        return array;
    }

    public static Bundle bundleFromMap(Map readableMap) {
        if (readableMap == null) {
            return null;
        }

        Bundle bundle = new Bundle();
        Iterator<String> iterator = readableMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = readableMap.get(key);
            if (value == null) {
                continue;
            }
            if (value instanceof Number) {
                // Can be int or double.
                bundle.putDouble(key, (Double) value);
            } else if (value instanceof Boolean) {
                bundle.putBoolean(key, (Boolean) value);
            } else if (value instanceof String) {
                bundle.putString(key, (String) value);
            } else if (value instanceof Map) {
                bundle.putBundle(key, bundleFromMap((Map) value));
            } else if (value instanceof Array) {
                // NOTICE! unsupported
                bundle.putString(key, value.toString());
            } else {
                bundle.putString(key, value.toString());
            }
        }
        return bundle;
    }

    /**
     * Convert a JSON object to a Bundle that can be passed as the extras of
     * an Intent. It passes each number as a double, and everything else as a
     * String, arrays of those two are also supported.
     */
    public static Bundle fromJson(JSONObject s) {
        Bundle bundle = new Bundle();
        for (Iterator<String> it = s.keys(); it.hasNext(); ) {
            String key = it.next();
            JSONArray arr = s.optJSONArray(key);
            JSONObject jsonObj = s.optJSONObject(key);
            Double num = s.optDouble(key);
            String str = s.optString(key);
            if (jsonObj != null) {
                bundle.putBundle(key, fromJson(jsonObj));
            } else if (arr != null && arr.length() <= 0) {
                bundle.putStringArray(key, new String[]{});
            } else if (arr != null && !Double.isNaN(arr.optDouble(0))) {
                double[] newarr = new double[arr.length()];
                for (int i = 0; i < arr.length(); i++) {
                    newarr[i] = arr.optDouble(i);
                }
                bundle.putDoubleArray(key, newarr);
            } else if (arr != null && arr.optString(0) != null) {
                Bundle[] bundles = new Bundle[arr.length()];
                for (int i = 0; i < arr.length(); i++) {
                    try {
                        bundles[i] = fromJson(arr.getJSONObject(i));
                    } catch (JSONException e) {
                        LogUtil.e("unable to transform get JsonObject from array " + key, e);
                    }
                }
                bundle.putParcelableArray(key, bundles);
            } else if (!num.isNaN()) {
                bundle.putDouble(key, num);
            } else if (str != null) {
                bundle.putString(key, str);
            } else {
                LogUtil.e("unable to transform json to bundle " + key);
            }
        }

        return bundle;
    }

}