package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGerenciamento extends Tela{

    private TelaInicial source;

    public TelaGerenciamento(TelaInicial source) {
        super("Gerenciamento", 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        construirTela();
        this.source = source;
    }

    @Override
    void construirTela() {

        JButton btnCadastrarApp = new JButton("Cadastrar App");
        JButton btnListarApps = new JButton("Listar Apps");
        JButton btnBuscarApp = new JButton("Buscar App");
        JButton btnSair = new JButton("Sair");

        adicionarComponentes(btnCadastrarApp, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(btnListarApps, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1,0,1,1);
        adicionarComponentes(btnBuscarApp, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2, 0, 1, 1);
        adicionarComponentes(btnSair, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3, 0, 1, 1);

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                source.setVisible(true);
            }
        });

        btnCadastrarApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastrarApp().setVisible(true);
            }
        });

        btnListarApps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaListarApp().setVisible(true);
            }
        });
    }
}
