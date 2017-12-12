package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TelaEditarApp extends Tela {

    private Aplicativo app;
    private TelaListarApp source;

    public TelaEditarApp(Aplicativo app, TelaListarApp source) {
        super("Editar Aplicativo", 300, 300);
        this.app = app;
        this.source = source;
        construirTela();
        pack();
    }

    @Override
    void construirTela() {
        JButton btnMudarNome = new JButton("Mudar Nome");
        JButton btnMudarDescricao = new JButton("Mudar Descrição");
        JButton btnMudarPalavrasChave = new JButton("Mudar Palavras-Chave");
        JButton btnVoltar = new JButton("Voltar");
        adicionarComponentes(btnMudarNome, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(btnMudarDescricao, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1,0,1,1);
        adicionarComponentes(btnMudarPalavrasChave, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2,0,1,1);
        adicionarComponentes(btnVoltar, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3,0,1,1);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                source.setVisible(true);
                setVisible(false);
                dispose();
            }
        });

        btnMudarNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novoNome = JOptionPane.showInputDialog(null,"Novo nome","Mudar nome",
                        JOptionPane.QUESTION_MESSAGE);
                app.setNome(novoNome);
            }
        });

        btnMudarDescricao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaMudarDescricao mudarDescricao = new TelaMudarDescricao(app);
                mudarDescricao.setVisible(true);
            }
        });

        btnMudarPalavrasChave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaMudarPalavraChave mudarPalavraChave = new TelaMudarPalavraChave(app);
                mudarPalavraChave.setVisible(true);
            }
        });

    }
}
