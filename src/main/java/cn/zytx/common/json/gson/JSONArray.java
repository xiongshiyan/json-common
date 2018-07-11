package cn.zytx.common.json.gson;

import cn.zytx.common.json.Json;
import cn.zytx.common.json.JsonArray;
import cn.zytx.common.json.JsonObject;
import cn.zytx.common.json.base.BaseListJSONArray;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiongshiyan at 2018/6/11
 */
public class JSONArray extends BaseListJSONArray {
    public JSONArray(List<Object> list){
        super(list);
    }
    public JSONArray(){
    }
    public JSONArray(String arrayString){
        super(arrayString);
    }

    @Override
    public List<Object> str2List(String arrayString) {
        return new Gson().fromJson(arrayString , List.class);
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
        this.list = new Gson().fromJson(jsonString, List.class);
        return this;
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

        return new Gson().toJson(this.list , List.class);
    }
}
