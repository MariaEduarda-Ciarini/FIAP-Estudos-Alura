package br.com.fiap.mercado.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transacao {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection conexao = null;

		int idProduto = 1015;
		int qtdProdutosParaTransferir = 5;
		int idArmazemOrigem = 1;
		int idArmazemDestino = 2;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conexao = DriverManager.getConnection(
					"jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RMXXXXXX",
					"XXXXXX");

			// Desabilita o autocommit
			conexao.setAutoCommit(false);

			// Primeira transacao - Atualiza o estoque do armazem 1
			PreparedStatement stmt = conexao.prepareStatement("UPDATE T_ESTOQUE SET "
					+ "QT_PRODUTO = QT_PRODUTO - ? "
					+ "WHERE CD_PRODUTO = ? "
					+ "AND CD_ARMAZEM = ?");

			stmt.setInt(1, qtdProdutosParaTransferir);
			stmt.setInt(2, idProduto);
			stmt.setInt(3, idArmazemOrigem);
			stmt.executeUpdate();

			// Segunda transação - Atualiza o estoque do armazem 2
			PreparedStatement stmt2 = conexao.prepareStatement("UPDATE T_ESTOQUE SET "
					+ "QT_PRODUTO = QT_PRODUTO + ? " // Ajuste aqui para somar a quantidade no destino
					+ "WHERE CD_PRODUTO = ? "
					+ "AND CD_ARMAZEM = ?");

			stmt2.setInt(1, qtdProdutosParaTransferir); // Aqui usamos a mesma quantidade que foi transferida
			stmt2.setInt(2, idProduto);
			stmt2.setInt(3, idArmazemDestino);
			stmt2.executeUpdate();

			// Efetiva as duas transações
			conexao.commit();

			// Imprime se der tudo certo
			System.out.println("05 produtos 1015 foram transferidos do armazém 1 para o 2");

		} catch (SQLException se) {
			// Não efetiva as duas transções
			conexao.rollback();
			System.out.println("Os 05 produtos 1015 não foram transferidos do armazém 1 para 2");
			se.printStackTrace();
		}

	}

}
