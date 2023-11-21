package org.example.collections.concurrent.onwritearray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        copyOnWriteArrayList.add("value1");
        copyOnWriteArrayList.add("value2");
        copyOnWriteArrayList.add("value3");
        copyOnWriteArrayList.add("value4");
        copyOnWriteArrayList.add("value5");

        System.out.println(copyOnWriteArrayList);

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Iterator<String> iterator = copyOnWriteArrayList.iterator(); // this iterator method returns an iterator
                                                                        // that is able to take snapshots of collections
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
//                iterator.remove(); we can not use remove, add, set etc. methods with this type of iterator (UnsupportedOperationException)
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // every time we want to remove an object we create a new copy of our array
            copyOnWriteArrayList.remove(2);
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        System.out.println(copyOnWriteArrayList);
    }
}
