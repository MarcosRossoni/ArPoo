package tela;

import componente.MeuCampoTexto;
import dao.EstadoDAO;
import pojo.Estado;

import javax.swing.*;

public class TelaCadastroEstado extends TelaCadastro {

    public Estado estado = new Estado();
    public EstadoDAO estadoDAO = new EstadoDAO(estado);
    public JLabel jlCodigo = new JLabel("Código");
    public JLabel jlName = new JLabel("Nome");
    public JLabel jlSigla = new JLabel("Sigla");
    public JLabel jlAtivo = new JLabel("Ativo");
    public MeuCampoTexto jtfCodigo = new MeuCampoTexto(10);
    public MeuCampoTexto jtfNome = new MeuCampoTexto(50);
    public MeuCampoTexto jtfSigla = new MeuCampoTexto(2);
    public MeuCampoTexto jtfAtivo = new MeuCampoTexto(1);

    public TelaCadastroEstado() {
        super("Cadastro Estado");
        adicionaComponente(1, 1, 1, 1, jlCodigo);
        adicionaComponente(1, 2, 1, 1, jtfCodigo);
        adicionaComponente(2, 1, 1, 1, jlName);
        adicionaComponente(2, 2, 1, 2, jtfNome);
        adicionaComponente(3, 1, 1, 1, jlSigla);
        adicionaComponente(3, 2, 1, 2, jtfSigla);
        adicionaComponente(4, 1, 1, 1, jlAtivo);
        adicionaComponente(4, 2, 1, 2, jtfAtivo);
        pack();
        habilitaComponentes(false);
    }

    public void setPersistencia(){
        estado.setId(Integer.parseInt(jtfCodigo.getText()));
        estado.setNome(jtfNome.getText());
        estado.setSigla(jtfSigla.getText());
        estado.setAtivo(jtfAtivo.getText());
    }

    @Override
    public void incluirBD(){
        setPersistencia();
        estadoDAO.inserir();
    }

    @Override
    public void alterarBD(){
        setPersistencia();
        estadoDAO.alterar();
    }

    @Override
    public void excluirBD(){
        estadoDAO.excluir();
        super.excluirBD();
    }

    @Override
    public void consultar() {
        super.consultar();
        new TelaConsulta(this,
                "Consulta de Estado",
                new String[] {"Codigo", "Nome", "Sigla", "Ativo"},
                EstadoDAO.SQL_PESQUISAR);
    }

    @Override
    public void preencherDados(int pk){
        estado.setId(pk);
        estadoDAO.consultar();
        jtfCodigo.setText(String.valueOf(estado.getId()));
        jtfNome.setText(estado.getNome());
        jtfSigla.setText(estado.getSigla());
        jtfAtivo.setText(estado.getAtivo());
        super.preencherDados(pk);
    }
}
