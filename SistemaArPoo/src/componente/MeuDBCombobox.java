package componente;

import bd.BancoDados;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MeuDBCombobox extends JComboBox implements MeuComponente {

    private String sql;
    private boolean obrigatorio;
    private String dica;
    private List<Integer> pks;

    public MeuDBCombobox(String sql, String dica, boolean obrigatorio){
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        preencher(sql);
    }

    public void preencher(String sql){
        this.sql = sql;
        pks = new ArrayList<>();
        removeAllItems();
        pks.add(-1);
        addItem("Selecione...");
        List<String[]> dados = BancoDados.executaQuery(sql);
        for (String[] dado : dados) {
            pks.add(Integer.parseInt(dado[0]));
            addItem(dado[1]);
        }
    }

    @Override
    public boolean obrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean fgVazio() {
        return getSelectedIndex() <= 0;
    }

    @Override
    public String getDica() {
        return dica;
    }

    @Override
    public void limpar() {
        setSelectedIndex(0);
    }

    @Override
    public void habilitar(boolean status) {
        setEnabled(status);
    }

    public Integer getValor(){
        return pks.get(getSelectedIndex());
    }

    public void setValor(int pk){
        for (int i = 0; i < pks.size(); i++) {
            if (pks.get(i) == pk) {
                setSelectedIndex(i);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, getDica() + " não encontrado.");
    }
}
