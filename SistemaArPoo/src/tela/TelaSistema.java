package tela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaSistema extends JFrame implements ActionListener {

    public static JDesktopPane jdp = new JDesktopPane();
    public JMenuBar jmb = new JMenuBar();
    public JMenu jmCadastros = new JMenu("Cadastros");
    public JMenuItem jmiEstado = new JMenuItem("Estado");
    public JMenuItem jmiCidade = new JMenuItem("Cidade");
    public JMenuItem jmiFornecedor = new JMenuItem("Fornecedor");

    public TelaSistema() {
        getContentPane().add(jdp);
        setTitle("Meu Sistema");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(jmb);
        jmb.add(jmCadastros);
        jmCadastros.add(jmiEstado);
        jmCadastros.add(jmiCidade);
        jmCadastros.add(jmiFornecedor);
        jmiEstado.addActionListener(this);
        jmiCidade.addActionListener(this);
        jmiFornecedor.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(jmiEstado)){
            TelaCadastroEstado telaCadastroEstado = new TelaCadastroEstado();
            jdp.add(telaCadastroEstado);
        } else if (ae.getSource().equals(jmiCidade)){
            TelaCadastroCidade telaCadastroCidade = new TelaCadastroCidade();
            jdp.add(telaCadastroCidade);
        } else if (ae.getSource().equals(jmiFornecedor)) {
            TelaCadastroFornecedor telaCadastroFornecedor = new TelaCadastroFornecedor();
            jdp.add(telaCadastroFornecedor);
        }
    }
}
