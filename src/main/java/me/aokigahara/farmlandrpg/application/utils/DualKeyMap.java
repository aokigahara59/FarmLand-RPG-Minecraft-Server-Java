package me.aokigahara.farmlandrpg.application.utils;

import java.util.HashMap;
import java.util.Map;

public class DualKeyMap<K1, K2, V> {
    private final Map<K1, Map<K2, V>> map = new HashMap<>();

    public void put(K1 key1, K2 key2, V value) {
        map.computeIfAbsent(key1, k -> new HashMap<>()).put(key2, value);
    }

    public V get(K1 key1, K2 key2) {
        Map<K2, V> subMap = map.get(key1);
        return (subMap != null) ? subMap.get(key2) : null;
    }

    public boolean containsKeys(K1 key1, K2 key2) {
        return map.containsKey(key1) && map.get(key1).containsKey(key2);
    }

    public V remove(K1 key1, K2 key2) {
        Map<K2, V> subMap = map.get(key1);
        if (subMap != null) {
            V value = subMap.remove(key2);
            if (subMap.isEmpty()) {
                map.remove(key1);
            }
            return value;
        }
        return null;
    }

    public void remove(K1 key1){
        map.remove(key1);
    }

    public void clear() {
        map.clear();
    }
}
