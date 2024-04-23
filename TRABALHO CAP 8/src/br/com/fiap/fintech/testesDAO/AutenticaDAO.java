package br.com.fiap.fintech.testesDAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.semperderconexao.DatabaseConnection;

public class AutenticaDAO {
	private Connection conexao;

	public AutenticaDAO() {
		conexao = DatabaseConnection.getConexao();
	}

	public class Autentica {
		public int idAutentica;
		public String nomeUsuario;
		public String senha;
	}

	public List<Autentica> getAll() {
		List<Autentica> resultados = new ArrayList<Autentica>();
		String sql = "SELECT * FROM T_ECOSIMP_AUTENTICA";
		try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			if (!rs.isBeforeFirst()) {
				System.out.println("Não há dados para exibir.");
			} else {
				while (rs.next()) {
					Autentica autentica = this.new Autentica();
					autentica.idAutentica = rs.getInt("ID_AUTENTICA");
					autentica.nomeUsuario = rs.getString("NOME_USUARIO");
					autentica.senha = rs.getString("SENHA");
					resultados.add(autentica);
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar a consulta: " + e.getMessage());
		}
		return resultados;
	}

	public void insert(int idAutentica, String nomeUsuario, String senha) {
		String sql = "INSERT INTO T_ECOSIMP_AUTENTICA (ID_AUTENTICA, NOME_USUARIO, SENHA) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
			pstmt.setInt(1, idAutentica);
			pstmt.setString(2, nomeUsuario);
			pstmt.setString(3, senha);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao inserir dados: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		AutenticaDAO banco = new AutenticaDAO();

		banco.insert(19, "duda", "dudinha123");
		banco.insert(10, "corinthians", "vaicurinthians2");
		banco.insert(20, "yurialberto", "horrivelemcampomaisbonito23");
		banco.insert(3, "cassio", "senha423243");
		banco.insert(7, "fiel", "fieltorcedor02392");

		List<Autentica> dados = banco.getAll();
		for (Autentica dado : dados) {
			System.out.println(dado.idAutentica);
			System.out.println(dado.nomeUsuario);
			System.out.println(dado.senha);
		}
	}
}
