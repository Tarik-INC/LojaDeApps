package br.ufla.dcc.ppoo.screens;

import javax.swing.*;
import java.awt.*;

/**
 * Protótipo das telas criadas pelo programa.
 * @author rafael, tarik, william
 */
abstract class Tela extends JFrame{

    private final GridBagConstraints gbc;
    private final GridBagLayout gbl;

    /**
     * Construtor da tela.
     * @param nomeTela Título da janela
     * @param largura Largura da janela
     * @param altura Altura da janela
     */
    public Tela(String nomeTela, int largura, int altura) {
        super(nomeTela);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        //setResizable(false);
        setSize(largura, altura);

        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);
        setLocationRelativeTo(null);
    }

    /**
     * Adiciona componentes na tela.
     * @param comp Componente em si
     * @param achor Âncora
     * @param fill Preenchimento
     * @param linha Linha
     * @param coluna Coluna
     * @param larg Largura
     * @param alt Altura
     */
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

    /**
     * Método para construir a tela.
     */
    abstract void construirTela();

}
