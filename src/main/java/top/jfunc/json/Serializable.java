package top.jfunc.json;

/**
 * 1.JavaBean序列化为String
 * 2.String反序列化为JavaBean
 * @author xiongshiyan at 2018/6/10
 */
public interface Serializable {
    /**
     * 序列化，把一个JavaBean序列化为String
     * @param javaBean JavaBean对象
     * @param nullHold null是否保留,保留的时候就是"key":null
     * @param ignoreFields 忽略那些字段
     * @return String
     */
    <T> String serialize(T javaBean , boolean nullHold , String...ignoreFields);

    /**
     * 序列化，把一个JavaBean序列化为String
     * @param javaBean JavaBean对象
     * @param ignoreFields 忽略那些字段
     * @return String
     */
    default <T> String serialize(T javaBean , String...ignoreFields){
        return serialize(javaBean , false , ignoreFields);
    }

    /**
     * 反序列化，把一个字符串反序列化为一个JavaBean
     * @param jsonString Json String
     * @param clazz JavaBean Class
     */
    <T> T deserialize(String jsonString, Class<T> clazz);
}
