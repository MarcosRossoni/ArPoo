package tela;

import bd.BancoDados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class TelaConsulta extends JInternalFrame implements MouseListener {
    private TelaCadastro telaChamadora;
    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable tabela = new JTable(dtm) {
        @Override
        public boolean isCellEditable(int linnha, int coluna){

            return false;
        }
    };

    private JScrollPane jsp = new JScrollPane(tabela);

    public TelaConsulta(TelaCadastro telaChamadora, String titulo, String[] colunas, String sql){
        super(titulo);
        this.telaChamadora = telaChamadora;
        tabela.getTableHeader().setReorderingAllowed(false);
        insereColunas(colunas);
        insereDados(sql);
        if (dtm.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Não existem dados cadastrados.");
            return;
        }
        getContentPane().add(jsp);
        pack();
        setVisible(true);
        TelaSistema.jdp.add(this);
        TelaSistema.jdp.moveToFront(this);
        tabela.addMouseListener(this);
    }

    public void insereColunas(String[] colunas){
        for (String coluna : colunas) {
            dtm.addColumn(coluna);
        }
    }

    public void insereDados(String sql){
        List<String[]> dados = BancoDados.executaQuery(sql);
        for (String[] dado : dados) {
            dtm.addRow(dado);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            telaChamadora.preencherDados(Integer.parseInt((String) dtm.getValueAt(tabela.getSelectedRow(),0)));
            dispose();
            TelaSistema.jdp.remove(this);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
