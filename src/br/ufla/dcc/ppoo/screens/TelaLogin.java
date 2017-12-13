package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.exceptions.LoginInexistenteException;
import br.ufla.dcc.ppoo.management.Gerenciador;
import br.ufla.dcc.ppoo.users.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class TelaLogin extends Tela {

    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    private TelaInicial source;

    public TelaLogin(TelaInicial source) {
        super("Login", 300, 300);
        construirTela();
        pack();
        this.source = source;
    }


    @Override
    void construirTela() {

        JLabel lbLogin = new JLabel("Login");
        JLabel lbSenha = new JLabel("Senha");

        JTextField txtLogin = new JTextField(20);
        JPasswordField txtSenha = new JPasswordField(6);

        adicionarComponentes(lbLogin, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0, 0, 1, 1);
        adicionarComponentes(lbSenha, GridBagConstraints.WEST, GridBagConstraints.BOTH, 1, 0, 1, 1);

        adicionarComponentes(txtLogin, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0, 1, 1, 1);
        adicionarComponentes(txtSenha, GridBagConstraints.WEST, GridBagConstraints.BOTH, 1, 1, 1, 1);

        //

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancela = new JButton("Cancelar");

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(1, 1, 30, 30));
        painel.add(btnConfirmar);
        painel.add(btnCancela);

        adicionarComponentes(painel, GridBagConstraints.CENTER, GridBagConstraints.NONE, 4, 1, 2, 1);


        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuario c = Gerenciador.buscarCadastro(txtLogin.getText());
                    if (c.isSenha(txtSenha.getText())) {
                        JOptionPane.showMessageDialog(null,
                                "Bem Vindo, " + c.getNome() + "!", "Logado com Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        source.dispose();
                        dispose();
                        new TelaGerenciamento(source, c).setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta.", "Falha ao Logar", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (LoginInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, "Login incorreto.", "Falha ao Logar", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnCancela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                dispose();
            }
        });
    }
}
