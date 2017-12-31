package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.management.Gerenciador;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Tela inicial do programa.
 * Mostra opções:
 * - Logar no sistema
 * - Cadastrar novo usuário
 * - Sair do programa
 * @author rafael, tarik, william
 */
public class TelaInicial extends Tela {
    
    private JButton btnCadastro;
    private JButton btnLogin;
    private JButton btnSair;
    
    public TelaInicial() {
        super("Loja de Apps", 300, 300);
        construirTela();
    }

    @Override
    public void construirTela() {
        btnCadastro = new JButton("Novo Usuário", new ImageIcon(getClass().getResource("images/cadastro.png")));
        btnLogin = new JButton("Login", new ImageIcon(getClass().getResource("images/login.png")));
        btnSair = new JButton("Sair", new ImageIcon(getClass().getResource("images/sair.png")));

        adicionarComponentes(btnLogin, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(btnCadastro, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1,0,1,1);
        adicionarComponentes(btnSair, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2,0,1,1);
        
        
        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TelaCadastrarUsuario(TelaInicial.this).setVisible(true);
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TelaLogin(TelaInicial.this).setVisible(true);
            }
        });
        
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacaoConfirmacaoSair();
            }
        });
    }
    
    private void operacaoConfirmacaoSair() {
        int closeOption = JOptionPane.showConfirmDialog(null, "Deseja mesmo sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (closeOption == JOptionPane.YES_OPTION) {
            try {
                Gerenciador.salvarDados();
                System.exit(0);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao Salvar Dados", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void acaoAoFechar() {
        btnSair.doClick();
    }

}
