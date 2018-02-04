package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.modeling.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.AppJaExistenteException;
import br.ufla.dcc.ppoo.exceptions.AppNomeVazioException;
import br.ufla.dcc.ppoo.exceptions.AppPalavrasChaveException;
import br.ufla.dcc.ppoo.modeling.Usuario;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Tela para usuário cadastrar novo app.
 * Pede para cadastro:
 * - Nome do app
 * - Descrição de texto longo
 * - Lista de palavras-chave
 * @author rafael, tarik, william
 */
public class TelaCadastrarApp extends Tela {
    
    private JLabel lbNome;
    private JLabel lbDescricao;
    private JLabel lbPalavrasChave;
    private JTextField txtNome;
    private JTextArea txtDescricao;
    private JScrollPane paneDescricao;
    private JTextField txtPalavrasChave;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JPanel painelBotoes;
    
    public TelaCadastrarApp(Usuario usuario) {
        this("Cadastrar App", null, usuario);
    }

    public TelaCadastrarApp(String nomeTela, Tela parentScreen, Usuario usuario) {
        super(nomeTela, parentScreen, usuario, 360, 340);
        construirTela();
        addListenerCancelar();
        addListenerSalvar();
    }

    @Override
    public void construirTela() {

        lbNome = new JLabel("Nome do Aplicativo");
        lbDescricao = new JLabel("Descrição");
        lbPalavrasChave = new JLabel("Palavras-chave (Mínimo 2)");
        txtNome = new JTextField(20);
        
        txtDescricao = new JTextArea(10, 30);
        txtDescricao.setLineWrap(true);
        paneDescricao = new JScrollPane(txtDescricao);
        
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
        adicionarComponentes(paneDescricao, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3,0,2,1);
        adicionarComponentes(lbPalavrasChave, GridBagConstraints.WEST, GridBagConstraints.BOTH, 4,0,1,1);
        adicionarComponentes(txtPalavrasChave, GridBagConstraints.WEST, GridBagConstraints.BOTH, 5,0,2,1);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 6,0,2,1);
        
    }
    
    public void addListenerCancelar() {
        btnCancelar.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    public void addListenerSalvar() {
        btnSalvar.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = getNomeVerificado();
                    List<String> palavrasChave = getPalavrasChaveVerificadas();
                    String descricao = getDescricaoVerificada();
                    Usuario usuario = getUsuario();

                    usuario.addApp( new Aplicativo(nome, descricao, palavrasChave, usuario) );

                    JOptionPane.showMessageDialog(null,
                            "Aplicativo cadastrado com sucesso!", "Cadastro Completo", 
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    acaoAoFechar();
                } 
                catch (AppNomeVazioException | AppPalavrasChaveException | AppJaExistenteException except) {
                    JOptionPane.showMessageDialog(null,
                            except.getMessage(), "Erro no Cadastro", 
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
    
    public String getNomeVerificado() throws AppNomeVazioException {
        String nome = txtNome.getText().trim();
        if ( nome.isEmpty() ) {
            throw new AppNomeVazioException("Campo nome do app está vazio.");
        }
        return nome;
    }
    
    public List<String> getPalavrasChaveVerificadas() throws AppPalavrasChaveException {
        String[] array = txtPalavrasChave.getText().replaceAll("\t", " ").split(" ");
        List<String> palavrasChave = new LinkedList();
        
        for (String palavra : array) {
            if ( ! palavra.isEmpty() ) {
                palavrasChave.add(palavra);
            }
        }
        
        if (palavrasChave.size() < 2) {
            throw new AppPalavrasChaveException("Deve haver no mínimo 2 palavras-chave.");
        } 
        
        return palavrasChave;
    }
    
    public String getDescricaoVerificada() {
        return txtDescricao.getText().trim();
    }

    public void setTxtNome(String nome) {
        txtNome.setText(nome);
    }

    public void setTxtDescricao(String descricao) {
        txtDescricao.setText(descricao);
    }

    public void setTxtPalavrasChave(List<String> palavrasChave) {
        String insert = "";
        
        for (String palavra : palavrasChave) {
            insert = insert.concat(palavra + " ");
        }
        
        txtPalavrasChave.setText(insert);
    }

    @Override
    public void acaoAoFechar() {
        btnCancelar.doClick();
    }
    
}
