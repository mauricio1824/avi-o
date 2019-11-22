/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Aviao;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoAviao {
    public static boolean inserir(Aviao objeto) {
        String sql = "INSERT INTO aviao (modelo, capacidade, volume, data_construcao) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getModelo());
            ps.setInt(2, objeto.getCapacidade());
            ps.setDouble(3, objeto.getVolume());
            ps.setDate(4, Date.valueOf(objeto.getData_construcao())); 
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static void main(String[] args) {
        Aviao objeto = new Aviao();
        objeto.setModelo("Brasil");
        objeto.setCapacidade(100);
        objeto.setVolume(3.2);
        objeto.setData_construcao(LocalDate.parse("11/12/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy"))); 
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    public static boolean alterar(Aviao objeto) {
        String sql = "UPDATE aviao SET modelo = ?, capacidade = ?, volume = ?, data_construcao = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getModelo());
            ps.setInt(2, objeto.getCapacidade());
            ps.setDouble(3, objeto.getVolume());
            ps.setDate(4, Date.valueOf(objeto.getData_construcao())); 
            ps.setInt(5, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      public static boolean excluir(Aviao objeto) {
        String sql = "DELETE FROM aviao WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      public static List<Aviao> consultar() {
        List<Aviao> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, modelo, capacidade, volume, data_construcao FROM aviao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aviao objeto = new Aviao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setModelo(rs.getString("modelo"));
                objeto.setCapacidade(rs.getInt("capacidade"));
                objeto.setVolume(rs.getDouble("volume"));
                objeto.setData_construcao(rs.getDate("data_construcao").toLocalDate());
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
    public static Aviao consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, modelo, capacidade, volume, data_construcao FROM aviao WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aviao objeto = new Aviao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setModelo(rs.getString("modelo"));
                objeto.setCapacidade(rs.getInt("capacidade"));
                objeto.setVolume(rs.getDouble("volume"));
                objeto.setData_construcao(rs.getDate("data_construcao").toLocalDate());
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
