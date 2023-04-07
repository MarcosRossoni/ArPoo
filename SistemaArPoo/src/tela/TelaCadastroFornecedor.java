package tela;

import componente.MeuCampoTexto;
import componente.MeuDBCombobox;
import dao.CidadeDAO;
import dao.FornecedorDAO;
import pojo.Fornecedor;

import java.time.LocalDate;

public class TelaCadastroFornecedor extends TelaCadastro{

    public Fornecedor fornecedor = new Fornecedor();
    public FornecedorDAO fornecedorDAO = new FornecedorDAO(fornecedor);
    private MeuCampoTexto campoId = new MeuCampoTexto(10, "Código", true );
    private MeuCampoTexto campoNome = new MeuCampoTexto(50, "Nome", true );
    private MeuCampoTexto campoCnpj = new MeuCampoTexto(14, "CNPJ", true );
    private MeuCampoTexto campoEndereco = new MeuCampoTexto(50, "Endereço", true );
    private MeuCampoTexto campoBairro = new MeuCampoTexto(50, "Bairro", true );
    private MeuCampoTexto campoCep = new MeuCampoTexto(8, "CEP", true );
    private MeuCampoTexto campoAtivo = new MeuCampoTexto(1, "Ativo", true );
    private MeuDBCombobox campoCidade = new MeuDBCombobox(CidadeDAO.SQL_COMBOBOX, "Cidade", true);

    public TelaCadastroFornecedor() {
        super("Cadastro Fornecedor");
        adicionaComponente(1, 1, 1, 1, campoId);
        adicionaComponente(2, 1, 1, 1, campoNome);
        adicionaComponente(4, 1, 1, 1, campoCnpj);
        adicionaComponente(5, 1, 1, 1, campoEndereco);
        adicionaComponente(6, 1, 1, 1, campoBairro);
        adicionaComponente(7, 1, 1, 1, campoCep);
        adicionaComponente(3, 1, 1, 1, campoAtivo);
        adicionaComponente(8, 1, 1, 1, campoCidade);
        pack();
        habilitaComponentes(false);
    }

    public void setPersistencia(){
        fornecedor.setId(Integer.parseInt(campoId.getText()));
        fornecedor.setNome(campoNome.getText());
        fornecedor.setCnpj(campoCnpj.getText());
        fornecedor.setEndereco(campoEndereco.getText());
        fornecedor.setBairro(campoBairro.getText());
        fornecedor.setCep(campoCep.getText());
        fornecedor.setAtivo(campoAtivo.getText());
        fornecedor.getCidade().setId(campoCidade.getValor());
        fornecedor.setCadastro(LocalDate.now());
    }

    @Override
    public void incluirBD(){
        setPersistencia();
        fornecedorDAO.inserir();
    }

    @Override
    public void alterarBD(){
        setPersistencia();
        fornecedorDAO.alterar();
    }

    @Override
    public void excluirBD(){
        fornecedorDAO.excluir();
        super.excluirBD();
    }

    @Override
    public void consultar() {
        super.consultar();
        new TelaConsulta(this,
                "Consulta de Fornecedor",
                new String[] {"Codigo", "Nome", "CNPJ", "Endereço", "Bairro", "CEP", "Data Cadastro", "Ativo", "Cidade"},
                FornecedorDAO.SQL_PESQUISAR);
    }

    @Override
    public void preencherDados(int pk){
        fornecedor.setId(pk);
        fornecedorDAO.consultar();
        campoId.setText(String.valueOf(fornecedor.getId()));
        campoNome.setText(fornecedor.getNome());
        campoCnpj.setText(fornecedor.getCnpj());
        campoEndereco.setText(fornecedor.getEndereco());
        campoBairro.setText(fornecedor.getBairro());
        campoCep.setText(fornecedor.getCep());
        campoAtivo.setText(fornecedor.getAtivo());
        campoCidade.setValor(fornecedor.getCidade().getId());
        super.preencherDados(pk);
    }


}
