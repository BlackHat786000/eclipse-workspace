package com.core_java.string_problems;

import java.util.ArrayList;

public class GetOccurenceOfChars2 {
	public static void main(String[] args) {
		System.out.println(
				"This program will print no. of occurance of each character in a given string (without use of HashMap)\n");
		String str = "ABBCCCDDDDEEEEEFFFFFF   GGGG%%###****";
		char[] charArr = str.toCharArray();
		ArrayList<Character> list = new ArrayList<Character>();
		outer: for (int i = 0; i <= charArr.length - 1; i++) {
			int cnt = 0;
			for (int j = 0; j <= charArr.length - 1; j++) {
				if (!list.contains(charArr[i])) {
					if (charArr[j] == charArr[i])
						cnt = cnt + 1;

				} else {
					continue outer;
				}

			}
			System.out.println("No. of " + charArr[i] + " : " + cnt);
			list.add(charArr[i]);
		}

	}
}
