package com.java.operators;

public class LogicalOperators {
	public static void main(String[] args) {

		int age = 20;
		boolean need_to_vote = age >= 16 && age < 70;
		System.out.println(need_to_vote);

		int nrYellow = 2, nrRed = 1;
		boolean suspended = nrYellow == 2 || nrRed == 1;
		System.out.println(suspended);

		int x = 9, y = 11;
		boolean test = x > 10 ^ y > 10;
		System.out.println(test);

		int age2 = 20;
		boolean majority = !(age2 >= 18);
		System.out.println(majority);
	}

}
