package cn.zytx.common.json.jackjson;

import cn.zytx.common.json.Json;
import cn.zytx.common.json.JsonArray;
import cn.zytx.common.json.JsonException;
import cn.zytx.common.json.JsonObject;
import cn.zytx.common.json.base.BaseListJSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiongshiyan at 2018/6/11
 */
public class JSONArray extends BaseListJSONArray {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public JSONArray(List<Object> list){
        super(list);
    }
    public JSONArray(){
        super();
    }
    public JSONArray(String arrayString){
        super(arrayString);
    }

    @Override
    public List<Object> str2List(String arrayString) {
        try {
            return objectMapper.readValue(arrayString , List.class);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    @Override
    public JsonObject getJsonObject(int index) {
        assertIndex(index , size());
        Object opt = list.get(index);
        if(opt instanceof Map){
            return new JSONObject((Map<String, Object>) opt);
        }
        return (JsonObject) opt;
    }

    @Override
    public JsonArray getJsonArray(int index) {
        assertIndex(index , size());
        Object opt = list.get(index);
        if(opt instanceof List){
            return new JSONArray((List<Object>) opt);
        }
        return (JsonArray) opt;
    }

    @Override
    public JsonArray parse(String jsonString) {
        try {
            this.list = objectMapper.readValue(jsonString, List.class);
            return this;
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    @Override
    public String toString() {
        //需要针对JsonObject/JsonArray处理
        Map<Integer , Json> map = new HashMap<>();
        int size = size();
        for (int i = 0; i < size; i++) {
            Object o = list.get(i);
            if(o instanceof JsonObject || o instanceof JsonArray){
                map.put(i , (Json) o);
            }
        }
        map.forEach((k,v)->{
            list.remove((int)k);
            list.add(k , v.unwrap());
        });

        try {
            return objectMapper.writeValueAsString(this.list);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    @Override
    public JsonArray fromList(List<Object> list) {
        /*String jsonString = objectMapper.writeValueAsString(list);
        this.list = objectMapper.readValue(jsonString, List.class);*/
        this.list = list;
        return this;
    }
}
