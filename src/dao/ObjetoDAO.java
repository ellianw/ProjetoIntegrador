package dao;

import entities.Objeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ObjetoDAO {
    Connection conn;
    Tipo_ObjDAO tipoDAO;

    public ObjetoDAO(Connection conn) {
        this.conn = conn;
        tipoDAO = new Tipo_ObjDAO(conn);
    }

    public Objeto buscaNome(String nomebusca){
        String sql = "SELECT CODIGO, NOME, COD_TIPO_OBJETO, SITUACAO FROM OBJETO WHERE NOME = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,nomebusca.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.isBeforeFirst()){
                return new Objeto(rs.getInt("CODIGO"),rs.getString("NOME"),tipoDAO.buscaCodigo(rs.getInt("COD_TIPO_OBJETO")),rs.getString("SITUACAO"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Objeto();
    }

    public void salvar(Objeto obj){
        String sql = "INSERT INTO OBJETO (NOME,COD_TIPO_OBJETO,SITUACAO) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNOME().toUpperCase());
            stmt.setInt(2,tipoDAO.getCodigo(obj.getTIPO()));
            stmt.setString(3,obj.getSITUACAO().toUpperCase());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean objetoExiste(String nomebusca){
        String sql = "SELECT CODIGO, NOME FROM OBJETO WHERE NOME = ?";
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

    public Objeto buscaCodigo(Integer codigo){
        String sql = "SELECT CODIGO, NOME, COD_TIPO_OBJETO, SITUACAO FROM OBJETO WHERE CODIGO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.isBeforeFirst()){
                return new Objeto(rs.getInt("CODIGO"),rs.getString("NOME"),tipoDAO.buscaCodigo(rs.getInt("COD_TIPO_OBJETO")),rs.getString("SITUACAO"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Objeto();
    }

    public void atualizarObjeto(Objeto obj){
        String sql = "UPDATE OBJETO SET NOME = ?, COD_TIPO_OBJETO = ?, SITUACAO = ? WHERE CODIGO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNOME().toUpperCase());
            stmt.setInt(2,obj.getTIPO().getCODIGO());
            stmt.setString(3,obj.getSITUACAO());
            stmt.setInt(4,obj.getCODIGO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluirObjeto(Objeto objeto){
        String sql = "DELETE FROM OBJETO WHERE CODIGO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,objeto.getCODIGO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Objeto> buscaPorTipo(int tipo){
        ArrayList<Objeto> arr = new ArrayList<>();
        String sql = "SELECT * FROM OBJETO WHERE COD_TIPO_OBJETO = ? ORDER BY NOME";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,tipo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                arr.add(new Objeto(rs.getInt("CODIGO"),rs.getString("NOME"),tipoDAO.buscaCodigo(rs.getInt("COD_TIPO_OBJETO")),rs.getString("SITUACAO")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arr;
    }

    public ArrayList<Objeto> buscaPorSituacao(){
        ArrayList<Objeto> arr = new ArrayList<>();
        String sql = "SELECT * FROM OBJETO ORDER BY SITUACAO, NOME";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                arr.add(new Objeto(rs.getInt("CODIGO"),rs.getString("NOME"),tipoDAO.buscaCodigo(rs.getInt("COD_TIPO_OBJETO")),rs.getString("SITUACAO")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arr;
    }

    public ArrayList<Objeto> buscaPorNome(){
        ArrayList<Objeto> arr = new ArrayList<>();
        String sql = "SELECT * FROM OBJETO ORDER BY NOME";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                arr.add(new Objeto(rs.getInt("CODIGO"),rs.getString("NOME"),tipoDAO.buscaCodigo(rs.getInt("COD_TIPO_OBJETO")),rs.getString("SITUACAO")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arr;
    }
}
