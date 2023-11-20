package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        List<Integer> integerList = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 5; i++) {
            integerList.add(i);
        }


        Runnable runnable = () -> {
            synchronized (integerList) {
                for (Integer value : integerList) {
                    System.out.println(value);
                }
            }
        };

        Runnable runnable2 = () -> integerList.remove(2);

        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        System.out.println(integerList);

    }
}