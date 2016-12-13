package com.bujue.dailybenefit.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;

import java.lang.reflect.Type;


/**
 * JSON工具类
 *
 * @author bujue
 */
public class JsonUtil {

    private final static Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();

    public static Gson getGson() {
        return gson;
    }

    /**
     * 将对象转为JSON串，此方法能够满足大部分需求
     *
     * @param src 将要被转化的对象
     * @return 转化后的JSON串
     */
    public static String toJson(Object src) {
        if (src == null) {
            return gson.toJson(JsonNull.INSTANCE);
        }
        return gson.toJson(src);
    }

    /**
     * 用来将JSON串转为对象，但此方法不可用来转带泛型的集合
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * 用来将JSON串转为对象，此方法可用来转带泛型的集合，如：Type为
     * new TypeToken<List<T>>(){}.getType()
     * 其它类也可以用此方法调用，就是将List<T>替换为你想要转成的类
     *
     * @param json
     * @param typeOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

}

