package dao;

import entities.Pessoa;

import java.sql.*;

public class PessoaDAO {
    Connection conn = null;

    public PessoaDAO(Connection conn) {
        this.conn = conn;
    }

    public Pessoa buscaNome(String nomebusca){
        String sql = "SELECT CODIGO, NOME, FROM PESSOA WHERE NOME = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,nomebusca);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Codigo: "+rs.getInt("CODIGO")+" Nome:"+rs.getString("NOME"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Pessoa(nomebusca);
    }
}
