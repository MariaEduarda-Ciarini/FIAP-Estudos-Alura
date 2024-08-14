package br.com.fiap.entiy;

import br.com.fiap.exception.SaldoInsuficienteException;

public class Test {

	public static void main(String[] args) {

		ContaCorrente conta = new ContaCorrenteEspecial();

		try {
			conta.sacar(20);
		} catch (SaldoInsuficienteException e) {
			e.printStackTrace();
		}

	}

}
