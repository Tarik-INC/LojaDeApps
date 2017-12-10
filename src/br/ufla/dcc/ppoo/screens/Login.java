package br.ufla.dcc.ppoo.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login extends Tela {

    private GridBagConstraints gbc;
    private GridBagLayout gbl;

    public Login() {
        super("Login", 300, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        construirTela();
        pack();
    }


    void construirTela() {

        JLabel lbLogin = new JLabel("Login");
        JLabel lbSenha = new JLabel("Senha");

        JTextField txtLogin = new JTextField(20);
        JTextField txtSenha = new JTextField(6);

        adicionarComponentes(lbLogin, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0, 0, 1, 1);
        adicionarComponentes(lbSenha, GridBagConstraints.WEST, GridBagConstraints.BOTH, 1, 0, 1, 1);

        adicionarComponentes(txtLogin, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0, 1, 1, 1);
        adicionarComponentes(txtSenha, GridBagConstraints.WEST, GridBagConstraints.BOTH, 1, 1, 1, 1);

        //

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
