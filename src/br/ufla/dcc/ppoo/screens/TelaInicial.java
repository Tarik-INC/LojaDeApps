package br.ufla.dcc.ppoo.screens;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class TelaInicial extends Tela {

    public TelaInicial() {
        super("Tela Inicial", 300, 300);

        construirTela();
    }

    @Override
    void construirTela() {
        JButton btnCadastro = new JButton("Novo Usuário", new ImageIcon(getClass().getResource("images/cadastro.png")));
        JButton btnLogin = new JButton("Login", new ImageIcon(getClass().getResource("images/login.png")));
        JButton btnRecuperarSenha = new JButton("Recuperar senha", new ImageIcon(getClass().getResource("images/recuperarSenha.png")));

        adicionarComponentes(btnLogin, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(btnCadastro, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1,0,1,1);
        //adicionarComponentes(btnRecuperarSenha, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2,0,1,1);
        //acrescentar para 2ª entrega


        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastro().setVisible(true);
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaLogin().setVisible(true);
            }
        });

        btnRecuperarSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaRecuperarSenha().setVisible(true);
            }
        });



    }



}
