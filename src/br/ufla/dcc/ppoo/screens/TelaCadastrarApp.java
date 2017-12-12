package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.AppNomeVazioException;
import br.ufla.dcc.ppoo.exceptions.AppPalavrasChaveException;
import br.ufla.dcc.ppoo.users.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * Tela para usuário cadastrar novo app.
 * Pede para cadastro:
 * - Nome do app
 * - Descrição de texto longo
 * - Lista de palavras-chave
 * @author rafael, tarik, william
 */
public class TelaCadastrarApp extends Tela{
    
    private final Tela source;
    private final Usuario usuario;
    private JLabel lbNome;
    private JLabel lbDescricao;
    private JLabel lbPalavrasChave;
    private JTextField txtNome;
    private JTextArea txtDescricao;
    private JTextField txtPalavrasChave;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JPanel painelBotoes;
    
    public TelaCadastrarApp(Tela source, Usuario usuario) {
        super("Cadastrar App", 300, 300);
        this.usuario = usuario;
        this.source = source;
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
                source.setVisible(true);
                dispose();
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = verificarNome();
                    List<String> palavrasChave = verificarPalavrasChave();
                    String descricao = verificarDescricao();
                    
                    usuario.addApp( new Aplicativo(nome, descricao, palavrasChave) );

                    JOptionPane.showMessageDialog(null,
                            "Aplicativo cadastrado com sucesso!", "Cadastro Completo", 
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    btnCancelar.doClick();
                } 
                catch (AppNomeVazioException | AppPalavrasChaveException except) {
                    JOptionPane.showMessageDialog(null,
                            except.getMessage(), "Erro", 
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
    
    /**
     * 
     * @return Nome do app em string
     */
    private String verificarNome() throws AppNomeVazioException {
        String nome = txtNome.getText().trim();
        if ( nome.isEmpty() ) {
            throw new AppNomeVazioException("Campo nome do app está vazio.");
        }
        return nome;
    }
    
    /**
     * 
     * @return Lista de palavras-chave
     */
    private List<String> verificarPalavrasChave() throws AppPalavrasChaveException {
        List<String> palavrasChave = Arrays.asList(txtPalavrasChave.getText().split(";"));
        if (palavrasChave.size() < 2) {
            throw new AppPalavrasChaveException("Deve haver no mínimo 2 palavras-chave.");
        } 
        return palavrasChave;
    }
    
    
    /**
     * 
     * @return 
     */
    private String verificarDescricao() {
        /*
        String[] texto = txtDescricao.getText().split("\n");
        String descricao = "";
        for (int i = 0; i < texto.length; ++i) {
            descricao += texto[i] + System.lineSeparator();
        }
        */
        return txtDescricao.getText().trim();
    }
    
}
