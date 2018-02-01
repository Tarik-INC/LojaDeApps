package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.users.Usuario;
import java.awt.GridBagConstraints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Tela de gerenciamento do usuário, diponível após o login.
 * Mostra como opções:
 * - cadastrar novo app
 * - listar meus apps
 * - buscar app
 * - logout
 * @author rafael, tarik, william
 */
public class TelaGerenciamento extends Tela {
    
    private JButton btnCadastrarApp;
    private JButton btnListarMeusApps;
    private JButton btnListarTodosApps;
    private JButton btnBuscarApp;
    private JButton btnSair;

    public TelaGerenciamento(Usuario usuario) {
        super("Loja de Apps", usuario, 300, 300);
        construirTela();
    }

    @Override
    public void construirTela() {

        btnCadastrarApp = new JButton("Cadastrar Novo App");
        btnListarMeusApps = new JButton("Listar Meus Apps");
        btnListarTodosApps = new JButton("Listar Todos os Apps");
        btnBuscarApp = new JButton("Buscar Apps");
        btnSair = new JButton("Logout");

        adicionarComponentes(btnCadastrarApp, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(btnListarMeusApps, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1,0,1,1);
        adicionarComponentes(btnListarTodosApps, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2,0,1,1);
        adicionarComponentes(btnBuscarApp, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3, 0, 1, 1);
        adicionarComponentes(btnSair, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 4, 0, 1, 1);

        btnCadastrarApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastrarApp(getUsuario()).setVisible(true);
            }
        });
        
        btnListarMeusApps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaListarMeusApps(getUsuario()).setVisible(true);
            }
        });
        
        btnListarTodosApps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaListarTodosApps(getUsuario()).setVisible(true);
            }
        });
        
        btnBuscarApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaBuscarApp(getUsuario()).setVisible(true);
            }
        });
        
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int closeOption = mensagemConfirmacaoLogout();
                if (closeOption == JOptionPane.YES_OPTION) {
                    // fecha todas as janelas da sessão:
                    for (Window window : Window.getWindows()) window.dispose();
                    // volta para tela inicial:
                    new TelaInicial().setVisible(true);
                }
            }
        });
    }

    private int mensagemConfirmacaoLogout() {
        return JOptionPane.showConfirmDialog(null, "Deseja mesmo encerrar sua sessão?", "Deslogar", JOptionPane.YES_NO_OPTION);
    }
    
    @Override
    public void acaoAoFechar() {
        btnSair.doClick();
    }
    
}
