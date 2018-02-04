package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.modeling.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.NenhumItemSelecionadoException;
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
import javax.swing.ListSelectionModel;

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
public class TelaListarMeusApps extends Tela {
    
    private JButton btnEditar;
    private JButton btnRemover;
    private JButton btnVisualizar ; 
    private JButton btnSair; 
    private JPanel painelBotoes; 
    private JLabel lbInstrucao; 
    private DefaultListModel listModel; 
    private JList<Aplicativo> list; 
    private JScrollPane listScroller; 

    public TelaListarMeusApps(Usuario usuario) {
        this("Meus Apps", usuario, 540, 425);
    }
    
    public TelaListarMeusApps(String nomeTela, Usuario usuario, int larg, int alt) {
        super(nomeTela, usuario, larg, alt);
        construirTela();
    }
    
    @Override
    public void construirTela() {

        lbInstrucao = new JLabel("Selecione um aplicativo para realizar alguma ação");
        btnVisualizar = new JButton("Visualizar");
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");
        btnSair = new JButton("Sair");
        
        criarPainelBotoes();
        criarLista();
        preencherLista();
        
        
        adicionarComponentes(lbInstrucao, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(listScroller, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1, 0, 1, 1);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2,0,1,1);
        
        
        addListenerSair();
        addListenerVisualizar();
        addListenerEditar();
        addListenerRemover();
        
    }
    
    public void preencherLista() {
        listModel.addElement( linhaFormatada("NOME DO APP:", "DESCRIÇÃO:", "NOTA:") );
        
        for (Aplicativo app : getUsuario().getAplicativos()) {
            listModel.addElement( linhaFormatada(app.getNome(), app.getDescricaoFormatada(), app.getNotaFormatada()) );
        }
    }
    
    public void addListenerEditar() {
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = getUsuario();
                try {
                    int index = getSelectedIndex();
                    
                    Tela tv = new TelaEditarApp(TelaListarMeusApps.this, usuario, usuario.getAplicativo(index));
                    tv.setVisible(true);
                }
                catch (NenhumItemSelecionadoException except) {
                    JOptionPane.showMessageDialog(
                        null, except.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
    
    public void addListenerRemover() {
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = getUsuario();
                try {
                    int index = getSelectedIndex();
                    
                    int reply = JOptionPane.showConfirmDialog(
                        null, "Tem certeza que quer remover o app?", "Remover Aplicativo", JOptionPane.YES_NO_OPTION
                    );
                    
                    if (reply == JOptionPane.YES_OPTION) {
                        usuario.removeAplicativo(index);
                        
                        JOptionPane.showMessageDialog(
                            null, "Remoção concluída!", "Concluído", JOptionPane.INFORMATION_MESSAGE
                        );
                        
                        // refresh:
                        dispose();
                        new TelaListarMeusApps(usuario).setVisible(true);
                    }
                }
                catch (NenhumItemSelecionadoException except) {                
                    JOptionPane.showMessageDialog(
                        null, except.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE
                    );
                } 
            }
        });
    }
    
    public void addListenerSair() {
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    public void addListenerVisualizar() {
        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Usuario usuario = getUsuario();
                try {
                    int index = getSelectedIndex();
                    
                    Tela tv = new TelaVisualizarApp(TelaListarMeusApps.this, usuario, usuario.getAplicativo(index));
                    tv.setVisible(true);
                } 
                catch (NenhumItemSelecionadoException except) {
                    JOptionPane.showMessageDialog(
                        null, except.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
    
    public void criarPainelBotoes() {
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 4, 30, 30));
        painelBotoes.add(btnVisualizar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnSair);
    }
    
    public void criarLista() {
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(500, 300));
    }
    
    public int getSelectedIndex() throws NenhumItemSelecionadoException {
        int index = list.getSelectedIndex();
        if (index < 1) {
            throw new NenhumItemSelecionadoException("Nenhum aplicativo selecionado.");
        }
        else {
            // subtrai 1 porque 1.a linha da lista é o cabeçalho
            return index - 1;
        }
    }
    
    public String formataStringTamanho(String nome) {
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
    
    public String linhaFormatada(String nome, String descricao, String nota) {
        return String.format("%s  |  %s  |  %s", 
            formataStringTamanho(nome),
            formataStringTamanho(descricao),
            formataStringTamanho(nota)
        );
    }

    @Override
    public void acaoAoFechar() {
        btnSair.doClick();
    }
    
    public JLabel getLbInstrucao() {
        return lbInstrucao;
    }

    public JButton getBtnVisualizar() {
        return btnVisualizar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnRemover() {
        return btnRemover;
    }

    public JButton getBtnSair() {
        return btnSair;
    }

    public JPanel getPainelBotoes() {
        return painelBotoes;
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    public JList<Aplicativo> getList() {
        return list;
    }

    public JScrollPane getListScroller() {
        return listScroller;
    }

    public void setLbInstrucao(JLabel lbInstrucao) {
        this.lbInstrucao = lbInstrucao;
    }

    public void setBtnVisualizar(JButton btnVisualizar) {
        this.btnVisualizar = btnVisualizar;
    }

    public void setBtnSair(JButton btnSair) {
        this.btnSair = btnSair;
    }

    public void setPainelBotoes(JPanel painelBotoes) {
        this.painelBotoes = painelBotoes;
    }

    public void setListModel(DefaultListModel listModel) {
        this.listModel = listModel;
    }

    public void setList(JList<Aplicativo> list) {
        this.list = list;
    }

    public void setListScroller(JScrollPane listScroller) {
        this.listScroller = listScroller;
    }

    public void setBtnEditar(JButton btnEditar) {
        this.btnEditar = btnEditar;
    }

    public void setBtnRemover(JButton btnRemover) {
        this.btnRemover = btnRemover;
    }
    
}
