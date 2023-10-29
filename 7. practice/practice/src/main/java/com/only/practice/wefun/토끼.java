package com.only.practice.wefun;

/**
 * Created by Gyuhwan
 */
public class 토끼 {

  static final int MOD = 1_000_000_007;

  public int solution(int n, int k) {
    int[][] dp = new int[k + 1][n + 1];
    dp[0][0] = 1; // 0 위치에서 시작하는 경우의 수는 1

    for (int i = 1; i <= k; i++) { // 각 점프 횟수에 대해
      int cumulative = 0;
      for (int j = 0; j <= n; j++) { // 각 위치에 대해
        cumulative = (cumulative + dp[i - 1][j]) % MOD;
        if (j >= i) {
          cumulative = (cumulative - dp[i - 1][j - i] + MOD) % MOD;
        }
        dp[i][j] = cumulative;
      }
    }

    return dp[k][n]; // k번 점프해서 n 위치에 도착하는 경우의 수를 반환
  }
}
