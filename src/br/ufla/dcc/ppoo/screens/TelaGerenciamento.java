package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.users.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TelaGerenciamento extends Tela{

    private TelaInicial source;
    private Usuario usuario;

    public TelaGerenciamento(TelaInicial source, Usuario usuario) {
        super("Gerenciamento", 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        construirTela();
        this.source = source;
        this.usuario = usuario;
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
                new TelaCadastrarApp(usuario).setVisible(true);
            }
        });

        btnListarApps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaListarApp(usuario).setVisible(true);
            }
        });

    }
}
