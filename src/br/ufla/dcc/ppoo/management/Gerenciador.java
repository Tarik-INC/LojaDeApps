package br.ufla.dcc.ppoo.management;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.AppInexistenteException;
import br.ufla.dcc.ppoo.exceptions.LoginInexistenteException;
import br.ufla.dcc.ppoo.exceptions.LoginJaExistenteException;
import br.ufla.dcc.ppoo.users.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Classe estática responsável por fornecer métodos de gestão da base de dados,
 * fazendo a interface entre a base de dados e o sistema do programa.
 * @author rafael, tarik, william
 */
public abstract class Gerenciador {

    private static BaseDados dataBase;
    private static File file;
    
    /**
     * Seleciona o endereço do arquivo da base de dados.
     * @param filePath Caminho do arquivo 
     */
    public static void setArquivoDados(String filePath) {
        file = new File(filePath);
    }
    
    /**
     * Tenta carregar dados do arquivo na base de dados.
     */
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

    /**
     * Salva base de dados no arquivo.
     */
    public static void salvarDados() throws IOException {
        dataBase.save(file);
    }

    /**
     * Adiciona novo usuário na base de dados.
     * @param cadastro Novo cadastro de usuário
     */
    public static void adicionarCadastro(Usuario cadastro) throws LoginJaExistenteException {
        dataBase.addCadastro(cadastro);
    }
    
    /**
     * Busca objeto usuário na base de dados a partir do login.
     * @param login Login a ser buscado
     * @return Referência para o objeto
     */
    public static Usuario buscarCadastro(String login) throws LoginInexistenteException {
        return dataBase.buscarCadastro(login);
    }
    
    /**
     * Busca um app na base de dados.
     * @param nome Nome do app buscado
     * @return Referência para o app
     */
    public static List<Aplicativo> buscarAplicativo(String nome) throws AppInexistenteException {
        return dataBase.buscarAplicativo(nome);
    }
    
    /**
     * Verifica se um app pertence à base de dados.
     * @param nome Nome do aplicativo
     * @return Booleano indicando sim ou não
     */
    public static boolean aplicativoExiste(String nome) {
        return dataBase.aplicativoExiste(nome);
    }

}
