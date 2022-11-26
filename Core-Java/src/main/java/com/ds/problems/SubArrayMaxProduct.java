package com.ds.problems;

public class SubArrayMaxProduct {

	static int findMaxProductOfSubArray(int arr[]) {

		int max = arr[0];

		for (int i = 0; i < arr.length; i++) {
			int tmp = arr[i];
			max = Math.max(max, tmp);

			for (int j = i + 1; j < arr.length; j++) {
				tmp = tmp * arr[j];
				max = Math.max(max, tmp);

			}
		}

		return max;

	}

	public static void main(String args[]) {
		int a[] = { 6, -3, -10, 0, 2 };
		int product = findMaxProductOfSubArray(a);
		System.out.println("The product of sub array is : " + product);
	}

}
