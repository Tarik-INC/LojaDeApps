package br.ufla.dcc.ppoo.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecuperarSenha extends Tela {


    public RecuperarSenha() {
        super("Recuperar Senha", 300, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        construirTela();
        pack();
    }

    void construirTela() {

        JLabel lbLogin = new JLabel("Login");
        JTextField txtLogin = new JTextField(20);

        adicionarComponentes(lbLogin, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(txtLogin, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0,1,1,1);


        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancela = new JButton("Cancela");

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(1, 1, 30, 30));
        painel.add(btnCancela);
        painel.add(btnConfirmar);

        adicionarComponentes(painel, GridBagConstraints.CENTER, GridBagConstraints.NONE, 4, 1, 2, 1);

        btnCancela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                dispose();
            }
        });
    }
}