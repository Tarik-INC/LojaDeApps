package br.ufla.dcc.ppoo.management;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.LoginInexistenteException;
import br.ufla.dcc.ppoo.exceptions.LoginJaExistenteException;
import br.ufla.dcc.ppoo.users.Cadastro;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author tarik
 */
public abstract class Gerenciador {

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

    public static void adicionarCadastro(Cadastro cadastro) throws LoginJaExistenteException {
        dataBase.addCadastro(cadastro);
    }
    
    public static void adicionarAplicativo(Aplicativo app) {
        dataBase.addNovoApp(app);
    }
    
    public static Cadastro buscarCadastro(String login) throws LoginInexistenteException {
        return dataBase.buscarCadastro(login);
    }

}
