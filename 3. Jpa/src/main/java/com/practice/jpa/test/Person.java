package com.practice.jpa.test;

public record Person(String name, int age) {

    public int getChange() {
        return age + age;
    }
}
