package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;

import javax.swing.*;
import java.awt.*;

public class TelaListarApp extends Tela {

    public TelaListarApp() {
        super("Lista Apps", 300, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        construirTela();
        pack();

    }

    @Override
    void construirTela() {
        /*
        String[] colunas = {"Nome", "Descrição", "Palavras-Chave", "Nota", "Downloads"};
        Object[][] data = {
                {"Nome", "Descricao", "Teste", 5, 5}
        };
        */
        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };
        JTable tbTabela = new JTable(data, columnNames);
        adicionarComponentes(tbTabela, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 1, 1);
    }
}
