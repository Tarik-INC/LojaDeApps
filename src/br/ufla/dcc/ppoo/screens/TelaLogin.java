package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.exceptions.LoginInexistenteException;
import br.ufla.dcc.ppoo.management.Gerenciador;
import br.ufla.dcc.ppoo.modeling.Usuario;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Tela de login do usuário.
 * Pede para autenticação:
 * - login
 * - senha
 * @author rafael, tarik, william
 */
public class TelaLogin extends Tela {
    
    private JLabel lbLogin;
    private JLabel lbSenha;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnLogar;
    private JButton btnCancela;
    private JPanel painel;

    public TelaLogin(Tela source) {
        super("Autenticar Usuário", source, 360, 180);
        construirTela();
    }

    @Override
    public void construirTela() {

        lbLogin = new JLabel("Login");
        lbSenha = new JLabel("Senha");

        txtLogin = new JTextField(20);
        txtSenha = new JPasswordField(6);
        
        btnLogar = new JButton("Logar");
        btnCancela = new JButton("Cancelar");

        painel = new JPanel();
        painel.setLayout(new GridLayout(1, 1, 10, 10));
        painel.add(btnLogar);
        painel.add(btnCancela);

        
        adicionarComponentes(lbLogin, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0, 0, 1, 1);
        adicionarComponentes(lbSenha, GridBagConstraints.WEST, GridBagConstraints.BOTH, 1, 0, 1, 1);
        adicionarComponentes(txtLogin, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0, 1, 1, 1);
        adicionarComponentes(txtSenha, GridBagConstraints.WEST, GridBagConstraints.BOTH, 1, 1, 1, 1);
        adicionarComponentes(new JPanel(), GridBagConstraints.CENTER, GridBagConstraints.NONE, 2, 0, 2, 1);
        adicionarComponentes(painel, GridBagConstraints.CENTER, GridBagConstraints.NONE, 3, 0, 2, 1);


        btnLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] senha = txtSenha.getPassword();
                try {
                    Usuario usuario = Gerenciador.buscarCadastro(txtLogin.getText());
                    if (usuario.isSenha(senha)) {
                        JOptionPane.showMessageDialog(
                            null, "Bem Vindo, " + usuario.getNome() + "!", "Logado com Sucesso", JOptionPane.INFORMATION_MESSAGE
                        );
                        
                        disposeParent();
                        dispose();
                        new TelaGerenciamento(usuario).setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta.", "Falha ao Logar", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (LoginInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, "Login incorreto.", "Falha ao Logar", JOptionPane.ERROR_MESSAGE);
                }
                finally {
                    // limpar por segurança
                    for (int i = 0; i < senha.length; i++) senha[i] = '\0';
                    senha = null;
                }
            }
        });


        btnCancela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                setParentVisible(true);
            }
        });
    }

    @Override
    public void acaoAoFechar() {
        btnCancela.doClick();
    }
    
}