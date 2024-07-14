package org.ashfaq.dev.parallelcomputing;

public class Sum_Problem_Sequential {

	public int sum(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {

			sum += nums[i];

		}

		return sum;
	}

	public static void main(String[] args) {

		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int sum = 0;
		Sum_Problem_Sequential sum_Problem_Sequential = new Sum_Problem_Sequential();
		sum = sum_Problem_Sequential.sum(nums);
		System.out.println(sum);
	}

}
