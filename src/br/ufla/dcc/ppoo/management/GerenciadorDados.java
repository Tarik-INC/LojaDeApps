package br.ufla.dcc.ppoo.management;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.users.Cadastro;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author tarik
 */
public abstract class GerenciadorDados {

    private static BaseDados dataBase;
    private static File file;

    public static void setArquivoDados(String filePath) {
        file = new File(filePath);
    }
    
    public static void carregarDados() {
        try {
            // carrega do arquivo
            dataBase = new BaseDados(file);
        }
        catch (IOException e) {
            // cria vazio
            dataBase = new BaseDados();
        }
    }

    public static void salvarArquivoDados() {

    }

    public static void adicionarCadastro(Cadastro cadastro) {
        dataBase.addCadastro(cadastro);
    }
    
    public static void removerCadastro(Cadastro cadastro) {
        dataBase.removeCadastro(cadastro);
    }
    
    public static void adicionarAplicativo(Aplicativo app) {
        dataBase.addApp(app);
    }
    
    public static void removerAplicativo(Aplicativo app) {
        dataBase.removeApp(app);
    }

}
