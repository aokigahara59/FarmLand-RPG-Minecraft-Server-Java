package me.aokigahara.farmlandrpg.application.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.charset.StandardCharsets;

public class JsonProxySerializationUtils {
    public static byte[] serialize(Object obj) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        Gson gson = gsonBuilder.create();

        String json = gson.toJson(obj);
        return json.getBytes(StandardCharsets.UTF_8);
    }

    public static <T> T deserialize(byte[] bytes, Class<?> clazz) {
        String json = new String(bytes, StandardCharsets.UTF_8);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        Gson gson = gsonBuilder.create();
        return (T) gson.fromJson(json, clazz);
    }
}
