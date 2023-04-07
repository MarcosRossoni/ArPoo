package tela;

import componente.MeuCampoTexto;
import componente.MeuDBCombobox;
import dao.CidadeDAO;
import dao.EstadoDAO;
import pojo.Cidade;

public class TelaCadastroCidade extends TelaCadastro {

    public Cidade cidade = new Cidade();
    public CidadeDAO cidadeDAO = new CidadeDAO(cidade);
    private MeuCampoTexto campoId = new MeuCampoTexto(10, "Código", true );
    private MeuCampoTexto campoNome = new MeuCampoTexto(50, "Nome", true );
    private MeuCampoTexto campoAtivo = new MeuCampoTexto(1, "Ativo", true );
    private MeuDBCombobox campoEstado = new MeuDBCombobox(EstadoDAO.SQL_COMBOBOX, "Estado", true);

    public TelaCadastroCidade() {
        super("Cadastro Cidade");
        adicionaComponente(1, 1, 1, 1, campoId);
        adicionaComponente(2, 1, 1, 1, campoNome);
        adicionaComponente(3, 1, 1, 1, campoAtivo);
        adicionaComponente(4, 1, 1, 1, campoEstado);
        pack();
        habilitaComponentes(false);
    }

    public void setPersistencia(){
        cidade.setId(Integer.parseInt(campoId.getText()));
        cidade.setNome(campoNome.getText());
        cidade.setAtivo(campoAtivo.getText());
        cidade.getEstado().setId(campoEstado.getValor());
    }

    @Override
    public void incluirBD(){
        setPersistencia();
        cidadeDAO.inserir();
    }

    @Override
    public void alterarBD(){
        setPersistencia();
        cidadeDAO.alterar();
    }

    @Override
    public void excluirBD(){
        cidadeDAO.excluir();
        super.excluirBD();
    }

    @Override
    public void consultar() {
        super.consultar();
        new TelaConsulta(this,
                "Consulta de Cidade",
                new String[] {"Codigo", "Nome", "Estado", "Ativo"},
                CidadeDAO.SQL_PESQUISAR);
    }

    @Override
    public void preencherDados(int pk){
        cidade.setId(pk);
        cidadeDAO.consultar();
        campoId.setText(String.valueOf(cidade.getId()));
        campoNome.setText(cidade.getNome());
        campoAtivo.setText(cidade.getAtivo());
        campoEstado.setValor(cidade.getEstado().getId());
        super.preencherDados(pk);
    }
}
