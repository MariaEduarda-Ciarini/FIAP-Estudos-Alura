package br.com.fiap;

public class TestException {

}

	public static void main(String[] args) throws DivisionbyZeroExcepetion {

		try {
			int val = 10 / 0;
			System.out.println(val);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
