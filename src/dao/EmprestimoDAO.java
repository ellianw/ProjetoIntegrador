package dao;

import entities.Emprestimo;
import entities.Objeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmprestimoDAO {
    Connection conn;
    ObjetoDAO objdao;
    PessoaDAO pesdao;

    public EmprestimoDAO(Connection conn) {
        this.conn = conn;
        objdao = new ObjetoDAO(conn);
        pesdao = new PessoaDAO(conn);
    }

    public Emprestimo buscaObjetoAtivo(Objeto emprestimo){
        String sql = "SELECT CODIGO, COD_OBJETO, COD_PESSOA, DATA_EMP, ESTADO FROM EMPRESTIMO WHERE COD_OBJETO = ? AND ESTADO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,emprestimo.getCODIGO());
            stmt.setString(2,"ATIVO");
            ResultSet rs = stmt.executeQuery();
            if (rs.isBeforeFirst()){
                return new Emprestimo(rs.getInt("CODIGO"),objdao.buscaCodigo(rs.getInt("COD_OBJETO")),pesdao.buscaCodigo(rs.getInt("COD_PESSOA")),rs.getString("DATA_EMP"),rs.getString("ESTADO"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Emprestimo();
    }

    public void salvar(Emprestimo emp) {
        String sql = "INSERT INTO EMPRESTIMO (COD_OBJETO, COD_PESSOA, DATA_EMP, ESTADO) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,emp.getOBJETO().getCODIGO());
            stmt.setInt(2,emp.getPESSOA().getCODIGO());
            stmt.setString(3,emp.getDATA());
            stmt.setString(4,emp.getESTADO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(Emprestimo emp){
        String sql = "UPDATE EMPRESTIMO SET COD_OBJETO = ?, COD_PESSOA = ?, DATA_EMP = ?, ESTADO = ? WHERE CODIGO = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,emp.getOBJETO().getCODIGO());
            stmt.setInt(2,emp.getPESSOA().getCODIGO());
            stmt.setString(3,emp.getDATA());
            stmt.setString(4,emp.getESTADO());
            stmt.setInt(5,emp.getCODIGO());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Emprestimo> buscaOrdenadaObjeto(){
        ArrayList<Emprestimo> arr = new ArrayList<>();
        String sql = "SELECT * FROM EMPRESTIMO ORDER BY COD_OBJETO";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                arr.add(new Emprestimo(rs.getInt("CODIGO"),objdao.buscaCodigo(rs.getInt("COD_OBJETO")),pesdao.buscaCodigo(rs.getInt("COD_PESSOA")),rs.getString("DATA_EMP"),rs.getString("ESTADO")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arr;
    }

    public ArrayList<Emprestimo> buscaOrdenadaPessoa(){
        ArrayList<Emprestimo> arr = new ArrayList<>();
        String sql = "SELECT * FROM EMPRESTIMO ORDER BY COD_PESSOA";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                arr.add(new Emprestimo(rs.getInt("CODIGO"),objdao.buscaCodigo(rs.getInt("COD_OBJETO")),pesdao.buscaCodigo(rs.getInt("COD_PESSOA")),rs.getString("DATA"),rs.getString("ESTADO")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arr;
    }

    public ArrayList<Emprestimo> buscaOrdenadaSituacao(){
        ArrayList<Emprestimo> arr = new ArrayList<>();
        String sql = "SELECT * FROM EMPRESTIMO ORDER BY ESTADO";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                arr.add(new Emprestimo(rs.getInt("CODIGO"),objdao.buscaCodigo(rs.getInt("COD_OBJETO")),pesdao.buscaCodigo(rs.getInt("COD_PESSOA")),rs.getString("DATA"),rs.getString("ESTADO")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arr;
    }

    public ArrayList<Emprestimo> buscaData(String data){
        ArrayList<Emprestimo> arr = new ArrayList<>();
        String sql = "SELECT * FROM EMPRESTIMO WHERE DATA_EMP <= ? ORDER BY ESTADO";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,data);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                arr.add(new Emprestimo(rs.getInt("CODIGO"),objdao.buscaCodigo(rs.getInt("COD_OBJETO")),pesdao.buscaCodigo(rs.getInt("COD_PESSOA")),rs.getString("DATA_EMP"),rs.getString("ESTADO")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arr;
    }
}
