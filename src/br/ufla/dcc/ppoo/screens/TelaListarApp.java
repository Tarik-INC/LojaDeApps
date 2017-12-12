package br.ufla.dcc.ppoo.screens;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.users.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaListarApp extends Tela {

    private Usuario usuario;

    public TelaListarApp(Usuario usuario) {
        super("Lista Apps", 300, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.usuario = usuario;
        construirTela();
        pack();

    }

    @Override
    void construirTela() {

        JLabel lbInstrucao = new JLabel("Selecione um aplicativo para realizar alguma ação:");
        JButton btnVisualizar = new JButton("Visualizar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");
        JButton btnSair = new JButton("Sair");
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 4, 30, 30));
        painelBotoes.add(btnVisualizar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnSair);

        adicionarComponentes(lbInstrucao, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0,0,1,1);
        adicionarComponentes(painelBotoes, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1,0,1,1);

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        
        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               setVisible(false);
               // para fins de teste
               ArrayList<String> palavrasChaves = new ArrayList<String>();
               palavrasChaves.add("PalavraChave");
               palavrasChaves.add("PalavrasChave2");
               TelaVisualizarApp tv =  new TelaVisualizarApp(usuario.getNome(), new Aplicativo("teste-apagar","nao  esquecer de apagar isso",
                       palavrasChaves),TelaListarApp.this);
               tv.setVisible(true);
            }     
        });


        String[] columnNames = {"Nome",
                "Descrição",
                "Palavras-Chave",
                "Nota",
                "Downloads"};
        Object[][] data = {

        };

/*
        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };
*/
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        adicionarComponentes(scrollPane, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 1, 1);

    }
}
