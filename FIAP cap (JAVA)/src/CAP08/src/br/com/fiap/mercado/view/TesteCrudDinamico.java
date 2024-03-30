package br.com.fiap.mercado.view;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class TesteCrudDinamico {

    public static void main(String[] args) {
        try {
             Define o Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                    "RMXXXXXX",
                    "XXXXX");

            System.out.println("Conectou!");

            PreparedStatement pstmt = conexao.prepareStatement(
                    "INSERT INTO T_PRODUTO (CD_PRODUTO, "
                            + "NM_PRODUTO, "
                            + "VL_PRODUTO,"
                            + " DT_VALIDADE) "
                            + "VALUES ("
                            + "SEQ_PRODUTO.NEXTVAL, "
                            + "?, ?, ?)");

             pstmt.setString(1,"Maracuja");  Primeiro parâmetro (nome)
             pstmt.setFloat(2, 7);  Segundo parâmetro (valor)
             java.sql.Date data = new java.sql.Date(new java.util.Date().getTime());
             pstmt.setDate(3, data); Terceiro parâmetro (data de validade)
             pstmt.executeUpdate();

             pstmt = conexao.prepareStatement("UPDATE T_PRODUTO SET VL_PRODUTO = ? WHERE CD_PRODUTO = ?");
             pstmt.setFloat(1, 8);
             pstmt.setInt(2, 3);
             pstmt.executeUpdate();

             pstmt = conexao.prepareStatement("DELETE FROM T_PRODUTO WHERE CD_PRODUTO = ?");
             pstmt.setInt(1, 2);
             pstmt.executeUpdate();

            CallableStatement cs = conexao.prepareCall("{call SP_INSERT_PRODUTO(?, ?, ?)}");
            cs.setString(1, "Morango");
            java.sql.Date data1 = new java.sql.Date(new java.util.Date().getTime());
            cs.setDate(2, data1);
            cs.setFloat(3, 10);
            cs.executeUpdate();
            cs.executeUpdate();

            pstmt = conexao.prepareStatement("SELECT * FROM T_PRODUTO WHERE CD_PRODUTO = ?");
            pstmt.setInt(1, 3);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {

                 Recupera os valores de cada coluna e imprime no console

                System.out.println(result.getInt("cd_produto") + " " +
                        result.getString("nm_produto") + " " +
                        result.getDouble("vl_produto") + " " +
                        result.getDate("dt_validade"));
            }

            conexao.close();

             Tratamento de erro de conexão

        } catch (SQLException e) {
            System.err.println("Não foi possível conectar no ORACLE FIAP");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.err.println("O Driver JDBC não foi encontrado!");
            e.printStackTrace();
        }
    }
}
