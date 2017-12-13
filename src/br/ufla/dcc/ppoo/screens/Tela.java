package br.ufla.dcc.ppoo.screens;

import javax.swing.*;
import java.awt.*;

abstract class Tela extends JFrame{


    private GridBagConstraints gbc;
    private GridBagLayout gbl;

    public Tela(String nomeTela, int largura, int altura) {
        super(nomeTela);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(largura, altura);

        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);
        setLocationRelativeTo(null);
    }

    void adicionarComponentes(Component comp, int achor, int fill,
                              int linha, int coluna, int larg, int alt) {
        gbc.anchor = (int) achor;
        gbc.fill = fill;
        gbc.gridy = linha;
        gbc.gridx = coluna;
        gbc.gridwidth = larg;
        gbc.gridheight = alt;
        gbc.insets = new Insets(3, 3, 3, 3);
        gbl.setConstraints(comp, gbc);
        add(comp);
    }

    abstract void construirTela();

}
