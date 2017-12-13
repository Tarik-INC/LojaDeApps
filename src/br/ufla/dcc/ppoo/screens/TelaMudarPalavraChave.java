package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class TelaMudarPalavraChave extends Tela {

    private Aplicativo app;

    public TelaMudarPalavraChave(Aplicativo app) {
        super("Mudar Palavras-Chave", 300, 300);
        this.app = app;
        construirTela();
        pack();
    }

    @Override
    void construirTela() {
        JLabel lbNovaPalavra = new JLabel("Novas Palavras-Chave:");
        JTextField txtNovaPalavra = new JTextField(20);
        JPanel painelBotoes = new JPanel();
        JButton btnConfirmar = new JButton("OK");
        JButton btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        adicionarComponentes(lbNovaPalavra, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0, 0, 1, 1);
        adicionarComponentes(txtNovaPalavra, GridBagConstraints.WEST, GridBagConstraints.BOTH, 1, 0, 1, 1);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2, 0, 1, 1);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<String> palavrasChave = Arrays.asList(txtNovaPalavra.getText().split(";"));
                if (palavrasChave.size() < 2) {
                    JOptionPane.showMessageDialog(null,
                            "Menos de duas palavras-chave!", "ERRO", JOptionPane.WARNING_MESSAGE);
                } else {
                    app.setPalavrasChave(palavrasChave);
                    setVisible(false);
                    dispose();
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });


    }
}
