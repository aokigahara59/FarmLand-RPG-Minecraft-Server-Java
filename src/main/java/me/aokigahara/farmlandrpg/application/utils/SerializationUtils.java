package me.aokigahara.farmlandrpg.application.utils;

import org.jetbrains.annotations.Nullable;

import java.io.*;

public class SerializationUtils {
    public static byte @Nullable [] serialize(Object obj) {

        try (var byteOutputStream = new ByteArrayOutputStream();
             var output = new ObjectOutputStream(byteOutputStream)) {
            output.writeObject(obj);
            return byteOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static @Nullable Object deserialize(byte[] bytes){
        try (var byteStream = new ByteArrayInputStream(bytes);
             var inputStream = new ObjectInputStream(byteStream)) {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
