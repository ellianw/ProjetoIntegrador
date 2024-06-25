package dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import entities.Pessoa;

import java.sql.*;

public class PessoaDAO {
    Connection conn;

    public PessoaDAO(Connection conn) {
        this.conn = conn;
    }

    public Pessoa buscaNome(String nomebusca){
        String sql = "SELECT CODIGO, NOME FROM PESSOA WHERE NOME = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,nomebusca.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.isBeforeFirst()){
                return new Pessoa(rs.getInt("CODIGO"),rs.getString("NOME"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Pessoa(nomebusca);
    }

    public void salvar(Pessoa pessoa){
        String sql = "INSERT INTO PESSOA (NOME) VALUES (?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,pessoa.getNOME().toUpperCase());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean pessoaExiste(String nomebusca){
        String sql = "SELECT CODIGO, NOME FROM PESSOA WHERE NOME = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,nomebusca.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.isBeforeFirst()){
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void atualizarPessoa(Pessoa pessoa){
        if (!pessoaExiste(pessoa.getNOME())) {
            return;
        }
        String sql = "UPDATE PESSOA SET NOME = ? WHERE CODIGO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,pessoa.getNOME().toUpperCase());
            stmt.setInt(2,pessoa.getCODIGO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluirPessoa(Pessoa pessoa){
        String sql = "DELETE FROM PESSOA WHERE CODIGO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,pessoa.getCODIGO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
