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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class TelaCadastro extends Tela {
    
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
    
    public TelaCadastro() {
        super("Cadastrar Usuário", 300, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        construirTela();
        pack();
    }

    @Override
    void construirTela() {

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 1, 30, 30));
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.NONE, 4, 1, 2, 1);

        rotuloNome = new JLabel("Nome ");
        adicionarComponentes(rotuloNome, GridBagConstraints.WEST, GridBagConstraints.NONE, 1, 0, 1, 1);

        caixaTextoNome = new JTextField(20);
        adicionarComponentes(caixaTextoNome, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 1, 1, 3, 1);

        rotuloLogin = new JLabel("Login ");
        adicionarComponentes(rotuloLogin, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 0, 1, 1);

        caixaTextoLogin = new JTextField(20);
        adicionarComponentes(caixaTextoLogin, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 2, 1, 3, 1);

        rotuloSenha = new JLabel("Senha ");
        adicionarComponentes(rotuloSenha, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 0, 1, 1);

        caixaTextoSenha = new JPasswordField(6);
        adicionarComponentes(caixaTextoSenha, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 1, 1, 1);

        rotuloSenhaConfirmar = new JLabel("Confirmar senha ");
        adicionarComponentes(rotuloSenhaConfirmar, GridBagConstraints.CENTER, GridBagConstraints.NONE, 3, 2, 1, 1);

        caixaTextoSenhaConfirmar = new JPasswordField(6);
        adicionarComponentes(caixaTextoSenhaConfirmar, GridBagConstraints.CENTER, GridBagConstraints.NONE, 3, 3, 1, 1);
        
        
        btnSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String login = caixaTextoLogin.getText().trim();
                String nome = caixaTextoNome.getText().trim();
                String senha = caixaTextoSenha.getText();
                String confirmacao = caixaTextoSenhaConfirmar.getText();

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
                        "Usuário já existe, favor escolha outro.", "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (LoginInvalidoException | SenhaInvalidaException except) {
                    JOptionPane.showMessageDialog(null, except.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                dispose();
            }
        });
    }
    
    private void validarLogin(String login) throws LoginInvalidoException {
        if ( login.isEmpty() ) {
            throw new LoginInvalidoException("Campo de login está vazio.");
        }
    }
    
    private void validarSenha(String senha, String confirmacao) throws SenhaInvalidaException {
        if ( senha.isEmpty() ) {
            throw new SenhaInvalidaException("Campo senha está vazio.");
        } 
        else if ( senha.length() <= 4 ) {
            throw new SenhaInvalidaException("Senha deve conter no mínimo 5 dígitos.");
        }
        else if ( ! senha.equals(confirmacao) ) {
            throw new SenhaInvalidaException("Confirmação de senha digitada incorretamente.");
        }
    }
    
    private void validarNome(String nome) throws LoginInvalidoException {
        if ( nome.isEmpty() ) {
            throw new LoginInvalidoException("Campo nome de usuário vazio.");
        }
    }
    
}
