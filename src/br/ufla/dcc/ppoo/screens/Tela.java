package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.users.Usuario;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * Protótipo das telas criadas pelo programa.
 * @author rafael, tarik, william
 */
public abstract class Tela extends JFrame {
            
    private final GridBagConstraints gbc;
    private final GridBagLayout gbl;
    private final Tela parentScreen;
    private final Usuario usuarioLogado;

    /**
     * Construtor da tela simplificado.
     * @param nomeTela Título da janela
     * @param largura Largura da janela
     * @param altura Altura da janela
     */
    public Tela(String nomeTela, int largura, int altura) {
        this(nomeTela, null, null, largura, altura);
    }
    
    /**
     * Construtor da tela simplificado 2.
     * @param nomeTela Título da janela
     * @param parentScreen Tela que chamou esta
     * @param largura Largura da janela
     * @param altura Altura da janela
     */
    public Tela(String nomeTela, Tela parentScreen, int largura, int altura) {
        this(nomeTela, parentScreen, null, largura, altura);
    }
    
    /**
     * Construtor da tela simplificado 3.
     * @param nomeTela Título da janela
     * @param usuario Usuário da sessão atual
     * @param largura Largura da janela
     * @param altura Altura da janela
     */
    public Tela(String nomeTela, Usuario usuario, int largura, int altura) {
        this(nomeTela, null, usuario, largura, altura);
    }
    
    /**
     * Construtor da tela completo.
     * @param nomeTela Título da janela
     * @param parentScreen Tela que chamou esta
     * @param usuario Usuário da sessão atual
     * @param largura Largura da janela
     * @param altura Altura da janela
     */
    public Tela(String nomeTela, Tela parentScreen, Usuario usuario, int largura, int altura) {
        super(nomeTela);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(largura, altura);
        setLocationRelativeTo(parentScreen);

        this.usuarioLogado = usuario;
        this.parentScreen = parentScreen;
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        setLayout(gbl);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (e.getID() == WindowEvent.WINDOW_CLOSING){
                    acaoAoFechar();
                }
            }
        });
    }

    /**
     * Adiciona componentes na tela.
     * @param comp Componente em si
     * @param achor Âncora
     * @param fill Preenchimento
     * @param linha Linha
     * @param coluna Coluna
     * @param larg Largura
     * @param alt Altura
     */
    public void adicionarComponentes(Component comp, int achor, int fill,
                                     int linha, int coluna, int larg, int alt) {
        gbc.anchor = (int) achor;
        gbc.fill = fill;
        gbc.gridy = linha;
        gbc.gridx = coluna;
        gbc.gridwidth = larg;
        gbc.gridheight = alt;
        gbc.insets = new Insets(3, 3, 3, 3);
        gbl.setConstraints(comp, gbc);
        add(comp);
    }
    
    /**
     * Muda visibilidade da tela que o chamou.
     * @param b Visível ou não (true/false)
     */
    public void setParentVisible(boolean b) {
        if (parentScreen != null) {
            parentScreen.setVisible(b);
        }
    }
    
    /**
     * Deleta a tela inutilizada.
     */
    public void disposeParent() {
        if (parentScreen != null) {
            parentScreen.dispose();
        }
    }

    /**
     * Get usuário logado na sessão atual da tela.
     * @return Referência para usuário
     */
    public Usuario getUsuario() {
        return usuarioLogado;
    }
    
    /**
     * Get tela pai.
     * @return Referência para a tela que a invocou
     */
    public Tela getParentScreen() {
        return parentScreen;
    }
    
    /**
     * Protótipo do método para construir a tela.
     */
    public abstract void construirTela();

    /**
     * Protótipo do método a ser chamado ao clicar em fechar a janela.
     */
    public abstract void acaoAoFechar();
    
}
