package br.com.fiap.fintech.testesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Calendar;

import br.com.fiap.semperderconexao.DatabaseConnection;

public class ContaDAO {
	private Connection conexao;

	public ContaDAO() {
		conexao = DatabaseConnection.getConexao();
	}

	public class Conta {
		public int idConta;
		public double saldo;
		public String stauts;
		public int idCliente;
		public Date dtCriacao;
		public double limiteCredito;
		public String numero;
		public String tipoConta;
	}

	public List<Conta> getAll() {
		List<Conta> resultados = new ArrayList<Conta>();
		String sql = "SELECT * FROM T_ECOSIMP_CONTA";
		try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			if (!rs.isBeforeFirst()) {
				System.out.println("Não há dados para exibir.");
			} else {
				while (rs.next()) {
					Conta conta = this.new Conta();
					conta.idConta = rs.getInt("ID_CONTA");
					conta.saldo = rs.getDouble("SALDO");
					conta.stauts = rs.getString("STATUS");
					conta.idCliente = rs.getInt("ID_CLIENTE");
					conta.dtCriacao = rs.getDate("DT_CRIACAO");
					conta.limiteCredito = rs.getDouble("LIMITE_CREDITO");
					conta.numero = rs.getString("NUMERO");
					conta.tipoConta = rs.getString("TIPO_CONTA");
					resultados.add(conta);
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar a consulta: " + e.getMessage());
		}
		return resultados;
	}

	public void insert(int idConta, double saldo, String status, int idCliente, Date dtCriacao, double limiteCredito,
			String numero, String tipoConta) {
		String sql = "INSERT INTO T_ECOSIMP_CONTA (ID_CONTA, SALDO, STATUS, ID_CLIENTE, DT_CRIACAO, LIMITE_CREDITO, NUMERO, TIPO_CONTA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
			pstmt.setInt(1, idConta);
			pstmt.setDouble(2, saldo);
			pstmt.setString(3, status);
			pstmt.setInt(4, idCliente);
			pstmt.setDate(5, new java.sql.Date(dtCriacao.getTime()));
			pstmt.setDouble(6, limiteCredito);
			pstmt.setString(7, numero);
			pstmt.setString(8, tipoConta);
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
		ContaDAO banco = new ContaDAO();

		banco.insert(19, 20, "ON", 19, banco.getRandomDate(2000, 2023), 4500, "336699", "Conta Rendimeno");
		banco.insert(10, 600, "ON", 10, banco.getRandomDate(2000, 2023), 2000, "689334", "Conta Poupança");
		banco.insert(20, 10, "OFF", 20, banco.getRandomDate(2000, 2023), 1500.0, "839440", "Conta Salário");
		banco.insert(3, 5043, "OFF", 3, banco.getRandomDate(2000, 2023), 2000.0, "993024", "Conta Corrente");
		banco.insert(7, 199, "OFF", 7, banco.getRandomDate(2000, 2023), 2500.0, "293232", "Conta Poupança");

		List<Conta> dados = banco.getAll();
		for (Conta dado : dados) {
			System.out.println(dado.idConta);
		}
	}
}
