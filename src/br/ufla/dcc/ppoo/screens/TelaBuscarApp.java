package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.AppInexistenteException;
import br.ufla.dcc.ppoo.exceptions.CampoVazioException;
import br.ufla.dcc.ppoo.exceptions.NenhumItemSelecionadoException;
import br.ufla.dcc.ppoo.management.Gerenciador;
import br.ufla.dcc.ppoo.users.Usuario;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaBuscarApp extends TelaListarMeusApps {
    
    private List<Aplicativo> listaApps;
    private JLabel lbBuscarAplicativo;
    private JTextField txtBuscarAplicativo;
    private JButton btnBuscar;
    private JPanel painelBuscar;
            
    public TelaBuscarApp(Usuario usuario) {
        super("Buscar Aplicativo", usuario, 540, 480);
    }

    @Override
    public void construirTela() {
        
        lbBuscarAplicativo = new JLabel("Digite o nome ou uma palavra-chave para buscar");
        txtBuscarAplicativo = new JTextField(20);
        
        setLbInstrucao(new JLabel("Selecione um aplicativo para visualizar"));
        
        btnBuscar = new JButton("Buscar");
        setBtnVisualizar(new JButton("Visualizar"));
        setBtnSair(new JButton("Sair"));
        
        painelBuscar = new JPanel();
        painelBuscar.setLayout(new GridLayout(1, 2, 30, 30));
        painelBuscar.add(txtBuscarAplicativo);
        painelBuscar.add(btnBuscar);
        
        criarPainelBotoes();
        criarLista();
        
        adicionarComponentes(lbBuscarAplicativo, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(painelBuscar, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1,0,1,1);
        adicionarComponentes(new JPanel(), GridBagConstraints.CENTER, GridBagConstraints.BOTH, 2,0,1,1);
        adicionarComponentes(getLbInstrucao(), GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3,0,1,1);
        adicionarComponentes(getListScroller(), GridBagConstraints.CENTER, GridBagConstraints.BOTH, 4, 0, 1, 1);
        adicionarComponentes(getPainelBotoes(), GridBagConstraints.CENTER, GridBagConstraints.BOTH, 5,0,1,1);
        
        
        addListenerVisualizar(); 
        addListenerSair(); // sair faz a mesma coisa que a superclasse
        addListenerBuscar();
    }
    
    public void addListenerBuscar() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel listModel = getListModel();
                listModel.clear();
                
                try {
                    String stringBusca = getTextoBusca();
                    
                    listModel.addElement("Procurando...");
                    
                    listaApps = Gerenciador.buscarAplicativos(stringBusca);
                    
                    listModel.clear();
                    listModel.addElement(linhaFormatada("NOME DO APP:", "AUTOR:", "NOTA:"));
                    
                    for (Aplicativo app : listaApps) {
                        listModel.addElement( 
                            linhaFormatada(app.getNome(), app.getAutor().getNome(), app.getNotaFormatada()) 
                        );
                    }
                } 
                catch (AppInexistenteException ex) {
                    listModel.clear();
                    listModel.addElement("Sua busca não retornou resultados.");
                } 
                catch (CampoVazioException ex) {
                    JOptionPane.showMessageDialog(
                        null, ex.getMessage(), "Erro ao Buscar", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
    
    private String getTextoBusca() throws CampoVazioException {
        String txt = txtBuscarAplicativo.getText().trim();
        if (txt.isEmpty()) {
            throw new CampoVazioException("Campo buscar está vazio.");
        }
        else {
            return txt;
        }
    }

    @Override
    public void addListenerVisualizar() {
        getBtnVisualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Usuario usuario = getUsuario();
                try {
                    int index = getSelectedIndex();
                    
                    Tela tv = new TelaVisualizarApp(TelaBuscarApp.this, usuario, listaApps.get(index));
                    tv.setVisible(true);
                } 
                catch (NenhumItemSelecionadoException except) {
                    JOptionPane.showMessageDialog(
                        null, except.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    @Override
    public void criarPainelBotoes() {
        setPainelBotoes(new JPanel());
        JPanel painelBotoes = getPainelBotoes();
        painelBotoes.setLayout(new GridLayout(1, 2, 30, 30));
        painelBotoes.add(getBtnVisualizar());
        painelBotoes.add(getBtnSair());
    }
    
}
