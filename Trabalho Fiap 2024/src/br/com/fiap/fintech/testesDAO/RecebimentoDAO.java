package br.com.fiap.fintech.testesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.semperderconexao.DatabaseConnection;

public class RecebimentoDAO {
	private Connection conexao;

	public RecebimentoDAO() {
		conexao = DatabaseConnection.getConexao();
	}

	public class Recebimento {
		public int idRecebimento;
		public String tipoRecebimento;
		public int idConta;
		public double valorRecebimento;

	}

	public List<Recebimento> getAll() {
		List<Recebimento> resultados = new ArrayList<Recebimento>();
		String sql = "SELECT * FROM T_ECOSIMP_RECEBIMENTO";
		try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			if (!rs.isBeforeFirst()) {
				System.out.println("Não há dados para exibir.");
			} else {
				while (rs.next()) {
					Recebimento recebimento = this.new Recebimento();
					recebimento.idRecebimento = rs.getInt("ID_RECEBIMENTO");
					recebimento.tipoRecebimento = rs.getString("TIPO_RECEBIMENTO");
					recebimento.idConta = rs.getInt("ID_CONTA");
					recebimento.valorRecebimento = rs.getDouble("VALOR_RECEBIMENTO");
					resultados.add(recebimento);
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar a consulta: " + e.getMessage());
		}
		return resultados;
	}

	public void insert(int idRecebimento, String tipoRecebimento, int idConta, double valorRecebimento) {
		String sql = "INSERT INTO T_ECOSIMP_RECEBIMENTO (ID_RECEBIMENTO, TIPO_RECEBIMENTO, ID_CONTA, VALOR_RECEBIMENTO) VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
			pstmt.setInt(1, idRecebimento);
			pstmt.setString(2, tipoRecebimento);
			pstmt.setInt(3, idConta);
			pstmt.setDouble(4, valorRecebimento);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao inserir dados: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		RecebimentoDAO banco = new RecebimentoDAO();

		banco.insert(19, "Mesada", 19, 2000);
		banco.insert(10, "Bônus", 10, 500);
		banco.insert(20, "Investimento", 20, 10000);
		banco.insert(3, "Venda", 3, 1200);
		banco.insert(7, "Herança", 7, 990000);

		List<Recebimento> dados = banco.getAll();
		for (Recebimento dado : dados) {
			System.out.println(dado.tipoRecebimento);
			System.out.println(dado.valorRecebimento);
		}
	}
}
