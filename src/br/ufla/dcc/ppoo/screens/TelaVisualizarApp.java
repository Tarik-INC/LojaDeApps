package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.miscellaneous.Comentario;
import br.ufla.dcc.ppoo.miscellaneous.StarRater;
import br.ufla.dcc.ppoo.miscellaneous.StarRater.StarListener;
import br.ufla.dcc.ppoo.users.Usuario;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TelaVisualizarApp extends Tela {
    
    private Aplicativo app;
    private JLabel lbNomeApp;
    private JPanel painelAvaliacao;
    private JLabel lbQuantAvaliacao;
    private StarRater starRater;
    private JLabel lbTituloDescricao;
    private JLabel lbDescicao;
    private JLabel lbTituloPalavraChave;
    private JLabel lbPalavrasChaves;
    private JLabel lbTituloComentarios;
    private DefaultListModel listModel;
    private JList<String> list;
    private JScrollPane listScroller;
    private JTextArea textComentarios;
    private JButton btnComentar;
    private JButton btnSair;
    private JPanel painelBotoes;

    public TelaVisualizarApp(Tela source, Usuario usuario, Aplicativo app) {
        super("Visualizar Aplicativo", source, usuario, 250, 400); 
        this.app = app;
        construirTela();
        pack();
    }

    @Override
    public void construirTela() {
        
        lbNomeApp = new JLabel("Login: " + getUsuario().getNome() + "   |    " + app.getNome());

        painelAvaliacao = new JPanel();
        painelAvaliacao.setLayout(new GridLayout(1, 2, 20, 20));

        lbQuantAvaliacao = new JLabel(Double.toString(0.0));
        starRater = new StarRater(5, app.getNota(), 0);
        starRater.addStarListener(new StarListener() {
            @Override
            public void handleSelection(int selection) {
                app.novaAvaliacao(selection);
                lbQuantAvaliacao.setText(Integer.toString(selection));
            }
        });

        painelAvaliacao.add(starRater);
        painelAvaliacao.add(lbQuantAvaliacao);
        
        lbTituloDescricao = new JLabel("Descrição: ");
        lbTituloDescricao.setFont(new Font("Arial", Font.BOLD, 16));
        
        lbDescicao = new JLabel(app.getDescricao());
        
        lbTituloPalavraChave = new JLabel("Palavras-Chave: ");
        lbTituloPalavraChave.setFont(new Font("Arial", Font.BOLD, 16));
        
        lbPalavrasChaves = new JLabel(app.getPalavrasChaveString());
        
        lbTituloComentarios = new JLabel("Comentários: ");
        lbTituloComentarios.setFont(new Font("Arial", Font.BOLD, 16));
        
        listModel = new DefaultListModel();
        list = new JList(listModel);
        for (int i = 0; i < app.getComentariosSize(); ++i) {
            listModel.addElement(app.getComentario(i));
        }

        list.setEnabled(false);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 100));
        
        textComentarios = new JTextArea(8, 20);
        
        btnComentar = new JButton("Comentar");
        btnSair = new JButton("Sair");

        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 2, 20, 40));
        painelBotoes.add(btnComentar);
        painelBotoes.add(btnSair);
        
        
        adicionarComponentes(lbNomeApp, GridBagConstraints.WEST, GridBagConstraints.NONE, 0, 0, 1, 1);
        adicionarComponentes(painelAvaliacao, GridBagConstraints.WEST, GridBagConstraints.NONE, 1, 0, 1, 2);
        adicionarComponentes(lbTituloDescricao, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 0, 1, 1);
        adicionarComponentes(lbDescicao, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 0, 1, 1);
        adicionarComponentes(lbTituloPalavraChave, GridBagConstraints.WEST, GridBagConstraints.NONE, 5, 0, 1, 1);
        adicionarComponentes(lbPalavrasChaves, GridBagConstraints.WEST, GridBagConstraints.NONE, 6, 0, 1, 1);
        adicionarComponentes(lbTituloComentarios, GridBagConstraints.WEST, GridBagConstraints.NONE, 7, 0, 1, 1);
        adicionarComponentes(listScroller, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 8, 0, 1, 1);
        adicionarComponentes(textComentarios, GridBagConstraints.WEST, GridBagConstraints.BOTH, 9, 0, 1, 1);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER,GridBagConstraints.NONE , 10, 0, 1, 1);

        
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                setParentVisible(true);
            }
        });

        btnComentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textComentarios.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Comentário vazio!",
                            "ERRO", JOptionPane.WARNING_MESSAGE);
                } else {
                    Comentario comentario = new Comentario(textComentarios.getText(), getUsuario().getNome());
                    app.addComentario(comentario);
                    JOptionPane.showMessageDialog(null,
                            "Comentário cadastrado com sucesso!", "Cadastro Completo", JOptionPane.INFORMATION_MESSAGE);
                    btnSair.doClick();
                }
            }
        });

    }

    @Override
    public void acaoAoFechar() {
        btnSair.doClick();
    }

}