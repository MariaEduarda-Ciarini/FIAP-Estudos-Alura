package br.com.fiap.mercado.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteDeConexao {
    
    public static void main(String[] args) {
        
        try {
            // Define o Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Interface JDBC cuja implementação DriveManager abra uma conexão com a url definida para acesso ao banco
            Connection conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RMXXXXXX",
                    "XXXXXX");
            
            System.out.println("Conectou!");
            
            // Fecha Conexão
            conexao.close();
            
            //Tratamento de erro de conexão
            
        } catch (SQLException e) {
            System.err.println("Não foi possível conectar no ORACLE FIAP");
            e.printStackTrace();
            
            // Tratamento de erro quando não encontrado o Driver do Oracle
        } catch (ClassNotFoundException e) {
            System.err.println("O Driver JDBC não foi encontrado!");
            e.printStackTrace();
        }
    }
}
