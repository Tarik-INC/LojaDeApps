package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaBuscar extends Tela {
    public TelaBuscar() {
        super("Buscar Aplicativo", 300, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        construirTela();
        pack();
    }

    @Override
    void construirTela() {
        JLabel lbBuscarAplicativo = new JLabel("Buscar Aplicativo");
        JTextField txtBuscarAplicativo = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar");
        JPanel painelBuscar = new JPanel();
        painelBuscar.setLayout(new GridLayout(1, 2, 30, 30));
        painelBuscar.add(txtBuscarAplicativo);
        painelBuscar.add(btnBuscar);
        JLabel lbInstrucao = new JLabel("Selecione um aplicativo para realizar alguma ação:");
        JButton btnVisualizar = new JButton("Visualizar");
        JButton btnSair = new JButton("Sair");
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 2, 30, 30));
        painelBotoes.add(btnVisualizar);
        painelBotoes.add(btnSair);

        adicionarComponentes(lbBuscarAplicativo, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(painelBuscar, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1,0,1,1);
        adicionarComponentes(lbInstrucao, GridBagConstraints.WEST, GridBagConstraints.BOTH, 2,0,1,1);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 4,0,1,1);


        DefaultListModel listModel = new DefaultListModel();

        JList<Aplicativo> list = new JList<Aplicativo>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(300, 300));
        adicionarComponentes(listScroller, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 3, 0, 1, 1);




        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Nenhum aplicativo selecionado!", "ERRO", JOptionPane.WARNING_MESSAGE);
                } else {
//                    TelaVisualizarApp tv = new TelaVisualizarApp(usuario.getNome(), usuario.getAplicativo(index), TelaListarApp.this);
//                    tv.setVisible(true);
//                    setVisible(false);
                }
            }
        });


        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}