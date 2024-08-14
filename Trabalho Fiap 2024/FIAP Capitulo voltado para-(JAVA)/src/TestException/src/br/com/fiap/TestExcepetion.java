package br.com.fiap;

public class TestExcepetion {

	public static void main(String[] args) throws DivisionbyZeroExcepetion {

		// try {

		// section where the exception may occur
		// int val = 10/0;
		// System.out.println(val);

		// } catch (Exception e) { // exception catching

		// exception handling
		// e.printStackTrace();

		// java.lang.ArithmeticException: / by zero at
		// TestException/br.com.fiap.TestExcepetion.main(TestExcepetion.java:10)

		// Unchecked exceptions

		try {
			// It is not possible to divide by zero! / by zero
			// java.lang.ArithmeticException: / by zero at
			// TestException/br.com.fiap.TestExcepetion.main(TestExcepetion.java:29)
			int val = 10 / 0;

			int[] values = new int[3];
			System.out.println(values[4]);

			String name = null;
			System.out.println(name.length());

			int number = Integer.parseInt("Zero");

			// } catch (ArithmeticException e) {

			// System.out.println("It is not possible to divide by zero!");

			// message with the list of errors

			// System.out.println(e.getMessage());

			// print the exception error stack

			// e.printStackTrace();

			// Cannot access array position 4!

		} catch (ArrayIndexOutOfBoundsException e) {

			System.out.println("Cannot access array position 4! ");

		} catch (NullPointerException e) {

			System.out.println("Could not return length, variable not instantiated! ");

		} catch (NumberFormatException e) {

			System.out.println("Unable to perform conversion!");

		} catch (Exception e) {

			System.out.println("Error executing the program!");
		}

		finally {
			System.out.println("It will always come here!");
		}

	}
}
