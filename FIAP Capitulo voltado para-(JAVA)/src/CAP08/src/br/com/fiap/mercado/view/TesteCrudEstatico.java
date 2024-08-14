package br.com.fiap.mercado.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteCrudEstatico {

	public static void main(String[] args) {
		
		try {
			// Define o Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
 // Interface JDBC cuja implementação DriveManager abra uma conexão com a url definida pelo banco
			
			Connection conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", 
                    "RMXXXXXX",
                    "XXXXXX");
			
			System.out.println("Conectou!");
			
			Statement stmt = conexao.createStatement();
			
					stmt.executeUpdate(
						    "INSERT INTO T_PRODUTO (" +
						    "CD_PRODUTO, " +
						    "NM_PRODUTO, " +
						    "VL_PRODUTO, " +
						    "DT_VALIDADE" +
						    ") VALUES (" +
						    "SEQ_PRODUTO.NEXTVAL, " +
						    "'UVA', " +
						    "11.99, " +
						    "TO_DATE('28/03/2024', 'DD/MM/YYYY')" +
						    ")");

			
			String sqlUpdate = "UPDATE T_PRODUTO SET VL_PRODUTO = 12.50 WHERE CD_PRODUTO = 1";
			stmt.executeUpdate(sqlUpdate);
			
			ResultSet result = stmt.executeQuery("SELECT * FROM T_PRODUTO");
			
			// Percorre todos os registros encontrados
			
			while (result.next()) {
				
				// Recupera os valores de cada coluna e imprime no console
				
				System.out.println( result.getInt("cd_produto")+ " " +
									result.getString("nm_produto") + " "+
									result.getDouble("vl_produto") + " " +
									result.getDate("dt_validade") 	);
			}
			String sqlDelete = "DELETE FROM T_PRODUTO WHERE CD_PRODUTO = 1";
			stmt.executeUpdate(sqlDelete);
			
			// Fecha conexão
			conexao.close();
			
			// Tratamento de erro de conexão
            
        } catch (SQLException e) {
            System.err.println("Não foi possível conectar no ORACLE FIAP");
            e.printStackTrace();
			
        } catch (ClassNotFoundException e) {
            System.err.println("O Driver JDBC não foi encontrado!");
            e.printStackTrace();
        }
	}
}