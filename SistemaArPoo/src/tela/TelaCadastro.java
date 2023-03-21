package tela;

import componente.MeuComponente;
import enumeration.EstadoTela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaCadastro extends JInternalFrame implements ActionListener {

    public List<MeuComponente> componentes = new ArrayList<>();
    public JPanel jpBotoes = new JPanel();
    public JPanel jpComponentes = new JPanel();
    public JButton jbIncluir = new JButton("Incluir");
    public JButton jbAlterar = new JButton("Alterar");
    public JButton jbExcluir = new JButton("Excluir");
    public JButton jbConsultar = new JButton("Consultar");
    public JButton jbConfirmar = new JButton("Confirmar");
    public JButton jbCancelar = new JButton("Cancelar");
    public EstadoTela fgEstadoTela = EstadoTela.PADRAO;
    public boolean fgTemDados = false;

    public TelaCadastro(String dsTitulo) {
        super(dsTitulo, true, true, true);
        setSize(400, 300);
        getContentPane().add(BorderLayout.CENTER, jpComponentes);
        jpComponentes.setLayout(new GridBagLayout());
        criarBotoes();
        actionListnerBotoes();
        pack();
        setVisible(true);
        habilitaBotoes();
    }

    public void adicionaComponente(int linha, int coluna, int linhas, int colunas, JComponent componente){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = coluna;
        gbc.gridy = linha;
        gbc.gridheight = linhas;
        gbc.gridwidth = colunas;
        gbc.anchor = GridBagConstraints.WEST;
        jpComponentes.add(componente, gbc);
        if (componente instanceof MeuComponente){
            componentes.add((MeuComponente) componente);
        }
    }

    public void habilitaComponentes(boolean status){
        for (MeuComponente componente : componentes) {
            componente.habilitar(status);
        }
    }

    public void limpaComponentes(){
        for (MeuComponente componente : componentes) {
            componente.limpar();
        }
    }

    public void habilitaBotoes() {
        jbIncluir.setEnabled(fgEstadoTela.equals(EstadoTela.PADRAO));
        jbAlterar.setEnabled(fgEstadoTela.equals(EstadoTela.PADRAO) && fgTemDados);
        jbExcluir.setEnabled(fgEstadoTela.equals(EstadoTela.PADRAO) && fgTemDados);
        jbConsultar.setEnabled(fgEstadoTela.equals(EstadoTela.PADRAO));
        jbConfirmar.setEnabled(!fgEstadoTela.equals(EstadoTela.PADRAO));
        jbCancelar.setEnabled(!fgEstadoTela.equals(EstadoTela.PADRAO));
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(jbIncluir)){
            incluir();
        } else if (ae.getSource().equals(jbAlterar)){
            alterar();
        } else if (ae.getSource().equals(jbIncluir)){
            excluir();
        } else if (ae.getSource().equals(jbConsultar)){
            consultar();
        } else if (ae.getSource().equals(jbConfirmar)) {
            confirmar();
        } else if (ae.getSource().equals(jbCancelar)) {
            cancelar();
        }
        habilitaBotoes();
    }

    public void incluir(){
        fgEstadoTela = EstadoTela.INCLUINDO;
    }

    public void alterar(){
        fgEstadoTela = EstadoTela.ALTERANDO;
    }

    public void excluir(){
        fgEstadoTela = EstadoTela.EXCLUINDO;
    }

    public void consultar(){
        fgEstadoTela = EstadoTela.CONSULTANDO;
    }

    public void confirmar(){
        fgEstadoTela = EstadoTela.PADRAO;
    }

    public void cancelar(){
        fgEstadoTela = EstadoTela.PADRAO;
    }

    public void criarBotoes(){
        getContentPane().add(BorderLayout.PAGE_END, jpBotoes);
        jpBotoes.setLayout(new GridLayout(1, 6));
        jpBotoes.add(jbIncluir);
        jpBotoes.add(jbAlterar);
        jpBotoes.add(jbExcluir);
        jpBotoes.add(jbConsultar);
        jpBotoes.add(jbConfirmar);
        jpBotoes.add(jbCancelar);
    }

    public void actionListnerBotoes(){
        jbIncluir.addActionListener(this);
        jbAlterar.addActionListener(this);
        jbExcluir.addActionListener(this);
        jbConsultar.addActionListener(this);
        jbConfirmar.addActionListener(this);
        jbCancelar.addActionListener(this);
    }
}
