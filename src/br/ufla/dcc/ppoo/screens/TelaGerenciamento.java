package br.ufla.dcc.ppoo.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGerenciamento extends Tela{

    private final TelaInicial source;
    private JButton btnCadastrarApp;
    private JButton btnListarApps;
    private JButton btnBuscarApp;
    private JButton btnSair;

    public TelaGerenciamento(TelaInicial source) {
        super("Gerenciamento", 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.source = source;
        construirTela();
    }

    @Override
    void construirTela() {

        btnCadastrarApp = new JButton("Cadastrar App");
        btnListarApps = new JButton("Listar Apps");
        btnBuscarApp = new JButton("Buscar App");
        btnSair = new JButton("Sair");

        adicionarComponentes(btnCadastrarApp, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(btnListarApps, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1,0,1,1);
        adicionarComponentes(btnBuscarApp, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2, 0, 1, 1);
        adicionarComponentes(btnSair, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3, 0, 1, 1);

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
        
        btnBuscarApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Não fiz!", "Deu Ruim", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                source.setVisible(true);
            }
        });
    }
    
}
