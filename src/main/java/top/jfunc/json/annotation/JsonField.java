package top.jfunc.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * field序列化和反序列化及名字
 * @author xiongshiyan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD , ElementType.METHOD})
public @interface JsonField {
    /**
     * 序列化名字
     */
    String value() default "";

    /**
     * 是否序列化 , it won't serialize if false
     */
    boolean serialize() default true;

    /**
     * 是否反序列化, it won't deserialize if false
     */
    boolean deserialize() default true;
}
