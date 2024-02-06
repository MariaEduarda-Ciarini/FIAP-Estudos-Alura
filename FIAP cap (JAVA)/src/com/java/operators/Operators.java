package com.java.operators;

public class Operators {
	public static void main(String[] args) {
		
		int x = 1500;
		int y = 536;
		int z = 2394;
		
		int result = x + y;
		System.out.println(result);
		
		result = x - y;
		System.out.println(result);
		
		result = x * y;
		System.out.println(result);
		
		result = x / y;
		System.out.println(result);
		
		result = x % y;
		System.out.println(result);
		
		result = z - x + y * (x / y);
		System.out.println(result);

	}

}
