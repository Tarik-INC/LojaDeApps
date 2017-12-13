package br.ufla.dcc.ppoo.management;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.AppInexistenteException;
import br.ufla.dcc.ppoo.exceptions.LoginInexistenteException;
import br.ufla.dcc.ppoo.exceptions.LoginJaExistenteException;
import br.ufla.dcc.ppoo.users.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author tarik
 */
public  class Gerenciador {

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
        catch (IOException | ClassNotFoundException e) {
            // cria vazio
            dataBase = new BaseDados();
        }
    }

    public static void salvarDados() throws IOException {
        dataBase.save(file);
    }

    public static void adicionarCadastro(Usuario cadastro) throws LoginJaExistenteException {
        dataBase.addCadastro(cadastro);
    }
    
    public static Usuario buscarCadastro(String login) throws LoginInexistenteException {
        return dataBase.buscarCadastro(login);
    }

    public static List<Aplicativo> buscarAplicativo(String aplicativo) throws AppInexistenteException {
        return dataBase.buscarAplicativo(aplicativo);
    }

    public static boolean aplicativoExiste(String aplicativo) {
        return dataBase.aplicativoExiste(aplicativo);
    }
}
