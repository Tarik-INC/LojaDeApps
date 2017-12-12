package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.management.Gerenciador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TelaInicial extends Tela {
    
    private JButton btnCadastro;
    private JButton btnLogin;
    private JButton btnSair;
    //private JButton btnRecuperarSenha;
    
    public TelaInicial() {
        super("Tela Inicial", 300, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        construirTela();
    }

    @Override
    void construirTela() {
        btnCadastro = new JButton("Novo Usuário", new ImageIcon(getClass().getResource("images/cadastro.png")));
        btnLogin = new JButton("Login", new ImageIcon(getClass().getResource("images/login.png")));
        btnSair = new JButton("Sair", new ImageIcon(getClass().getResource("images/sair.png")));
        //btnRecuperarSenha = new JButton("Recuperar Senha", new ImageIcon(getClass().getResource("images/recuperarSenha.png")));

        adicionarComponentes(btnLogin, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(btnCadastro, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1,0,1,1);
        adicionarComponentes(btnSair, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2,0,1,1);
        //adicionarComponentes(btnRecuperarSenha, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2,0,1,1);
        
        
        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastro().setVisible(true);
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaLogin(TelaInicial.this).setVisible(true);
            }
        });
        
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Gerenciador.salvarDados();
                    System.exit(0);
                } catch (IOException ex) {
                    
                }
            }
        });
        
        /*btnRecuperarSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaRecuperarSenha().setVisible(true);
            }
        });*/
        
    }

}
