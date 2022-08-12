package com.tdd.membership.service;

public interface PointService {
    public int calculateAmount(int price);

    public boolean isAccumulatedPoint(int price);
}
