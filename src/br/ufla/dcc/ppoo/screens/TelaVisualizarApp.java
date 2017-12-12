package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.miscellaneous.Comentario;
import br.ufla.dcc.ppoo.starRater.StarRater;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TelaVisualizarApp extends Tela {

    private String nomeUsuario;
    private Aplicativo app;
    private TelaListarApp source;

    public TelaVisualizarApp(String nomeUsuario, Aplicativo app, TelaListarApp source) {
        super("Visualizar Aplicativo", 250, 400);
        this.app = app;
        this.nomeUsuario = nomeUsuario;
        this.source = source;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        construirTela();
        pack();

    }

    @Override
    void construirTela() {

        JLabel lbNomeApp = new JLabel("Login: " + nomeUsuario + "   |    " + app.getNome());
        adicionarComponentes(lbNomeApp, GridBagConstraints.WEST, GridBagConstraints.NONE, 0, 0, 1, 1);

        JPanel painelAvaliacao = new JPanel();
        painelAvaliacao.setLayout(new GridLayout(1, 2, 20, 20));

        JLabel lbQuantAvaliacao = new JLabel(Double.toString(0.0));
        StarRater starRater = new StarRater(5, app.getNota(), 0);
        starRater.addStarListener(
                new StarRater.StarListener() {

                    public void handleSelection(int selection) {
                        app.novaAvaliacao(selection);
                        lbQuantAvaliacao.setText(Integer.toString(selection));
                    }
                });

        painelAvaliacao.add(starRater);
        painelAvaliacao.add(lbQuantAvaliacao);
        adicionarComponentes(painelAvaliacao, GridBagConstraints.WEST, GridBagConstraints.NONE, 1, 0, 1, 2);

        JLabel lbTituloDescricao = new JLabel("Descrição: ");
        lbTituloDescricao.setFont(new Font("Arial", Font.BOLD, 16));
        adicionarComponentes(lbTituloDescricao, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 0, 1, 1);

        JLabel lbDescicao = new JLabel(app.getDescricao());
        adicionarComponentes(lbDescicao, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 0, 1, 1);

        JLabel lbTituloPalavraChave = new JLabel("Palavras-Chave: ");
        lbTituloPalavraChave.setFont(new Font("Arial", Font.BOLD, 16));
        adicionarComponentes(lbTituloPalavraChave, GridBagConstraints.WEST, GridBagConstraints.NONE, 5, 0, 1, 1);

        JLabel lbPalavrasChaves = new JLabel(app.getPalavrasChaveString());
        adicionarComponentes(lbPalavrasChaves, GridBagConstraints.WEST, GridBagConstraints.NONE, 6, 0, 1, 1);

        JLabel lbTituloComentarios = new JLabel("Comentários: ");
        lbTituloComentarios.setFont(new Font("Arial", Font.BOLD, 16));
        adicionarComponentes(lbTituloComentarios, GridBagConstraints.WEST, GridBagConstraints.NONE, 7, 0, 1, 1);

        //
        DefaultListModel listModel = new DefaultListModel();
        JList<String> list = new JList<String>(listModel);
        for (int i = 0; i < app.getComentariosSize(); ++i) {
            listModel.addElement(app.getComentario(i));
        }

        list.setEnabled(false);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 100));
        adicionarComponentes(listScroller, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 8, 0, 1, 1);
        //

        JTextArea textComentarios = new JTextArea(8, 20);
        adicionarComponentes(textComentarios, GridBagConstraints.WEST, GridBagConstraints.BOTH, 9, 0, 1, 1);

        JButton btnComentar = new JButton("Comentar");
        JButton btnSair = new JButton("Sair");

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 2, 20, 40));
        painelBotoes.add(btnComentar);
        painelBotoes.add(btnSair);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER,GridBagConstraints.NONE , 10, 0, 1, 1);

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                source.setVisible(true);
            }
        });


        btnComentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textComentarios.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Comentário vazio!",
                            "ERRO", JOptionPane.WARNING_MESSAGE);
                } else {
                    Comentario comentario = new Comentario(textComentarios.getText(), nomeUsuario);
                    app.addComentario(comentario);
                    JOptionPane.showMessageDialog(null,
                            "Comentário cadastrado com sucesso!", "Cadastro Completo", JOptionPane.INFORMATION_MESSAGE);
                    btnSair.doClick();
                }
            }
        });

    }

}
