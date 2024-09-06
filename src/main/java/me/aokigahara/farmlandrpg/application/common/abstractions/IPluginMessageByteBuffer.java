package me.aokigahara.farmlandrpg.application.common.abstractions;

import org.jetbrains.annotations.NotNull;

public interface IPluginMessageByteBuffer {

    void writeInt(int value);
    int readInt();

    void writeVarInt(int value);
    int readVarInt();
    void writeLong(long value);
    long readLong();
    void writeVarLong(long value);
    long readVarLong();
    void writeBoolean(boolean value);
    boolean readBoolean();
    void writeString(@NotNull String string);
    String readString();
    void writeBytes(byte[] bytes);
    void writeByteArray(byte[] bytes);
    byte[] readByteArray();
    byte[] readBytes();
    byte[] readBytes(int size);
    void readBytes(byte[] destination);
    void writeByte(byte value);
    void writeByte(int value);
    byte readByte();
    byte[] asByteArray();

    void writeDouble(double value);
    double readDouble();
}
