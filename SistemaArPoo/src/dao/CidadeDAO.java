package dao;

import bd.BancoDados;
import pojo.Cidade;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CidadeDAO {

    private final String SQL_INCLUIR = "INSERT INTO CIDADE VALUES (?, ?, ?, ?)";
    private final String SQL_ALTERAR = "UPDATE CIDADE SET NOME = ?, ATIVO = ?, IDESTADO = ? WHERE ID = ?";
    private final String SQL_EXCLUIR = "DELETE FROM CIDADE WHERE ID = ?";
    private final String SQL_CONSULTAR = "SELECT * FROM CIDADE WHERE ID = ?";
    public static final String SQL_PESQUISAR = "SELECT CIDADE.ID, CIDADE.NOME, ESTADO.SIGLA, CIDADE.ATIVO " +
            "FROM CIDADE, ESTADO WHERE CIDADE.IDESTADO = ESTADO.ID ORDER BY CIDADE.NOME";
    public static final String SQL_COMBOBOX = "SELECT ID, NOME FROM CIDADE ORDER BY NOME";

    private Cidade cidade;

    public CidadeDAO(Cidade cidade) {
        this.cidade = cidade;
    }

    public boolean inserir() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_INCLUIR);
            ps.setInt(1, cidade.getId());
            ps.setString(2, cidade.getNome());
            ps.setString(3, cidade.getAtivo());
            ps.setInt(4, cidade.getEstado().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel incluir a cidade.");
            return false;
        }
    }

    public boolean alterar() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_ALTERAR);
            ps.setString(1, cidade.getNome());
            ps.setString(2, cidade.getAtivo());
            ps.setInt(3, cidade.getEstado().getId());
            ps.setInt(4, cidade.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar a cidade.");
            return false;
        }
    }

    public boolean excluir() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_EXCLUIR);
            ps.setInt(1, cidade.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar a cidade.");
            return false;
        }
    }

    public boolean consultar() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_CONSULTAR);
            ps.setInt(1, cidade.getId());
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                cidade.setNome(result.getString("NOME"));
                cidade.setAtivo(result.getString("ATIVO"));
                cidade.getEstado().setId(Integer.parseInt(result.getString("IDESTADO")));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cidade não encontrada.");
                return false;
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel consultar a cidade.");
            return false;
        }
    }
}
