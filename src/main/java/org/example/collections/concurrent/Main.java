package org.example.collections.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        List<String> vector = new Vector<>();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 10_000_000; i++) {
            vector.add("" + i);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time);

        List<String> arrayList = new ArrayList<>();
        long time3 = System.currentTimeMillis();
        for (int i = 0; i < 10_000_000; i++) {
            arrayList.add("" + i);
        }
        long time4 = System.currentTimeMillis();
        System.out.println(time4 - time3);
    }
}
