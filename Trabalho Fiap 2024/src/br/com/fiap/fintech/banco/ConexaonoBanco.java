package br.com.fiap.fintech.banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Calendar;

public class ConexaonoBanco {
	private Connection conexao;

	public ConexaonoBanco() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RMXXXXXX",
					"XXXXXX");
			System.out.println("Conectou!");
		} catch (SQLException e) {
			System.err.println("Não foi possível conectar no ORACLE FIAP");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("O Driver JDBC não foi encontrado!");
			e.printStackTrace();
		}
	}

	public class Cliente {
		public int idCliente;
		public String nome;
		public String cpf;
		public String email;
		public String telefone;
		public String endereco;
		public Date dtNasc;
		public String status;
	}

	public List<Cliente> getAll() {
		List<Cliente> resultados = new ArrayList<Cliente>();
		String sql = "SELECT * FROM T_ECOSIMP_CLIENTE";
		try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			if (!rs.isBeforeFirst()) {
				System.out.println("Não há dados para exibir.");
			} else {
				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.idCliente = rs.getInt("ID_CLIENTE");
					cliente.nome = rs.getString("NOME");
					cliente.cpf = rs.getString("CPF");
					cliente.email = rs.getString("EMAIL");
					cliente.telefone = rs.getString("TELEFONE");
					cliente.endereco = rs.getString("ENDERECO");
					cliente.dtNasc = rs.getDate("DT_NASC");
					cliente.status = rs.getString("STATUS");
					resultados.add(cliente);
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar a consulta: " + e.getMessage());
		}
		return resultados;
	}

	public void insert(int idCliente, String nome, String cpf, String email, String telefone, String endereco,
			Date dtNasc, String status) {
		String sql = "INSERT INTO T_ECOSIMP_CLIENTE (ID_CLIENTE, NOME, CPF, EMAIL, TELEFONE, ENDERECO, DT_NASC, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
			pstmt.setInt(1, idCliente);
			pstmt.setString(2, nome);
			pstmt.setString(3, cpf);
			pstmt.setString(4, email);
			pstmt.setString(5, telefone);
			pstmt.setString(6, endereco);
			pstmt.setDate(7, new java.sql.Date(dtNasc.getTime()));
			pstmt.setString(8, status);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao inserir dados: " + e.getMessage());
		}
	}

	public Date getRandomDate(int startYear, int endYear) {
		int day = ThreadLocalRandom.current().nextInt(1, 31);
		int month = ThreadLocalRandom.current().nextInt(1, 13);
		int year = ThreadLocalRandom.current().nextInt(startYear, endYear);

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);

		return new Date(calendar.getTimeInMillis());
	}

	public void closeConnection() {
		try {
			if (conexao != null) {
				conexao.close();
				System.out.println("Conexão fechada!");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao fechar a conexão: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		ConexaonoBanco banco = new ConexaonoBanco();

		banco.insert(19, "duda", "29144732813", "duda@example.com", "15922122963", "Rua Zacarias Fontan de Melo",
				banco.getRandomDate(2000, 2023), "off");
		banco.insert(10, "corinthians", "88039644879", "corinthians@example.com", "17928088328", "Neo Quimica Arena",
				banco.getRandomDate(2000, 2023), "off");
		banco.insert(20, "yurialberto", "54181614824", "yurialberto@example.com", "11931257536", "Rua Osório Horácio",
				banco.getRandomDate(2000, 2023), "on");
		banco.insert(3, "cassio", "17810424890", "cassio@example.com", "16931242033", "Rua Alfredo Eduardo Lopez",
				banco.getRandomDate(2000, 2023), "on");
		banco.insert(7, "fiel", "93821719834", "fiel@example.com", "13923884351", "Rua Estudante Clebson Damasceno",
				banco.getRandomDate(2000, 2023), "off");

		List<Cliente> dados = banco.getAll();
		for (Cliente dado : dados) {
			System.out.println(dado.nome);
			System.out.println(dado.email);
		}
		banco.closeConnection();
	}
}
