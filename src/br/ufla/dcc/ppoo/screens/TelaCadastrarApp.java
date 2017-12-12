package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TelaCadastrarApp extends Tela{

    public TelaCadastrarApp() {
        super("Cadastrar App", 300, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        construirTela();
        pack();
    }

    @Override
    void construirTela() {

        JLabel lbNome = new JLabel("Nome");
        JLabel lbDescricao = new JLabel("Descrição");
        JLabel lbPalavrasChave = new JLabel("Palavras-Chave (Mínimo 2, separadas por ;)");
        JTextField txtNome = new JTextField(20);
        JTextArea txtDescricao = new JTextArea(10, 30);
        JTextField txtPalavrasChave = new JTextField(20);
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 1, 30, 30));
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        adicionarComponentes(lbNome, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(txtNome, GridBagConstraints.WEST, GridBagConstraints.BOTH, 1,0,2,1);
        adicionarComponentes(lbDescricao, GridBagConstraints.WEST, GridBagConstraints.BOTH, 2,0,1,1);
        adicionarComponentes(txtDescricao, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3,0,2,1);
        adicionarComponentes(lbPalavrasChave, GridBagConstraints.WEST, GridBagConstraints.BOTH, 4,0,1,1);
        adicionarComponentes(txtPalavrasChave, GridBagConstraints.WEST, GridBagConstraints.BOTH, 5,0,2,1);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 6,0,2,1);



        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aplicativo aplicativo = new Aplicativo(txtNome.getText(), txtDescricao.getText(),
                        Arrays.asList(txtPalavrasChave.getText().split(";")));
            }
        });

    }
}