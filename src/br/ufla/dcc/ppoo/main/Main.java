package br.ufla.dcc.ppoo.main;

import br.ufla.dcc.ppoo.management.GerenciadorDados;
import br.ufla.dcc.ppoo.screens.*;

public class Main {
    public static void main(String[] args) {
        GerenciadorDados.setArquivoDados("database.bin");
        GerenciadorDados.carregarDados();
        new TelaInicial().setVisible(true);
    }
}
