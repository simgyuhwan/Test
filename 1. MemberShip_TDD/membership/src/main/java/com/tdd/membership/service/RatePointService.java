package com.tdd.membership.service;

import com.tdd.membership.error.MembershipErrorResult;
import com.tdd.membership.error.MembershipException;
import org.springframework.stereotype.Service;

@Service
public class RatePointService implements PointService{

    private static final int POINT_RATE = 1;

    public boolean isAccumulatedPoint(int price) {
        if(price < 0){
            return false;
        }
        return true;
    }

    public int calculateAmount(int price) {

        return price * POINT_RATE / 100;
    }
}
