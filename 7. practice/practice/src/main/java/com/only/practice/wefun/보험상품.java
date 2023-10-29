package com.only.practice.wefun;

/**
 * Created by Gyuhwan
 */
public class 보험상품 {

  static int answer = 0;
  static int[] nums;
  int K, T;

  public int solution(int[] arr, int k, int t) {
    nums = arr;
    K = k;
    T = t;

    for (int i = 0; i < arr.length; i++) {
      dfs(i, arr[i], 1);
    }

    return answer;
  }

  private void dfs(int index, int sum, int count) {
    if (count >= K && sum <= T) {
      answer++;
    }

    for (int i = index + 1; i < nums.length; i++) {
      if (sum + nums[i] <= T) {
        dfs(i, sum + nums[i], count + 1);
      }
    }

  }
}
