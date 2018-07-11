package cn.zytx.common.json.fastjson;

import cn.zytx.common.json.base.BaseJson;
import cn.zytx.common.json.Json;
import cn.zytx.common.json.JsonArray;
import cn.zytx.common.json.JsonObject;
import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xiongshiyan at 2018/6/10
 */
public class JSONObject extends BaseJson<JSONObject> implements JsonObject {

    private com.alibaba.fastjson.JSONObject jsonObject;

    public JSONObject(com.alibaba.fastjson.JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }
    public JSONObject(Map<String , Object> map){
        this.jsonObject = new com.alibaba.fastjson.JSONObject(map);
    }
    public JSONObject(){
        this.jsonObject = new com.alibaba.fastjson.JSONObject();
    }
    public JSONObject(String jsonString){
        this.jsonObject = JSON.parseObject(jsonString);
    }

    @Override
    public com.alibaba.fastjson.JSONObject unwrap() {
        return jsonObject;
    }

    @Override
    public Object get(String key) {
        assertKey(key);
        return checkNullValue(key , jsonObject.get(key));
    }

    @Override
    public Object get(String key, Object defaultObject) {
        assertKey(key);
        Object temp = jsonObject.get(key);
        return null == temp ? defaultObject : temp;
    }

    @Override
    public JsonObject getJsonObject(String key) {
        assertKey(key);
        //这里不能使用getJSONObject，因为每一种Json实现不一样，给出的JsonObject类型是不一致的。
        //这里就是各种JsonObject不能混用的原因
        Object temp = this.jsonObject.get(key);
        Object t = checkNullValue(key, temp);

        if(t instanceof com.alibaba.fastjson.JSONObject){
            return new JSONObject((com.alibaba.fastjson.JSONObject) t);
        }
        if(t instanceof Map){
            return new JSONObject((Map<String, Object>) t);
        }

        return (JsonObject) t;
    }

    @Override
    public JsonArray getJsonArray(String key) {
        assertKey(key);
        //这里不能使用getJSONObject，因为每一种Json实现不一样，给出的JsonObject类型是不一致的。
        //这里就是各种JsonObject不能混用的原因
        Object temp = this.jsonObject.get(key);
        Object t = checkNullValue(key, temp);

        if(t instanceof com.alibaba.fastjson.JSONArray){
            return new JSONArray((com.alibaba.fastjson.JSONArray)t);
        }
        if(t instanceof List){
            return new JSONArray((List<Object>) t);
        }
        return (JsonArray) t;
    }

    @Override
    public String getString(String key) {
        assertKey(key);
        String temp = this.jsonObject.getString(key);
        return checkNullValue(key, temp);
    }

    @Override
    public String getString(String key, String defaultValue) {
        assertKey(key);
        String temp = this.jsonObject.getString(key);
        return null == temp ? defaultValue : temp;
    }

    @Override
    public Boolean getBoolean(String key) {
        assertKey(key);
        Boolean temp = this.jsonObject.getBoolean(key);
        return checkNullValue(key, temp);
    }

    @Override
    public Boolean getBoolean(String key, Boolean defaultValue) {
        assertKey(key);
        Boolean temp = this.jsonObject.getBoolean(key);
        return null == temp ? defaultValue : temp;
    }

    @Override
    public Integer getInteger(String key) {
        assertKey(key);
        Integer temp = this.jsonObject.getInteger(key);
        return checkNullValue(key, temp);
    }

    @Override
    public Integer getInteger(String key, Integer defaultValue) {
        assertKey(key);
        Integer temp = this.jsonObject.getInteger(key);
        return null == temp ? defaultValue : temp;
    }

    @Override
    public Long getLong(String key) {
        assertKey(key);
        Long temp = this.jsonObject.getLong(key);
        return checkNullValue(key, temp);
    }

    @Override
    public Long getLong(String key, Long defaultValue) {
        assertKey(key);
        Long temp = this.jsonObject.getLong(key);
        return null == temp ? defaultValue : temp;
    }

    @Override
    public Float getFloat(String key) {
        assertKey(key);
        Float temp = this.jsonObject.getFloat(key);
        return checkNullValue(key, temp);
    }

    @Override
    public Float getFloat(String key, Float defaultValue) {
        assertKey(key);
        Float temp = this.jsonObject.getFloat(key);
        return null == temp ? defaultValue : temp;
    }

    @Override
    public Double getDouble(String key) {
        assertKey(key);
        Double temp = this.jsonObject.getDouble(key);
        return checkNullValue(key, temp);
    }

    @Override
    public Double getDouble(String key, Double defaultValue) {
        assertKey(key);
        Double temp = this.jsonObject.getDouble(key);
        return null == temp ? defaultValue : temp;
    }

    @Override
    public BigInteger getBigInteger(String key) {
        assertKey(key);
        BigInteger temp = this.jsonObject.getBigInteger(key);
        return checkNullValue(key, temp);
    }

    @Override
    public BigInteger getBigInteger(String key, BigInteger defaultValue) {
        assertKey(key);
        BigInteger temp = this.jsonObject.getBigInteger(key);
        return null == temp ? defaultValue : temp;
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        assertKey(key);
        BigDecimal temp = this.jsonObject.getBigDecimal(key);
        return checkNullValue(key, temp);
    }

    @Override
    public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        assertKey(key);
        BigDecimal temp = this.jsonObject.getBigDecimal(key);
        return null == temp ? defaultValue : temp;
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        return jsonObject.getObject(key , clazz);
    }

    @Override
    public Set<String> keySet() {
        return jsonObject.keySet();
    }

    @Override
    public int size() {
        return jsonObject.size();
    }

    @Override
    public boolean isEmpty() {
        return jsonObject.isEmpty();
    }

    @Override
    public boolean containsKey(String key) {
        return jsonObject.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return jsonObject.containsValue(value);
    }

    @Override
    public JsonObject put(String key, Object value) {
        jsonObject.put(key, value);
        return this;
    }

    @Override
    public JsonObject putAll(Map<? extends String, ?> m) {
        jsonObject.putAll(m);
        return this;
    }

    @Override
    public JsonObject clear() {
        jsonObject.clear();
        return this;
    }

    @Override
    public Object remove(String key) {
        return jsonObject.remove(key);
    }

    @Override
    public JsonObject parse(String jsonString) {
        jsonObject = JSON.parseObject(jsonString);
        return this;
        //return new JSONObject(JSON.parseObject(jsonString));
    }

    @Override
    public JsonObject fromMap(Map<String, Object> map) {
        return new JSONObject(new com.alibaba.fastjson.JSONObject(map));
    }

    @Override
    public String serialize(Object javaBean) {
        return JSON.toJSONString(javaBean);
    }

    @Override
    public <T> T deserialize(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString , clazz);
    }

    @Override
    public String toString() {
        //需要针对JsonObject/JsonArray处理
        Map<String , Json> map = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            Object o = jsonObject.get(key);
            if(o instanceof JsonObject || o instanceof JsonArray){
                map.put(key , (Json) o);
            }
        }
        map.forEach((k , v)-> jsonObject.put(k , v.unwrap()));

        return jsonObject.toString();
    }

    @Override
    public int hashCode() {
        return jsonObject.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return jsonObject.equals(obj);
    }

}
