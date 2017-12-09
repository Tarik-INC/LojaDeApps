/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.users.Cadastro;
import br.ufla.dcc.ppoo.users.GerenciadorCadastro;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TelaCadastro extends Tela {

    private GerenciadorCadastro gCadastro;

    private GridBagConstraints gbc;
    private GridBagLayout gbl;

    public TelaCadastro() {
        super("Cadastrar Usuário", 300, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        gCadastro = new GerenciadorCadastro();

        construirTela();
        pack();
    }


    void construirTela() {

        JButton btnSalvar = new JButton("Salvar");
        JButton btnSair = new JButton("Sair");

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 1, 30, 30));
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnSair);

        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.NONE, 4, 1, 2, 1);

        JLabel rotuloNome = new JLabel("Nome ");
        adicionarComponentes(rotuloNome, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 0, 1, 1);

        JTextField caixaTextoNome = new JTextField(20);
        adicionarComponentes(caixaTextoNome, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 2, 1, 3, 1);

        JLabel rotuloLogin = new JLabel("Login ");
        adicionarComponentes(rotuloLogin, GridBagConstraints.WEST, GridBagConstraints.NONE, 1, 0, 1, 1);

        JTextField caixaTextoLogin = new JTextField(20);
        adicionarComponentes(caixaTextoLogin, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 1, 1, 3, 1);

        JLabel rotuloSenha = new JLabel("Senha ");
        adicionarComponentes(rotuloSenha, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 0, 1, 1);

        JTextField caixaTextoSenha = new JTextField(6);
        adicionarComponentes(caixaTextoSenha, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 1, 1, 1);

        JLabel rotuloSenhaConfirmar = new JLabel("Confirmar senha ");
        adicionarComponentes(rotuloSenhaConfirmar, GridBagConstraints.CENTER, GridBagConstraints.NONE, 3, 2, 1, 1);

        JTextField caixaTextoSenhaConfirmar = new JTextField(6);
        adicionarComponentes(caixaTextoSenhaConfirmar, GridBagConstraints.CENTER, GridBagConstraints.NONE, 3, 3, 1, 1);

        /*   JPanel painelSalvar = new JPanel();
        painelSalvar.setLayout(new GridLayout(1,4));
        painelSalvar.add(rotuloSenha);
        painelSalvar.add(caixaTextoSenha);
        painelSalvar.add(rotuloSenhaConfirmar);
        painelSalvar.add(caixaTextoSenhaConfirmar);
         */
        btnSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String login = caixaTextoLogin.getText().trim();
                String nome = caixaTextoNome.getText().trim();
                String senha = caixaTextoSenha.getText().trim();
                String confirmar = caixaTextoSenhaConfirmar.getText().trim();

                //Podemos criar exceçoes customizadas para esse caso e do confimar senha(nao feito)
                if (login.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Senha ou email inválidos!",
                            "ERRO", JOptionPane.WARNING_MESSAGE);

                } else if (senha.length() <= 4) {

                    JOptionPane.showMessageDialog(null, "Senhas devem ter mais de 4 caracteres",
                            "ERRO", JOptionPane.INFORMATION_MESSAGE);
                } else if (!confirmar.equals(senha)) {
                    
                    JOptionPane.showMessageDialog(null, "Senha de confirmação incorreta", 
                            "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    gCadastro.AdicionarCadastro(new Cadastro(nome,login,senha));
                    JOptionPane.showMessageDialog(null,
                            "Cadastro OK!", "Parabéns", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                dispose();
                try {
                    gCadastro.SalvarCadastrosArq();
                } catch (IOException ex) {
                   ex.getMessage();
                }
            }
        });
    }
}
