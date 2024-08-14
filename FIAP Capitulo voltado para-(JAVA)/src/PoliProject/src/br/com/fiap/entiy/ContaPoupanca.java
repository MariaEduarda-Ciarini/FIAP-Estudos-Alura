package br.com.fiap.entiy;

public final class ContaPoupanca extends Conta {

	@Override
	public double verificarSaldo() {
		return saldo;
	}
}

// classe ContaPoupanca não pode possuir nenhuma subclasse. Dessa forma,
// não podemos utilizá-la como base para criar nenhuma outra classe:
// O código mostrado não compila, pois, a ContaPoupanca está marcada como
// final e, por isso, não pode ser estendida