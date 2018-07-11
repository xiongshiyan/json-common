package cn.zytx.common.json.fastjson;

import cn.zytx.common.json.base.BaseJson;
import cn.zytx.common.json.Json;
import cn.zytx.common.json.JsonArray;
import cn.zytx.common.json.JsonObject;
import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiongshiyan at 2018/6/11
 */
public class JSONArray extends BaseJson<JSONArray> implements JsonArray {
    private com.alibaba.fastjson.JSONArray jsonArray;
    public JSONArray(com.alibaba.fastjson.JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }
    public JSONArray(List<Object> list){
        this.jsonArray = new com.alibaba.fastjson.JSONArray(list);
    }
    public JSONArray(){
        this.jsonArray = new com.alibaba.fastjson.JSONArray();
    }
    public JSONArray(String arrayString){
        this.jsonArray = JSON.parseArray(arrayString);
    }
    @Override
    public int size() {
        return jsonArray.size();
    }

    @Override
    public Object get(int index) {
        assertIndex(index , size());
        return checkNullValue(index , jsonArray.get(index));
    }

    @Override
    public String getString(int index) {
        assertIndex(index , size());
        return checkNullValue(index , jsonArray.getString(index));
    }

    @Override
    public Boolean getBoolean(int index) {
        assertIndex(index , size());
        return checkNullValue(index , jsonArray.getBoolean(index));
    }

    @Override
    public Integer getInteger(int index) {
        assertIndex(index , size());
        return checkNullValue(index , jsonArray.getInteger(index));
    }

    @Override
    public Long getLong(int index) {
        assertIndex(index , size());
        return checkNullValue(index , jsonArray.getLong(index));
    }

    @Override
    public Double getDouble(int index) {
        assertIndex(index , size());
        return checkNullValue(index , jsonArray.getDouble(index));
    }

    @Override
    public Float getFloat(int index) {
        assertIndex(index , size());
        return checkNullValue(index , jsonArray.getFloat(index));
    }

    @Override
    public BigInteger getBigInteger(int index) {
        assertIndex(index , size());
        return checkNullValue(index , jsonArray.getBigInteger(index));
    }

    @Override
    public BigDecimal getBigDecimal(int index) {
        assertIndex(index , size());
        return checkNullValue(index , jsonArray.getBigDecimal(index));
    }

    @Override
    public JsonObject getJsonObject(int index) {
        assertIndex(index , size());
        Object opt = jsonArray.get(index);
        if(opt instanceof com.alibaba.fastjson.JSONObject){
            return new JSONObject((com.alibaba.fastjson.JSONObject)opt);
        }
        if(opt instanceof Map){
            return new JSONObject((Map<String, Object>) opt);
        }
        return (JsonObject) opt;
    }

    @Override
    public JsonArray getJsonArray(int index) {
        assertIndex(index , size());
        Object opt = jsonArray.get(index);
        if(opt instanceof com.alibaba.fastjson.JSONArray){
            return new JSONArray((com.alibaba.fastjson.JSONArray)opt);
        }
        if(opt instanceof List){
            return new JSONArray((List)opt);
        }
        return (JsonArray) opt;
    }

    @Override
    public JsonArray remove(int index) {
        jsonArray.remove(index);
        return this;
    }

    @Override
    public JsonArray clear() {
        jsonArray.clear();
        return this;
    }

    @Override
    public JsonArray put(Object o) {
        jsonArray.add(o);
        return this;
    }

    @Override
    public JsonArray put(int index, Object o) {
        jsonArray.remove(index);
        jsonArray.add(index , o);
        return this;
    }

    @Override
    public JsonArray putAll(Collection<?> os) {
        jsonArray.addAll(os);
        return this;
    }

    @Override
    public JsonArray parse(String jsonString) {
        this.jsonArray = JSON.parseArray(jsonString);
        return this;
        //return new JSONArray(JSON.parseArray(jsonString));
    }

    @Override
    public com.alibaba.fastjson.JSONArray unwrap() {
        return jsonArray;
    }

    @Override
    public String toString() {
        //需要针对JsonObject/JsonArray处理
        Map<Integer , Json> map = new HashMap<>();
        int size = size();
        for (int i = 0; i < size; i++) {
            Object o = jsonArray.get(i);
            if(o instanceof JsonObject || o instanceof JsonArray){
                map.put(i , (Json) o);
            }
        }
        map.forEach((k,v)->{
            jsonArray.remove((int)k);
            jsonArray.add((int)k , v.unwrap());
        });

        return jsonArray.toString();
    }

    @Override
    public int hashCode() {
        return jsonArray.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return jsonArray.equals(obj);
    }

    @Override
    public JsonArray fromList(List<Object> list) {
        jsonArray = new com.alibaba.fastjson.JSONArray(list);
        return this;
    }
}
