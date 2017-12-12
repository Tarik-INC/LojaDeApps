package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.users.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class TelaListarApp extends Tela {

    private Usuario usuario;

    public TelaListarApp(Usuario usuario) {
        super("Lista Apps", 300, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.usuario = usuario;
        construirTela();
        pack();
    }

    @Override
    void construirTela() {

        JLabel lbInstrucao = new JLabel("Selecione um aplicativo para realizar alguma ação:");
        JButton btnVisualizar = new JButton("Visualizar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");
        JButton btnSair = new JButton("Sair");
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 4, 30, 30));
        painelBotoes.add(btnVisualizar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnSair);

        adicionarComponentes(lbInstrucao, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3,0,1,1);

        JPanel painelRotulo = new JPanel();
        JLabel lbNome = new JLabel("Nome |");
        JLabel lbDescricao = new JLabel("Descricao |");
        JLabel lbPalavraChave = new JLabel("Palavras-Chave |");
        JLabel lbNota = new JLabel("Nota");
        painelRotulo.add(lbNome);
        painelRotulo.add(lbDescricao);
        painelRotulo.add(lbPalavraChave);
        painelRotulo.add(lbNota);
        adicionarComponentes(painelRotulo, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1, 0, 1, 1);

        DefaultListModel listModel = new DefaultListModel();

        usuario.sortAplicativos();
        JList<Aplicativo> list = new JList<Aplicativo>(listModel);
        for (int i = 0; i < usuario.getAplicativos().size(); ++i) {
            listModel.addElement(usuario.getApp(i));
        }
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(300, 300));
        adicionarComponentes(listScroller, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2, 0, 1, 1);

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = list.getSelectedIndex();

                if (index == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Nenhum aplicativo selecionado!", "ERRO", JOptionPane.WARNING_MESSAGE);
                } else {
                    TelaVisualizarApp tv = new TelaVisualizarApp(usuario.getNome(), usuario.getAplicativo(index), TelaListarApp.this);
                    tv.setVisible(true);
                    setVisible(false);
                }
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();

                if (index == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Nenhum aplicativo selecionado!", "ERRO", JOptionPane.WARNING_MESSAGE);
                } else {
                    new TelaEditarApp(usuario.getAplicativo(index), TelaListarApp.this).setVisible(true);
                    setVisible(false);
                }
            }
        });


        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();

                if (index == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Nenhum aplicativo selecionado!", "ERRO", JOptionPane.WARNING_MESSAGE);
                } else {
                    int reply = JOptionPane.showConfirmDialog(null, "Tem certeza que quer deletar?",
                            "Deletar Aplicativo", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        usuario.deleteAplicativo(index);
                    }
                }
            }
        });


    }
}
