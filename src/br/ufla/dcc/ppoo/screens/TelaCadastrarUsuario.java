package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.exceptions.LoginInvalidoException;
import br.ufla.dcc.ppoo.exceptions.LoginJaExistenteException;
import br.ufla.dcc.ppoo.exceptions.SenhaInvalidaException;
import br.ufla.dcc.ppoo.management.Gerenciador;
import br.ufla.dcc.ppoo.users.Usuario;

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
 * Tela de cadastro de um novo usuário.
 * Pede para cadastro:
 * - Nome
 * - Login
 * - Senha
 * @author rafael, tarik, william
 */
public class TelaCadastrarUsuario extends Tela {
      
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JPanel painelBotoes;
    private JLabel rotuloNome;
    private JLabel rotuloLogin;
    private JLabel rotuloSenha;
    private JLabel rotuloSenhaConfirmar;
    private JTextField caixaTextoNome;
    private JTextField caixaTextoLogin;
    private JPasswordField caixaTextoSenha;
    private JPasswordField caixaTextoSenhaConfirmar;
    
    public TelaCadastrarUsuario(Tela source) {
        super("Cadastrar Usuário", source, 360, 180);
        construirTela();
    }

    @Override
    public void construirTela() {

        btnSalvar = new JButton("Cadastrar");
        btnCancelar = new JButton("Cancelar");

        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 1, 10, 10));
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        
        rotuloNome = new JLabel("Nome");
        caixaTextoNome = new JTextField(20);
        
        rotuloLogin = new JLabel("Login");
        caixaTextoLogin = new JTextField(20);
        
        rotuloSenha = new JLabel("Senha");
        caixaTextoSenha = new JPasswordField(6);
        
        rotuloSenhaConfirmar = new JLabel("Confirmar senha");
        caixaTextoSenhaConfirmar = new JPasswordField(6);
        

        adicionarComponentes(rotuloNome, GridBagConstraints.WEST, GridBagConstraints.NONE, 0, 0, 1, 1);        
        adicionarComponentes(caixaTextoNome, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 0, 1, 3, 1);        
        adicionarComponentes(rotuloLogin, GridBagConstraints.WEST, GridBagConstraints.NONE, 1, 0, 1, 1);        
        adicionarComponentes(caixaTextoLogin, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 1, 1, 3, 1);        
        adicionarComponentes(rotuloSenha, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 0, 1, 1);        
        adicionarComponentes(caixaTextoSenha, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 1, 1, 1);        
        adicionarComponentes(rotuloSenhaConfirmar, GridBagConstraints.CENTER, GridBagConstraints.NONE, 2, 2, 1, 1);        
        adicionarComponentes(caixaTextoSenhaConfirmar, GridBagConstraints.CENTER, GridBagConstraints.NONE, 2, 3, 1, 1);
        adicionarComponentes(new JPanel(), GridBagConstraints.CENTER, GridBagConstraints.NONE, 3, 0, 4, 1);        
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.NONE, 4, 0, 4, 1);        
        
        
        btnSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String login = caixaTextoLogin.getText().trim();
                String nome = caixaTextoNome.getText().trim();
                char[] senha = caixaTextoSenha.getPassword();
                char[] confirmacao = caixaTextoSenhaConfirmar.getPassword();

                try {
                    validarNome(nome);
                    validarLogin(login);
                    validarSenha(senha, confirmacao);
                    
                    try {
                        Gerenciador.adicionarCadastro(new Usuario(nome,login,senha));
                        JOptionPane.showMessageDialog(null,
                            "Cadastrado usuário com sucesso!", "Cadastro Completo", JOptionPane.INFORMATION_MESSAGE);
                        
                        btnCancelar.doClick();
                    }
                    catch (LoginJaExistenteException except) {
                        JOptionPane.showMessageDialog(null,
                        except.getMessage(), "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (LoginInvalidoException | SenhaInvalidaException except) {
                    JOptionPane.showMessageDialog(null, except.getMessage(),
                        "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
                }
                finally {
                    // limpa senhas passadas
                    for (int i = 0; i < senha.length; i++) senha[i] = '\0';
                    for (int i = 0; i < confirmacao.length; i++) confirmacao[i] = '\0';
                    senha = null;
                    confirmacao = null;
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                setParentVisible(true);
            }
        });
    }
    
    private void validarLogin(String login) throws LoginInvalidoException {
        if ( login.isEmpty() ) {
            throw new LoginInvalidoException("Campo de login está vazio.");
        }
    }
    
    private void validarSenha(char[] senha, char[] confirmacao) throws SenhaInvalidaException {
        if ( senha == null || senha.length == 0 ) {
            throw new SenhaInvalidaException("Campo senha está vazio.");
        } 
        else if ( senha.length < 4 ) {
            throw new SenhaInvalidaException("Senha deve conter no mínimo 4 dígitos.");
        }
        else if ( ! senhaIgualConfirmacao(senha, confirmacao) ) {
            throw new SenhaInvalidaException("Confirmação de senha digitada incorretamente.");
        }
    }
    
    private void validarNome(String nome) throws LoginInvalidoException {
        if ( nome.isEmpty() ) {
            throw new LoginInvalidoException("Campo nome de usuário vazio.");
        }
    }
    
    private boolean senhaIgualConfirmacao(char[] senha, char[] confirm) {
        if (senha.length != confirm.length) {
            return false;
        }
        else {
            for (int i = 0; i < senha.length; i++) {
                if (senha[i] != confirm[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public void acaoAoFechar() {
        btnCancelar.doClick();
    }
    
}
