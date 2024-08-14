package br.com.fiap.entiy;

import br.com.fiap.exception.SaldoInsuficienteException;

public abstract class ContaCorrente {

	protected double saldo;
	public static final int BANCO = 33;

	public void sacar(double valor) throws SaldoInsuficienteException {
		if (valor > saldo) {
			throw new SaldoInsuficienteException();
		}
		saldo = saldo - valor;
	}

	public abstract double verificarSaldo();
}
