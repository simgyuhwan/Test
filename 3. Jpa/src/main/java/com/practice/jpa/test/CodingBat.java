package com.practice.jpa.test;

public class CodingBat {
	public static int countClumps(int[] nums) {
		if(nums == null || nums.length == 0 || nums.length == 1) {
			return 0;
		}
		int count = 0;
		int prev = nums[0];
		boolean inClump = false;
		for (int num : nums) {
			if (num == prev && !inClump) {
				inClump = true;
				count += 1;
			}
			if (num != prev) {
				prev = num;
				inClump = false;
			}
		}
		return count;
	}
}
