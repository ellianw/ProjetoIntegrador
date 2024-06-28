package dao;

import entities.Tipo_Obj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tipo_ObjDAO {
    Connection conn;

    public Tipo_ObjDAO(Connection conn) {
        this.conn = conn;
    }

    public Tipo_Obj buscaNome(String nomebusca){
        String sql = "SELECT CODIGO, NOME FROM TIPO_OBJETO WHERE NOME = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,nomebusca.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            //rs.next();
            if (rs.isBeforeFirst()){
                return new Tipo_Obj(rs.getInt("CODIGO"),rs.getString("NOME"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Tipo_Obj(nomebusca);
    }

    public Tipo_Obj buscaCodigo(Integer codigo){
        String sql = "SELECT CODIGO, NOME FROM TIPO_OBJETO WHERE CODIGO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.isBeforeFirst()){
                return new Tipo_Obj(rs.getInt("CODIGO"),rs.getString("NOME"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Tipo_Obj();
    }

    public void salvar(Tipo_Obj tipoObj){
        String sql = "INSERT INTO TIPO_OBJETO (NOME) VALUES (?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,tipoObj.getTIPO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean tipoInvalido(String nomebusca){
        String sql = "SELECT CODIGO, NOME FROM TIPO_OBJETO WHERE NOME = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,nomebusca.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.isBeforeFirst()){
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Integer getCodigo(Tipo_Obj tipo){
        String sql = "SELECT CODIGO, NOME FROM TIPO_OBJETO WHERE NOME = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,tipo.getTIPO());
            ResultSet rs = stmt.executeQuery();
            if (rs.isBeforeFirst()){
                return rs.getInt("CODIGO");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public Integer qtdPorTipo(Tipo_Obj tipo){
        String sql = "SELECT COUNT() FROM OBJETO WHERE COD_TIPO_OBJETO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,tipo.getCODIGO());
            ResultSet rs = stmt.executeQuery();
            if (rs.isBeforeFirst()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void atualizarTipo(Tipo_Obj tipoobj){
        String sql = "UPDATE TIPO_OBJETO SET NOME = ? WHERE CODIGO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,tipoobj.getTIPO().toUpperCase());
            stmt.setInt(2,tipoobj.getCODIGO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluirTipo(Tipo_Obj tipoObj){
        String sql = "DELETE FROM TIPO_OBJETO WHERE CODIGO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,tipoObj.getCODIGO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
