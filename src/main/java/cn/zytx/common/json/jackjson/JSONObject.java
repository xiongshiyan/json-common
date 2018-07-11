package cn.zytx.common.json.jackjson;

import cn.zytx.common.json.Json;
import cn.zytx.common.json.JsonArray;
import cn.zytx.common.json.JsonException;
import cn.zytx.common.json.JsonObject;
import cn.zytx.common.json.base.BaseMapJSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiongshiyan at 2018/6/10
 */
public class JSONObject extends BaseMapJSONObject {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JSONObject(Map<String , Object> map){
        super(map);
    }
    public JSONObject(){
        super();
    }
    public JSONObject(String jsonString){
        super(jsonString);
    }

    @Override
    protected Map<String, Object> str2Map(String jsonString) {
        try {
            return objectMapper.readValue(jsonString , Map.class);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    @Override
    public JsonObject getJsonObject(String key) {
        assertKey(key);
        //这里不能使用getJSONObject，因为每一种Json实现不一样，给出的JsonObject类型是不一致的。
        //这里就是各种JsonObject不能混用的原因
        Object temp = this.map.get(key);
        Object t = checkNullValue(key, temp);

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
        Object temp = this.map.get(key);
        Object t = checkNullValue(key, temp);

        if(t instanceof List){
            return new JSONArray((List<Object>)t);
        }
        return (JsonArray) t;
    }


    @Override
    public JsonObject parse(String jsonString) {
        try {
            Map map = objectMapper.readValue(jsonString, Map.class);
            this.map = map;
            return this;
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    @Override
    public String serialize(Object javaBean) {
        try {
            return objectMapper.writeValueAsString(javaBean);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    @Override
    public <T> T deserialize(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString , clazz);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    @Override
    public String toString() {
        //需要针对JsonObject/JsonArray处理
        Map<String , Json> map = new HashMap<>();
        for (String key : map.keySet()) {
            Object o = map.get(key);
            if(o instanceof JsonObject || o instanceof JsonArray){
                map.put(key , (Json) o);
            }
        }
        map.forEach((k , v)-> this.map.put(k , v.unwrap()));

        try {
            return objectMapper.writeValueAsString(this.map);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }
}
