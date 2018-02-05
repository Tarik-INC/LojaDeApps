package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.modeling.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.ComentarioException;
import br.ufla.dcc.ppoo.modeling.Comentario;
import br.ufla.dcc.ppoo.thirdparty.StarRater;
import br.ufla.dcc.ppoo.thirdparty.StarRater.StarListener;
import br.ufla.dcc.ppoo.modeling.Usuario;
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
import javax.swing.ScrollPaneConstants;

/**
 * Tela para visualizar detalhes do app.
 * Mostra:
 * - Nome do app resumido (tipText mostra completo se não couber na tela)
 * - Nome do autor do app resumido (tipText mostra completo também)
 * - Nota média arredondada (tipText mostra valor com precisão de 4 casas)
 * - Última avaliação que o usuário fez (estrela colorida)
 * - Descrição completa
 * - Lista de palavras-chave
 * - Comentários
 * Pede como entrada (opcional):
 * - Avalição do usuário
 * - Comentário do usuário
 * @author rafael, tarik, william
 */
public class TelaVisualizarApp extends Tela {

    private Aplicativo app;
    private JLabel lbNomeApp;
    private JLabel lbAutorApp;
    private JLabel lbNotaMedia;
    private JPanel painelNome;
    private JLabel lbAvaliacao;
    private StarRater starRater;
    private JPanel painelAvaliacao;
    private JLabel lbTituloDescricao;
    private JTextArea txtDescicao;
    private JScrollPane painelDescricao;
    private JLabel lbTituloPalavraChave;
    private JTextArea txtPalavrasChave;
    private JScrollPane painelPalavrasChave;
    private JLabel lbTituloComentarios;
    private DefaultListModel listModel;
    private JList<String> list;
    private JScrollPane listComentarios;
    private JLabel lbTituloComentar;
    private JTextArea textComentar;
    private JScrollPane painelComentar;
    private JButton btnComentar;
    private JButton btnSair;
    private JPanel painelBotoes;

    public TelaVisualizarApp(Tela source, Usuario usuario, Aplicativo app) {
        super("Visualizar Aplicativo", source, usuario, 375, 600);
        this.app = app;
        construirTela();
    }

    @Override
    public void construirTela() {

        lbNomeApp = new JLabel( stringReduzida(app.getNome(), 35) );
        lbNomeApp.setToolTipText( app.getNome() );
        lbNomeApp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        
        lbAutorApp = new JLabel( "by " + stringReduzida(app.getAutor().getNome(), 25) );
        lbAutorApp.setToolTipText( app.getAutor().getNome() );
        
        lbNotaMedia = new JLabel("Média das avaliações: " + app.getNotaFormatada());
        lbNotaMedia.setToolTipText(String.format("Nota = %.4f", app.getNota()));

        painelNome = new JPanel();
        painelNome.setLayout(new GridLayout(3, 1));
        painelNome.add(lbNomeApp);
        painelNome.add(lbAutorApp);
        painelNome.add(lbNotaMedia);

        lbAvaliacao = new JLabel("Avaliar:");
        starRater = new StarRater(5, app.getNotaUsuario(getUsuario()), 0);
        starRater.addStarListener(new StarListener() {
            @Override
            public void handleSelection(int selection) {
                app.setNotaAvaliacao(getUsuario(), selection);
                JOptionPane.showMessageDialog(null, "Avaliação salva!", "Concluído", JOptionPane.INFORMATION_MESSAGE);

                //refresh
                dispose();
                new TelaVisualizarApp(getParentScreen(), getUsuario(), app).setVisible(true);

            }
        });
        
        if (app.getAutor().equals(getUsuario())) {
            starRater.setEnabled(false);
        }

        painelAvaliacao = new JPanel();
        painelAvaliacao.setLayout(new GridLayout(1, 2));
        painelAvaliacao.add(lbAvaliacao);
        painelAvaliacao.add(starRater);

        lbTituloDescricao = new JLabel("Descrição:");
        txtDescicao = new JTextArea(app.getDescricao(), 5, 30);
        txtDescicao.setLineWrap(true);
        txtDescicao.setEditable(false);        
        
        painelDescricao = new JScrollPane(txtDescicao);
        painelDescricao.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        lbTituloPalavraChave = new JLabel("Palavras-Chave:");
        txtPalavrasChave = new JTextArea(app.getPalavrasChaveString() + ".", 3, 30);
        txtPalavrasChave.setLineWrap(true);
        txtPalavrasChave.setEditable(false);        
        
        painelPalavrasChave = new JScrollPane(txtPalavrasChave);
        painelPalavrasChave.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        lbTituloComentarios = new JLabel("Comentários:");

        listModel = new DefaultListModel();
        list = new JList(listModel);
        for (Comentario comentario : app.getComentarios()) {
            listModel.addElement( String.format("%s: %s", comentario.getUsuario(), comentario.getComentario()) );
        }

        list.setEnabled(false);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        listComentarios = new JScrollPane(list);
        listComentarios.setPreferredSize(new Dimension(250, 100));

        lbTituloComentar = new JLabel("Escreva seu comentário na caixa abaixo:");        
        textComentar = new JTextArea(4, 20);
        textComentar.setLineWrap(true);

        painelComentar = new JScrollPane(textComentar);

        btnComentar = new JButton("Comentar");
        btnSair = new JButton("Sair");

        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 2, 20, 40));
        painelBotoes.add(btnComentar);
        painelBotoes.add(btnSair);

        adicionarComponentes(painelNome, GridBagConstraints.WEST, GridBagConstraints.NONE, 0, 0, 1, 1);
        adicionarComponentes(new JPanel(), GridBagConstraints.WEST, GridBagConstraints.NONE, 1, 0, 1, 1);
        adicionarComponentes(painelAvaliacao, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 0, 1, 1);
        adicionarComponentes(new JPanel(), GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 0, 1, 1);
        adicionarComponentes(lbTituloDescricao, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 0, 1, 1);
        adicionarComponentes(painelDescricao, GridBagConstraints.WEST, GridBagConstraints.NONE, 5, 0, 1, 1);
        adicionarComponentes(lbTituloPalavraChave, GridBagConstraints.WEST, GridBagConstraints.NONE, 6, 0, 1, 1);
        adicionarComponentes(painelPalavrasChave, GridBagConstraints.WEST, GridBagConstraints.NONE, 7, 0, 1, 1);
        adicionarComponentes(new JPanel(), GridBagConstraints.WEST, GridBagConstraints.NONE, 8, 0, 1, 1);
        adicionarComponentes(lbTituloComentarios, GridBagConstraints.WEST, GridBagConstraints.NONE, 9, 0, 1, 1);
        adicionarComponentes(listComentarios, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 10, 0, 1, 1);
        adicionarComponentes(lbTituloComentar, GridBagConstraints.WEST, GridBagConstraints.BOTH, 11, 0, 1, 1);
        adicionarComponentes(painelComentar, GridBagConstraints.WEST, GridBagConstraints.BOTH, 12, 0, 1, 1);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.NONE, 13, 0, 1, 1);

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getParentScreen() instanceof TelaListarTodosApps) {
                    new TelaListarTodosApps(getUsuario()).setVisible(true);
                    disposeParent();
                }
                else if (getParentScreen() instanceof TelaBuscarApp) {
                    new TelaBuscarApp(getUsuario()).setVisible(true);
                    disposeParent();
                }
                else if (getParentScreen() instanceof TelaListarMeusApps) {
                    new TelaListarMeusApps(getUsuario()).setVisible(true);
                    disposeParent();
                }
                else {
                    System.out.println("deu ruim!");
                    setParentVisible(true);
                }
                dispose();
            }
        });

        btnComentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String texto = getComentario();
                
                    Comentario comentario = new Comentario(texto, getUsuario().getNome());
                    app.addComentario(comentario);
                    
                    // refresh
                    dispose();
                    new TelaVisualizarApp(getParentScreen(), getUsuario(), app).setVisible(true);
                }
                catch (ComentarioException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
    
    private String getComentario() throws ComentarioException {
        final int MAX = 144;
        String texto = textComentar.getText().trim().replace("\n", " ");
        
        if (texto.isEmpty()) {
            throw new ComentarioException("Comentário vazio.");
        }
        else if (texto.length() > MAX) {
            throw new ComentarioException(String.format("Comentário tem %d caracteres, máximo é %d.", texto.length(), MAX));
        }
        else {
            return texto;
        }
    }
    
    private String stringReduzida(String s, int tam) {
        if (s.length() > tam) {
            return s.substring(0, tam-3) + "...";
        }
        else {
            return s;
        }
    }

    @Override
    public void acaoAoFechar() {
        btnSair.doClick();
    }

}
