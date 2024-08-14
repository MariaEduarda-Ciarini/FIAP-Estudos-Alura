// Em vez de abrir uma nova conexão para cada classe DAO, aqui uma classe para compartilhar a mesma conexão entre todas as classes DAO.

package br.com.fiap.semperderconexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection conexao;

    public static Connection getConexao() {
        if (conexao == null) {
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
        return conexao;
    }
}