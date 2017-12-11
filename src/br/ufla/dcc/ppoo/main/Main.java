package br.ufla.dcc.ppoo.main;

import br.ufla.dcc.ppoo.management.Gerenciador;
import br.ufla.dcc.ppoo.screens.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Gerenciador.setArquivoDados("database.bin");
        Gerenciador.carregarDados();
        
        new TelaInicial().setVisible(true);
    }
}
