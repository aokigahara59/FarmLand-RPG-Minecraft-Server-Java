package me.aokigahara.farmlandrpg.application.utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Tuple<T, K> {
    private T first;
    private K second;

    public Tuple(T first, K second) {
        this.first = first;
        this.second = second;
    }
}
