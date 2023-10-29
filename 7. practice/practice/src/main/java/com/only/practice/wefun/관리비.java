package com.only.practice.wefun;

/**
 * Created by Gyuhwan
 */
public class 관리비 {

  public int[] solution(int day, int k) {
    int[] answer = new int[12];
    int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int currentDay = day;

    for (int month = 0; month < 12; month++) {
      int paymentDay = (currentDay + k) % 7;

      if (paymentDay == 0 || paymentDay == 6) {
        answer[month] = 1;
      } else {
        answer[month] = 0;
      }

      currentDay = (currentDay + monthDays[month]) % 7;
    }

    return answer;
  }

}
