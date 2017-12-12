package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.users.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TelaCadastrarApp extends Tela{
    
    private Usuario usuario;
    private JLabel lbNome;
    private JLabel lbDescricao;
    private JLabel lbPalavrasChave;
    private JTextField txtNome;
    private JTextArea txtDescricao;
    private JTextField txtPalavrasChave;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JPanel painelBotoes;
    
    public TelaCadastrarApp(Usuario usuario) {
        super("Cadastrar App", 300, 300);
        this.usuario = usuario;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        construirTela();
        pack();
    }

    @Override
    void construirTela() {

        lbNome = new JLabel("Nome");
        lbDescricao = new JLabel("Descrição");
        lbPalavrasChave = new JLabel("Palavras-Chave (Mínimo 2, separadas por ;)");
        txtNome = new JTextField(20);
        txtDescricao = new JTextArea(10, 30);
        txtPalavrasChave = new JTextField(20);
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        painelBotoes = new JPanel();
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

                java.util.List<String> palavrasChave = Arrays.asList(txtPalavrasChave.getText().split(";"));
                if (palavrasChave.size() < 2) {
                    JOptionPane.showMessageDialog(null,
                            "Menos de duas palavras-chave!", "ERRO", JOptionPane.WARNING_MESSAGE);
                } else {

                    String[] texto = txtDescricao.getText().split("\n");
                    String descricao = "";
                    for (int i = 0; i < texto.length; ++i) {
                        descricao += texto[i] + System.lineSeparator();
                    }

                    Aplicativo aplicativo = new Aplicativo(txtNome.getText(), descricao, palavrasChave);
                    usuario.addApp(aplicativo);

                    JOptionPane.showMessageDialog(null,
                            "Aplicativo cadastrado com sucesso!", "Cadastro Completo", JOptionPane.INFORMATION_MESSAGE);

                    btnCancelar.doClick();
                }
            }
        });

    }
}
