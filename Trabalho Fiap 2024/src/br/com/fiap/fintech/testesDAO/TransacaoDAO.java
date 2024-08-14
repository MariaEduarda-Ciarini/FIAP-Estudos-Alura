package br.com.fiap.fintech.testesDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import br.com.fiap.semperderconexao.DatabaseConnection;

public class TransacaoDAO {
	private Connection conexao;

	public TransacaoDAO() {
		conexao = DatabaseConnection.getConexao();
	}

	public class Transacao {
		public int idTransacao;
		public Date data;
		public double valor;
		public String tipo;
		public String descricao;
		public String categoria;
		public int idCliente;
		public int idConta;
	}

	public List<Transacao> getAll() {
		List<Transacao> resultados = new ArrayList<Transacao>();
		String sql = "SELECT * FROM T_ECOSIMP_TRANSACAO";
		try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			if (!rs.isBeforeFirst()) {
				System.out.println("Não há dados para exibir.");
			} else {
				while (rs.next()) {
					Transacao transacao = this.new Transacao();
					transacao.idTransacao = rs.getInt("ID_TRANSACAO");
					transacao.data = rs.getDate("DATA");
					transacao.valor = rs.getDouble("VALOR");
					transacao.tipo = rs.getString("TIPO");
					transacao.descricao = rs.getString("DESCRICAO");
					transacao.categoria = rs.getString("CATEGORIA");
					transacao.idCliente = rs.getInt("ID_CLIENTE");
					transacao.idCliente = rs.getInt("ID_CONTA");
					resultados.add(transacao);
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar a consulta: " + e.getMessage());
		}
		return resultados;
	}

	public void insert(int idTransacao, Date data, double valor, String tipo, String descricao, String categoria,
			int idCliente, int idConta) {
		String sql = "INSERT INTO T_ECOSIMP_TRANSACAO (ID_TRANSACAO, DATA, VALOR, TIPO, DESCRICAO, CATEGORIA, ID_CLIENTE, ID_CONTA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
			pstmt.setInt(1, idTransacao);
			pstmt.setDate(2, data);
			pstmt.setDouble(3, valor);
			pstmt.setString(4, tipo);
			pstmt.setString(5, descricao);
			pstmt.setString(6, categoria);
			pstmt.setInt(7, idCliente);
			pstmt.setInt(8, idConta);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao inserir dados: " + e.getMessage());
		}
	}

	public Date getRandomDate(int startYear, int endYear) {
		int day = ThreadLocalRandom.current().nextInt(1, 29);
		int month = ThreadLocalRandom.current().nextInt(1, 13);
		int year = ThreadLocalRandom.current().nextInt(startYear, endYear);

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);

		return new Date(calendar.getTimeInMillis());
	}

	public static void main(String[] args) {
		TransacaoDAO banco = new TransacaoDAO();

		banco.insert(19, banco.getRandomDate(2000, 2023), 5000.00, "Crédito", "Salário", "Renda", 19, 19);
		banco.insert(10, banco.getRandomDate(2000, 2023), 2000.00, "Débito", "Aluguel", "Despesa", 10, 10);
		banco.insert(20, banco.getRandomDate(2000, 2023), 15000.00, "Crédito", "Investimento", "Renda", 20, 20);
		banco.insert(3, banco.getRandomDate(2000, 2023), 3000.00, "Débito", "Supermercado", "Despesa", 3, 3);
		banco.insert(7, banco.getRandomDate(2000, 2023), 10000.00, "Crédito", "Herança", "Renda", 7, 7);

		List<Transacao> dados = banco.getAll();
		for (Transacao dado : dados) {
			System.out.println(dado.categoria);
			System.out.println(dado.tipo);
		}
	}
}
