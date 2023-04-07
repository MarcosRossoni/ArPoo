package dao;

import bd.BancoDados;
import pojo.Fornecedor;

import javax.swing.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class FornecedorDAO {

    private final String SQL_INCLUIR = "INSERT INTO FORNECEDOR VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_ALTERAR = "UPDATE FORNECEDOR SET NOME = ?, CNPJ = ?, ENDERECO = ?, BAIRRO = ?, CEP = ?, " +
            "ATIVO = ?, IDCIDADE = ? WHERE ID = ?";
    private final String SQL_EXCLUIR = "DELETE FROM FORNECEDOR WHERE ID = ?";
    private final String SQL_CONSULTAR = "SELECT * FROM FORNECEDOR WHERE ID = ?";
    public static final String SQL_PESQUISAR = "SELECT FORNECEDOR.ID, FORNECEDOR.NOME, FORNECEDOR.CNPJ, FORNECEDOR.ENDERECO, " +
            "FORNECEDOR.BAIRRO, FORNECEDOR.CEP, FORNECEDOR.CADASTRO, FORNECEDOR.ATIVO, CIDADE.NOME " +
            "FROM FORNECEDOR, CIDADE WHERE FORNECEDOR.IDCIDADE = CIDADE.ID ORDER BY FORNECEDOR.NOME";

    private Fornecedor fornecedor;

    public FornecedorDAO (Fornecedor fornecedor){
        this.fornecedor = fornecedor;
    }

    public boolean inserir() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_INCLUIR);
            ps.setInt(1, fornecedor.getId());
            ps.setString(2, fornecedor.getNome());
            ps.setString(3, fornecedor.getCnpj());
            ps.setString(4, fornecedor.getEndereco());
            ps.setString(5, fornecedor.getBairro());
            ps.setString(6, fornecedor.getCep());
            ps.setDate(7, Date.valueOf(fornecedor.getCadastro()));
            ps.setString(8, fornecedor.getAtivo());
            ps.setInt(9, fornecedor.getCidade().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel incluir o fornecedor.");
            return false;
        }
    }

    public boolean alterar() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_ALTERAR);
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEndereco());
            ps.setString(4, fornecedor.getBairro());
            ps.setString(5, fornecedor.getCep());
            ps.setString(6, fornecedor.getAtivo());
            ps.setInt(7, fornecedor.getCidade().getId());
            ps.setInt(8, fornecedor.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar o fornecedor.");
            return false;
        }
    }

    public boolean excluir() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_EXCLUIR);
            ps.setInt(1, fornecedor.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar o fornecedor.");
            return false;
        }
    }

    public boolean consultar() {
        try {
            PreparedStatement ps = BancoDados.getConnection().prepareStatement(SQL_CONSULTAR);
            ps.setInt(1, fornecedor.getId());
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                fornecedor.setNome(result.getString("NOME"));
                fornecedor.setCnpj(result.getString("CNPJ"));
                fornecedor.setEndereco(result.getString("ENDERECO"));
                fornecedor.setBairro(result.getString("BAIRRO"));
                fornecedor.setCep(result.getString("CEP"));
                fornecedor.setCadastro(LocalDate.parse(result.getString("CADASTRO")));
                fornecedor.setAtivo(result.getString("ATIVO"));
                fornecedor.getCidade().setId(Integer.parseInt(result.getString("IDCIDADE")));
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
