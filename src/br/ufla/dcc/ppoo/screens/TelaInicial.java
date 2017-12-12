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

        adicionarComponentes(btnLogin, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(btnCadastro, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1,0,1,1);
        adicionarComponentes(btnSair, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2,0,1,1);
        
        
        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaCadastro(TelaInicial.this).setVisible(true);
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaLogin(TelaInicial.this).setVisible(true);
            }
        });
        
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int closeOption = JOptionPane.showConfirmDialog(null, 
                    "Deseja mesmo sair?", "Sair", 
                    JOptionPane.WARNING_MESSAGE
                );
                
                if (closeOption == JOptionPane.OK_OPTION) {
                    try {
                        Gerenciador.salvarDados();
                        System.exit(0);
                    } catch (IOException ex) {

                    }
                }
            }
        });
    }

}
