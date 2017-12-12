package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.users.Usuario;
import javax.swing.*;

public class TelaVisualizarApp extends Tela {
    private Usuario usuario;
    public TelaVisualizarApp() {
        super("Visualizar Aplicativo", 300, 800);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        construirTela();
        pack();
    }

    @Override
    void construirTela() {
        
        JLabel lbNomeApp = new JLabel (usuario.usuario.getNome() + "  " ); 
        
    }
}