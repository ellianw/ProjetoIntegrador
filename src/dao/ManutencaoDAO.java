package dao;

import entities.Manutencao;
import entities.Objeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManutencaoDAO {
    Connection conn;
    ObjetoDAO objdao;

    public ManutencaoDAO(Connection conn) {
        this.conn = conn;
        this.objdao = new ObjetoDAO(conn);
    }

    public Manutencao buscaObjeto(Objeto objeto){
        String sql = "SELECT CODIGO, OBJETO, ATIVO FROM MANUTENCAO WHERE OBJETO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,objeto.getCODIGO());
            ResultSet rs = stmt.executeQuery();
            if (rs.isBeforeFirst()){
                return new Manutencao(rs.getInt("CODIGO"),objdao.buscaCodigo(rs.getInt("OBJETO")),Boolean.parseBoolean(rs.getString("ATIVO")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Manutencao();
    }

    public void salvar(Manutencao manutencao){
        String sql = "INSERT INTO MANUTENCAO (OBJETO,ATIVO) VALUES (?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,manutencao.getOBJETO().getCODIGO());
            stmt.setString(2,String.valueOf(manutencao.isATIVO()).toUpperCase());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarManutencao(Manutencao manutencao){
        String sql = "UPDATE MANUTENCAO SET ATIVO = ? WHERE CODIGO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,String.valueOf(manutencao.isATIVO()).toUpperCase());
            stmt.setInt(2,manutencao.getCODIGO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluirManutencao(Manutencao manutencao){
        String sql = "DELETE FROM MANUTENCAO WHERE CODIGO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,manutencao.getCODIGO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
