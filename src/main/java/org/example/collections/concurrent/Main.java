package org.example.collections.concurrent;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        concurrentHashMap.put("key1", "value1");
        concurrentHashMap.put("key2", "value2");
        concurrentHashMap.put("key3", "value3");
        concurrentHashMap.put("key4", "value4");
        concurrentHashMap.put("key5", "value5");
        concurrentHashMap.put("key6", "value6");
        concurrentHashMap.put("key7", "value7");

        Thread thread = new Thread(() -> {
            Iterator<String> iterator = concurrentHashMap.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String key = iterator.next();
                System.out.println(key + ": " + concurrentHashMap.get(key));
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            concurrentHashMap.put("key8", "value8");
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        System.out.println(concurrentHashMap);
    }
}
