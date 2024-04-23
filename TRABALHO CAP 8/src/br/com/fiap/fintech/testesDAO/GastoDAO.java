package br.com.fiap.fintech.testesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.semperderconexao.DatabaseConnection;

public class GastoDAO {
	private Connection conexao;

	public GastoDAO() {
		conexao = DatabaseConnection.getConexao();
	}

	public class Gasto {
		public int idGasto;
		public String tipoGasto;
		public int idConta;
		public double valorGasto;
	}

	public List<Gasto> getAll() {
		List<Gasto> resultados = new ArrayList<Gasto>();
		String sql = "SELECT * FROM T_ECOSIMP_GASTO";
		try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			if (!rs.isBeforeFirst()) {
				System.out.println("Não há dados para exibir.");
			} else {
				while (rs.next()) {
					Gasto gasto = this.new Gasto();
					gasto.idGasto = rs.getInt("ID_GASTO");
					gasto.tipoGasto = rs.getString("TIPO_GASTO");
					gasto.idConta = rs.getInt("ID_CONTA");
					gasto.valorGasto = rs.getDouble("VALOR_GASTO");
					resultados.add(gasto);
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar a consulta: " + e.getMessage());
		}
		return resultados;
	}

	public void insert(int idGasto, String tipoGasto, int idConta, double valorGasto) {
		String sql = "INSERT INTO T_ECOSIMP_GASTO (ID_GASTO, TIPO_GASTO, ID_CONTA, VALOR_GASTO) VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
			pstmt.setInt(1, idGasto);
			pstmt.setString(2, tipoGasto);
			pstmt.setInt(3, idConta);
			pstmt.setDouble(4, valorGasto);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao inserir dados: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		GastoDAO banco = new GastoDAO();

		banco.insert(19, "Sextar", 19, 300);
		banco.insert(10, "Transporte", 10, 30.0);
		banco.insert(40, "Baile", 20, 450);
		banco.insert(3, "Educação", 3, 200.0);
		banco.insert(7, "Saúde", 7, 150.0);

		List<Gasto> dados = banco.getAll();
		for (Gasto dado : dados) {
			System.out.println(dado.tipoGasto);
			System.out.println(dado.valorGasto);
		}
	}
}
