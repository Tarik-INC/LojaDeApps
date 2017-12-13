package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMudarDescricao extends Tela {

    private Aplicativo app;

    public TelaMudarDescricao(Aplicativo app) {
        super("Mudar Descrição", 300, 300);
        this.app = app;
        construirTela();
        pack();
    }

    @Override
    void construirTela() {
        JLabel lbNovaDescricao = new JLabel("Nova Descrição:");
        JTextArea txtNovaDescricao = new JTextArea(10, 30);
        JPanel painelBotoes = new JPanel();
        JButton btnConfirmar = new JButton("OK");
        JButton btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        adicionarComponentes(lbNovaDescricao, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0, 0, 1, 1);
        adicionarComponentes(txtNovaDescricao, GridBagConstraints.WEST, GridBagConstraints.BOTH, 1, 0, 1, 1);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2, 0, 1, 1);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.setDescricao(txtNovaDescricao.getText());
                setVisible(false);
                dispose();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });


    }
}
