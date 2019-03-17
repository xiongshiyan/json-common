# common-json
common json interface define and base impl with map and  list

我们项目中可能会用到许多的json框架，Fastjson、jackson、Gson、orgjson、json-lib，其实现方式各不相同，接口Api就不一样，我们想换一个json框架改动的地方就会很多。参考SLF4J的思想，我统一了json框架的调用方式。定义了顶层的json接口，其下定义了代表JsonObject和JsonArray的cn.zytx.common.json.JsonObject和cn.zytx.common.json.JsonArray接口，不同的json框架去实现此接口。其实现类为cn.zytx.common.json.impl.JSONObject和cn.zytx.common.json.impl.JSONArray。 **_注意所有的框架实现，这两个类名不变_** 。于是调用方完全不用修改代码。详细说明如下：

@link 

https://blog.csdn.net/xxssyyyyssxx/article/details/80696560

https://blog.csdn.net/xxssyyyyssxx/article/details/80696822


一些示例代码：

https://gitee.com/xxssyyyyssxx/Json-fastjson/blob/master/src/test/java/top/jfunc/json/JSONObjectTest.java

https://gitee.com/xxssyyyyssxx/Json-fastjson/blob/master/src/test/java/top/jfunc/json/JSONArrayTest.java


@see 

https://gitee.com/xxssyyyyssxx/Json-fastjson

https://gitee.com/xxssyyyyssxx/Json-Gson

https://gitee.com/xxssyyyyssxx/Json-Jackson

https://gitee.com/xxssyyyyssxx/Json-orgJson

https://gitee.com/xxssyyyyssxx/Json-Jsonlib

https://gitee.com/xxssyyyyssxx/json-codejson

https://gitee.com/xxssyyyyssxx/json-jsoniter

https://gitee.com/xxssyyyyssxx/json-tokenjson



