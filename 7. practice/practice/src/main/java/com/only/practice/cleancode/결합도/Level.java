package com.only.practice.cleancode.결합도;

import java.lang.reflect.Field;

public class Level {
    private static final int MIN = 1;
    private static final int MAX = 99;
    final int value;

    private Level(final int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    static Level initialize() {
        return new Level(MIN);
    }

    Level increase() {
        if (value < MAX) return new Level(value + 1);
        return this;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Level level = Level.initialize();
        System.out.println("Level : " + level.value);

        Field field = Level.class.getDeclaredField("value");
        field.setAccessible(true);
        field.setInt(level, 999);

        System.out.println("Level : " + level.value);
    }
}
