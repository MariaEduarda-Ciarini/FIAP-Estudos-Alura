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

public class CartaoDAO {
	private Connection conexao;

	public CartaoDAO() {
		conexao = DatabaseConnection.getConexao();
	}

	public class Cartao {
		public int idCartao;
		public String numero;
		public String tipo;
		public String bandeira;
		public Date dtVencimento;
		public int cvv;
		public String status;
		public Date dtCriacao;
		public int idCliente;
	}

	public void insert(int idCartao, String numero, String tipo, String bandeira, Date dtVencimento, int cvv,
			String status, Date dtCriacao, int idCliente) {
		String sql = "INSERT INTO T_ECOSIMP_CARTAO (ID_CARTAO, NUMERO, TIPO, BANDEIRA, DT_VENCIMENTO, CVV, STATUS, DT_CRIACAO, ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
			pstmt.setInt(1, idCartao);
			pstmt.setString(2, numero);
			pstmt.setString(3, tipo);
			pstmt.setString(4, bandeira);
			pstmt.setDate(5, dtVencimento);
			pstmt.setInt(6, cvv);
			pstmt.setString(7, status);
			pstmt.setDate(8, dtCriacao);
			pstmt.setInt(9, idCliente);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao inserir dados: " + e.getMessage());
		}
	}

	public List<Cartao> getAll() {
		List<Cartao> resultados = new ArrayList<Cartao>();
		String sql = "SELECT * FROM T_ECOSIMP_CARTAO";
		try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			if (!rs.isBeforeFirst()) {
				System.out.println("Não há dados para exibir.");
			} else {
				while (rs.next()) {
					Cartao cartao = this.new Cartao();
					cartao.idCartao = rs.getInt("ID_CARTAO");
					cartao.numero = rs.getString("NUMERO");
					cartao.tipo = rs.getString("TIPO");
					cartao.bandeira = rs.getString("BANDEIRA");
					cartao.dtVencimento = rs.getDate("DT_VENCIMENTO");
					cartao.cvv = rs.getInt("CVV");
					cartao.status = rs.getString("STATUS");
					cartao.dtCriacao = rs.getDate("DT_CRIACAO");
					cartao.idCliente = rs.getInt("ID_CLIENTE");
					resultados.add(cartao);
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar a consulta: " + e.getMessage());
		}
		return resultados;
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
		CartaoDAO banco = new CartaoDAO();

		banco.insert(19, "1234567891", "Crédito", "Visa", banco.getRandomDate(2000, 2023), 213, "Ativo",
				new Date(System.currentTimeMillis()), 19);
		banco.insert(10, "2345678923456789", "Débito", "MasterCard", banco.getRandomDate(2000, 2023), 234, "Ativo",
				new Date(System.currentTimeMillis()), 10);
		banco.insert(20, "34567890345", "Crédito", "MasterCard", banco.getRandomDate(2000, 2023), 345, "Ativo",
				new Date(System.currentTimeMillis()), 20);
		banco.insert(3, "4567890145678901", "Débito", "Visa", banco.getRandomDate(2000, 2023), 456, "Ativo",
				new Date(System.currentTimeMillis()), 3);
		banco.insert(7, "5678901256789012", "Crédito", "MasterCard", banco.getRandomDate(2000, 2023), 567, "Ativo",
				new Date(System.currentTimeMillis()), 7);

		List<Cartao> dados = banco.getAll();
		for (Cartao dado : dados) {
			System.out.println(dado.idCartao);
			System.out.println(dado.bandeira);
		}
	}
}
