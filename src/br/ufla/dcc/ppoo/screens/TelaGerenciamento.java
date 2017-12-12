package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.users.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGerenciamento extends Tela {

    private final TelaInicial source;
    private final Usuario usuario;
    private JButton btnCadastrarApp;
    private JButton btnListarApps;
    private JButton btnBuscarApp;
    private JButton btnSair;

    public TelaGerenciamento(TelaInicial source, Usuario usuario) {
        super("Gerenciamento", 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        construirTela();
        this.source = source;
        this.usuario = usuario;
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
                new TelaCadastrarApp(usuario).setVisible(true);
            }
        });
        
        btnListarApps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaListarApp(usuario).setVisible(true);
            }
        });
        
        btnBuscarApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaBuscar().setVisible(true);
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
