package org.example.collections.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.*;
import java.util.function.Consumer;


class MainTest {

    List<String> list;
    Integer numberOfElements;

    @BeforeEach
    void beforeEach() {
        list = new ArrayList<>();
        numberOfElements = 200_000;
    }

    @Test
    void addToBeginningTest() {
        long time = getTIme(this::fillListFromBeginning);
        log(time, "addToBeginningTest");
    }

    @Test
    void addToEndTest() {
        long time = getTIme(this::fillListFromEnd);
        log(time, "addToEndTest");
    }

    @Test
    void removeFromBeginningTest() {
        fillListFromEnd(list);
        long time = getTIme(MainTest::removeFormBeginning);
        log(time, "removeFromBeginningTest");
    }

    @Test
    void removeFromMiddleTest() {
        fillListFromEnd(list);
        long time = getTIme(MainTest::removeFromMiddle);
        log(time, "removeFromMiddleTest");
    }

    @Test
    void removeFromEndTest() {
        fillListFromEnd(list);
        long time = getTIme(this::removeFromEnd);
        log(time, "removeFromEndTest");
    }

    @Test
    void iterationTest() {
        fillListFromEnd(list);
        long time = getTIme(this::iterate);
        log(time, "iterationTest");
    }

    private static void removeFormBeginning(List<String> strings) {
        while (!strings.isEmpty()) {
            strings.remove(0);
        }
    }

    private static void removeFromMiddle(List<String> strings) {
        while (!strings.isEmpty()) {
            strings.remove(strings.size() / 2);
        }
    }

    private void removeFromEnd(List<String> strings) {
        while (!strings.isEmpty()) {
            strings.remove(strings.size() - 1);
        }
    }

    private void log(long time, String testName) {
        System.out.println(list.getClass().getTypeName() + ": " + testName + " - " + time);
    }

    private Long getTIme(Consumer<List<String>> consumer) {
        long startingTime = System.currentTimeMillis();
        consumer.accept(list);
        long finishingTile = System.currentTimeMillis();
        return finishingTile - startingTime;
    }

    private void fillListFromEnd(List<String> strings) {
        for (int i = 0; i < numberOfElements; i++) {
            strings.add("" + i);
        }
    }

    private void fillListFromBeginning(List<String> strings) {
        for (int i = 0; i < numberOfElements; i++) {
            strings.add(0, "" + i);
        }
    }

    private void iterate(List<String> strings) {
        for (int i = 0; i < numberOfElements; i++) {
            String value = strings.get(i);
        }
    }
}