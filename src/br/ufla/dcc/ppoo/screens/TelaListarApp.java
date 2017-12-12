package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.users.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tela para listar e gerenciar os apps de um usuário.
 * Mostra para visualização:
 * - lista de apps com descrição resumida
 * Mostra como opções:
 * - visualizar
 * - editar
 * - remover
 * - sair
 * @author rafael, tarik, william
 */
public class TelaListarApp extends Tela {
    
    private final Tela source;
    private final Usuario usuario;
    private JLabel lbInstrucao;
    private JButton btnVisualizar ;
    private JButton btnEditar;
    private JButton btnRemover;
    private JButton btnSair;
    private JPanel painelBotoes;
    private JPanel painelRotulo;
    private JLabel lbNome;
    private JLabel lbDescricao;
    //private JLabel lbPalavraChave;
    private JLabel lbNota;
    private DefaultListModel listModel;
    private JList<Aplicativo> list;
    private JScrollPane listScroller;

    public TelaListarApp(Tela source, Usuario usuario) {
        super("Meus Apps", 300, 300);
        this.usuario = usuario;
        this.source = source;
        construirTela();
        pack();
    }

    @Override
    void construirTela() {

        lbInstrucao = new JLabel("Selecione um aplicativo para realizar alguma ação:");
        btnVisualizar = new JButton("Visualizar");
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");
        btnSair = new JButton("Sair");
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 4, 30, 30));
        painelBotoes.add(btnVisualizar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnSair);

        adicionarComponentes(lbInstrucao, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3,0,1,1);

        painelRotulo = new JPanel();
        lbNome = new JLabel("Nome |");
        lbDescricao = new JLabel("Descricao |");
        //lbPalavraChave = new JLabel("Palavras-Chave |");
        lbNota = new JLabel("Nota");
        painelRotulo.add(lbNome);
        painelRotulo.add(lbDescricao);
        //painelRotulo.add(lbPalavraChave);
        painelRotulo.add(lbNota);
        adicionarComponentes(painelRotulo, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1, 0, 1, 1);

        DefaultListModel listModel = new DefaultListModel();

        usuario.sortAplicativos();
        list = new JList<Aplicativo>(listModel);
        for (Aplicativo app : usuario.getAplicativos()) {
            listModel.addElement( linhaFormatada(app.getNome(), app.getDescricaoFormatada(), app.getNotaFormatada()) );
        }
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(300, 300));
        adicionarComponentes(listScroller, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2, 0, 1, 1);

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                source.setVisible(true);
                dispose();
            }
        });

        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = list.getSelectedIndex();

                if (index == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Nenhum aplicativo selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
                            "Nenhum aplicativo selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
                            "Nenhum aplicativo selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    int reply = JOptionPane.showConfirmDialog(null, "Tem certeza que quer deletar?",
                            "Deletar Aplicativo", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        usuario.removeAplicativo(index);
                    }
                }
            }
        });
    }
    
    
    private String formataStringTamanho(String nome) {
        final int TAMANHO_MAX_COLUNA = 20;
        String out = "";
        
        if (nome.length() > TAMANHO_MAX_COLUNA - 3) {
            out += nome.substring(0, TAMANHO_MAX_COLUNA - 3) + "...";
        }
        else {
            out += nome;
            for (int i = 0; i < TAMANHO_MAX_COLUNA - nome.length(); i++) out += " ";
        }
        
        return out;
    }
    
    private String linhaFormatada(String nome, String descricao, String nota) {
        return String.format("%s  |  %s  |  %s", 
            formataStringTamanho(nome),
            formataStringTamanho(descricao),
            formataStringTamanho(nota)
        );
    }
    
}
