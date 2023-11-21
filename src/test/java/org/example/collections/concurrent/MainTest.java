package org.example.collections.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;


class MainTest {

    List<String> list;
    Integer numberOfElements;

    @BeforeEach
    void beforeEach() {
        list = new ArrayList<>();
        numberOfElements = 10_000_000;
    }

    @Test
    void addTest() {
        printTime(this::fillList);
    }

    @Test
    void removeFromEndTest() {
        fillList(list);
        printTime(strings -> {
            ListIterator<String> listIterator = strings.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                listIterator.previous();
                listIterator.remove();
            }
            System.out.println(strings);
        });
    }

    @Test
    void removeFromBeginningTest() {
        fillList(list);
        printTime(strings -> {
            ListIterator<String> listIterator = strings.listIterator();
            while (listIterator.hasNext()) {
                listIterator.next();
                listIterator.remove();
            }
            System.out.println(strings);
        });
    }

    private void printTime(Consumer<List<String>> consumer) {
        long startingTime = System.currentTimeMillis();
        consumer.accept(list);
        long finishingTile = System.currentTimeMillis();
        System.out.println(finishingTile - startingTime);
    }

    private void fillList(List<String> strings) {
        for (int i = 0; i < numberOfElements; i++) {
            strings.add("" + i);
        }
    }
}