package dao;

import bd.BancoDados;
import pojo.Estado;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EstadoDAO {

    private final String SQL_INCLUIR = "INSERT INTO ESTADO VALUES (?, ?, ?, ?)";
    private final String SQL_ALTERAR = "UPDATE ESTADO SET NOME = ?, SIGLA = ?, ATIVO = ? WHERE ID = ?";
    private final String SQL_EXCLUIR = "DELETE FROM ESTADO WHERE ID = ?";
    private final String SQL_CONSULTAR = "SELECT * FROM ESTADO WHERE ID = ?";
    public static final String SQL_PESQUISAR = "SELECT * FROM ESTADO ORDER BY ID";
    private Estado estado;

    public EstadoDAO(Estado estado){
        this.estado = estado;
    }

    public boolean inserir() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_INCLUIR);
            ps.setInt(1, estado.getId());
            ps.setString(2, estado.getNome());
            ps.setString(3, estado.getSigla());
            ps.setString(4, estado.getAtivo());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel incluir o estado.");
            return false;
        }
    }

    public boolean alterar() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_ALTERAR);
            ps.setString(1, estado.getNome());
            ps.setString(2, estado.getSigla());
            ps.setString(3, estado.getAtivo());
            ps.setInt(4, estado.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar o estado.");
            return false;
        }
    }

    public boolean excluir() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_EXCLUIR);
            ps.setInt(1, estado.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar o estado.");
            return false;
        }
    }

    public boolean consultar() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_CONSULTAR);
            ps.setInt(1, estado.getId());
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                estado.setNome(result.getString("NOME"));
                estado.setSigla(result.getString("SIGLA"));
                estado.setAtivo(result.getString("ATIVO"));
                return true;
            }
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar o estado.");
            return false;
        }
    }
}
