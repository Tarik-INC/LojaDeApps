package br.ufla.dcc.ppoo.management;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.users.Cadastro;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Todos os dados do sistema
 * @author william
 */
public class Dados {
    private final List<Cadastro> cadastros;
    private final List<Aplicativo> aplicativos;

    /**
     * Construtor do zero
     */
    public Dados() {
        cadastros = new LinkedList();
        aplicativos = new LinkedList();
    }
    
    /**
     * Construtor a partir do arquivo
     * @param file 
     */
    public Dados(File file) {
        
    }
}
