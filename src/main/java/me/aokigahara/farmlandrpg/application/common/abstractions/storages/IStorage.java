package me.aokigahara.farmlandrpg.application.common.abstractions.storages;

import java.util.List;

public interface  IStorage<T> {

    void add(T item);
    void delete(T item);
    List<T> getAll();
    boolean contains(T item);
}
