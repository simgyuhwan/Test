package com.practice.jpa.test;

public class OwnerInfo {

    private final String name;
    private final String address;
    private final String city;
    private final String telephone;
    private final String pets;

    public OwnerInfo(String name, String address, String city, String telephone, String pets) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }
}
