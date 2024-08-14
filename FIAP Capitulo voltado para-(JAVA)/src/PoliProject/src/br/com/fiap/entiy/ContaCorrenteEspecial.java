package br.com.fiap.entiy;

import br.com.fiap.exception.SaldoInsuficienteException;

public class ContaCorrenteEspecial extends ContaCorrente implements Transferivel {

	private double limite;

	@Override
	public void sacar(double valor) throws SaldoInsuficienteException {
		if (valor > valor + limite) {
			throw new SaldoInsuficienteException();
		}
		saldo = saldo - valor;
	}

	@Override
	public double verificarSaldo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean realizarDoc(int nrBanco, int nrAgencia, int nrConta, double valor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean realizarTed(int nrBanco, int nrAgencia, int nrConta, double valor) {
		// TODO Auto-generated method stub
		return false;
	}
}
