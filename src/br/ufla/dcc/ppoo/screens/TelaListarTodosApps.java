package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.modeling.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.NenhumItemSelecionadoException;
import br.ufla.dcc.ppoo.management.Gerenciador;
import br.ufla.dcc.ppoo.modeling.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * Tela para listar todos os apps de todos os usuários, ordenados pelo nome.
 * Funciona da mesma forma que a Tela de listar os apps de um usuário,
 * apenas restringe o acesso às opções de modificação para somente que for
 * proprietário do recurso
 * @author rafael, tarik, william
 */
public class TelaListarTodosApps extends TelaListarMeusApps {

    private List<Aplicativo> list;
    
    public TelaListarTodosApps(Usuario usuario) {
        super("Todos os Apps", usuario, 540, 425);
    }
    
    @Override
    public void preencherLista() {
        DefaultListModel listModel = getListModel();
        list = Gerenciador.obterTodosAplicativos();
        list.sort(Comparator.comparing(Aplicativo::getNome));
        
        listModel.addElement( linhaFormatada("NOME DO APP:", "AUTOR:", "NOTA:") );
        
        for (Aplicativo app : list) {
            listModel.addElement( linhaFormatada(app.getNome(), app.getAutor().getNome(), app.getNotaFormatada()) );
        }
    }

    @Override
    public void addListenerVisualizar() {
        getBtnVisualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = getSelectedIndex();
                    TelaListarTodosApps.this.setVisible(false);
                    new TelaVisualizarApp(TelaListarTodosApps.this, getUsuario(), list.get(index)).setVisible(true);
                } 
                catch (NenhumItemSelecionadoException ex) {
                    JOptionPane.showMessageDialog(
                        null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    @Override
    public void addListenerEditar() {
        getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = getSelectedIndex();
                    
                    if (list.get(index).getAutor().equals(getUsuario())) {
                        TelaListarTodosApps.this.setVisible(false);
                        new TelaEditarApp(TelaListarTodosApps.this, getUsuario(), list.get(index)).setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(
                            null, "Somente o autor pode fazer isso.", "Proibido", JOptionPane.WARNING_MESSAGE
                        );
                    }
                } 
                catch (NenhumItemSelecionadoException ex) {
                    JOptionPane.showMessageDialog(
                        null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    @Override
    public void addListenerRemover() {
        getBtnRemover().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = getSelectedIndex();
                    
                    if (list.get(index).getAutor().equals(getUsuario())) {
                        int reply = JOptionPane.showConfirmDialog(
                            null, "Tem certeza que quer remover o app?", "Remover Aplicativo", JOptionPane.YES_NO_OPTION
                        );

                        if (reply == JOptionPane.YES_OPTION) {
                            getUsuario().removeAplicativo(list.get(index).getNome());

                            JOptionPane.showMessageDialog(
                                null, "Remoção concluída!", "Concluído", JOptionPane.INFORMATION_MESSAGE
                            );

                            // refresh:
                            TelaListarTodosApps.this.dispose();
                            new TelaListarTodosApps(getUsuario()).setVisible(true);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(
                            null, "Somente o autor pode fazer isso.", "Proibido", JOptionPane.WARNING_MESSAGE
                        );
                    }
                } 
                catch (NenhumItemSelecionadoException ex) {
                    JOptionPane.showMessageDialog(
                        null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
    
}
