package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.AppNomeVazioException;
import br.ufla.dcc.ppoo.exceptions.AppPalavrasChaveException;
import br.ufla.dcc.ppoo.users.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class TelaEditarApp extends TelaCadastrarApp {

    private final Aplicativo app;

    public TelaEditarApp(Tela source, Usuario usuario, Aplicativo app) {
        super("Editar App", source, usuario);
        this.app = app;
        preencherCampos();
    }

    private void preencherCampos() {
        setTxtNome(app.getNome());
        setTxtDescricao(app.getDescricao());
        setTxtPalavrasChave(app.getPalavrasChave());
    }
    
    @Override
    public void addListenerCancelar() {
        getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                disposeParent();
                new TelaListarApp(getUsuario()).setVisible(true);
            }
        });
    }
    
    @Override
    public void addListenerSalvar() {
        getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = getNomeVerificado();
                    List<String> palavrasChave = getPalavrasChaveVerificadas();
                    String descricao = getDescricaoVerificada();
                    Usuario usuario = getUsuario();

                    usuario.removeAplicativo(app.getNome());
                    usuario.addApp( new Aplicativo(nome, descricao, palavrasChave, usuario) );

                    JOptionPane.showMessageDialog(null,
                            "Aplicativo atualizado com sucesso!", "Edição Completo", 
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    acaoAoFechar();
                } 
                catch (AppNomeVazioException | AppPalavrasChaveException except) {
                    JOptionPane.showMessageDialog(null,
                            except.getMessage(), "Erro no Cadastro", 
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
    
}
