package br.com.fiap.fintech.testesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.semperderconexao.DatabaseConnection;

public class InvestimentoDAO {
	private Connection conexao;

	public InvestimentoDAO() {
		conexao = DatabaseConnection.getConexao();
	}

	public class Investimento {
		public int idInvestimento;
		public String nmInvestimento;
		public double txRetorno;
		public double vlMontante;
		public String dsRisco;
		public int idConta;
		public int idCliente;
	}

	public List<Investimento> getAll() {
		List<Investimento> resultados = new ArrayList<Investimento>();
		String sql = "SELECT * FROM T_ECOSIMP_INVESTIMENTO";
		try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			if (!rs.isBeforeFirst()) {
				System.out.println("Não há dados para exibir.");
			} else {
				while (rs.next()) {
					Investimento investimento = new Investimento();
					investimento.idInvestimento = rs.getInt("ID_INVESTIMENTO");
					investimento.nmInvestimento = rs.getString("NM_INVESTIMENTO");
					investimento.txRetorno = rs.getDouble("TX_RETORNO");
					investimento.vlMontante = rs.getDouble("VL_MONTANTE");
					investimento.dsRisco = rs.getString("DS_RISCO");
					investimento.idConta = rs.getInt("ID_CONTA");
					investimento.idCliente = rs.getInt("ID_CLIENTE");
					resultados.add(investimento);
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar a consulta: " + e.getMessage());
		}
		return resultados;
	}

	public void insert(int idInvestimento, String nmInvestimento, double txRetorno, double vlMontante, String dsRisco,
			int idConta, int idCliente) {
		String sql = "INSERT INTO T_ECOSIMP_INVESTIMENTO (ID_INVESTIMENTO, NM_INVESTIMENTO, TX_RETORNO, VL_MONTANTE, DS_RISCO, ID_CONTA, ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
			pstmt.setInt(1, idInvestimento);
			pstmt.setString(2, nmInvestimento);
			pstmt.setDouble(3, txRetorno);
			pstmt.setDouble(4, vlMontante);
			pstmt.setString(5, dsRisco);
			pstmt.setInt(6, idConta);
			pstmt.setInt(7, idCliente);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao inserir dados: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		InvestimentoDAO banco = new InvestimentoDAO();

		banco.insert(19, "Comprar Iphone 15 Pro Max", 5500, 15000, "Médio", 19, 19);
		banco.insert(10, "Bitcoin", 0.94, 100000, "Médio", 10, 10);
		banco.insert(20, "Bolsa de Valores", 2.4, 5600, "Baixo", 20, 20);
		banco.insert(45, "Carro", 1.3, 6500, "Médio", 45, 45);
		banco.insert(7, "Mansao Tommy Vercetti", 0.08, 10043, "Médio", 7, 7);

		List<Investimento> dados = banco.getAll();
		for (Investimento dado : dados) {
			System.out.println(dado.nmInvestimento);
			System.out.println(dado.vlMontante);
		}
	}
}
