package com.talultimate.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WeightedPicker<T> {
    private static class E<T>{ T v; int w; E(T v,int w){this.v=v;this.w=w;} }
    private final List<E<T>> list = new ArrayList<>();
    private int total = 0;
    public void add(T v, int weight){ if (weight<=0) return; list.add(new E<>(v,weight)); total+=weight; }
    public T pick(){
        if (total<=0) return null;
        int r = ThreadLocalRandom.current().nextInt(total), acc=0;
        for (E<T> e: list){ acc+=e.w; if (r<acc) return e.v; }
        return null;
    }
}
