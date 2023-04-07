package componente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MeuCampoTexto extends JTextField implements FocusListener, MeuComponente {
    private String dica;
    private boolean obrigatorio;
    public MeuCampoTexto(int colunas, String dica, boolean obrigatorio){
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        setColumns(colunas);
        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        setBackground(Color.GRAY);
    }

    @Override
    public void focusLost(FocusEvent e) {
        setBackground(Color.WHITE);
    }

    @Override
    public boolean obrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean fgVazio() {
        return getText().trim().isEmpty();
    }

    @Override
    public void limpar() {
        setText("");
    }

    @Override
    public void habilitar(boolean status) {
        setEnabled(status);
    }

    public String getDica(){
        return dica;
    }
}
