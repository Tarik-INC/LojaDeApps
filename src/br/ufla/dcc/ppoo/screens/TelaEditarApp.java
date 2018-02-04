package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.modeling.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.AppJaExistenteException;
import br.ufla.dcc.ppoo.exceptions.AppNomeVazioException;
import br.ufla.dcc.ppoo.exceptions.AppPalavrasChaveException;
import br.ufla.dcc.ppoo.modeling.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Tela para editar uma app já cadastrado.
 * Funciona do mesmo jeito que a Tela de cadastro de app,
 * apenas preenche os campos com os valores já salvos
 * @author rafael, tarik, william
 */
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
                new TelaListarMeusApps(getUsuario()).setVisible(true);
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

                    if (usuario.containsOther(app.getNome(), nome)) {
                        throw new AppJaExistenteException(String.format("Já existe um app chamado \"%s\".", nome));
                    }
                    else {
                        app.setNome(nome);
                        app.setDescricao(descricao);
                        app.setPalavrasChave(palavrasChave);
                        usuario.sortAplicativos();

                        JOptionPane.showMessageDialog(null,
                                "Aplicativo atualizado com sucesso!", "Edição Completa", 
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }

                    acaoAoFechar();
                } 
                catch (AppNomeVazioException | AppPalavrasChaveException |AppJaExistenteException except) {
                    JOptionPane.showMessageDialog(null,
                            except.getMessage(), "Erro no Cadastro", 
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
    
}
