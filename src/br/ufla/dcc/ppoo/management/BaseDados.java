package br.ufla.dcc.ppoo.management;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.exceptions.AppInexistenteException;
import br.ufla.dcc.ppoo.exceptions.LoginInexistenteException;
import br.ufla.dcc.ppoo.exceptions.LoginJaExistenteException;
import br.ufla.dcc.ppoo.users.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class BaseDados {
    private final List<Usuario> cadastros;

    /**
     * Cria base de dados vazia.
     */
    public BaseDados() {
        cadastros = new ArrayList<Usuario>();
    }
    
    /**
     * Cria base de dados a partir do arquivo
     * @param file Arquivo a ser lido
     * @throws java.io.IOException
     */
    public BaseDados(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file));
        cadastros = (List<Usuario>) reader.readObject();
    }
    
    public void save(File file) throws IOException {
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
        writer.writeObject(cadastros);
    }
    
    public void addCadastro(Usuario cadastro) throws LoginJaExistenteException {
        for (Usuario c : cadastros) {
            if (c.isLogin(cadastro.getLogin())) {
                throw new LoginJaExistenteException("Login já existe");
            }
        }
        
        cadastros.add(cadastro);
    }
    
    public Usuario buscarCadastro(String login) throws LoginInexistenteException {
        for (Usuario c : cadastros) {
            if (c.isLogin(login)) {
                return c;
            }
        }
        
        throw new LoginInexistenteException("Cadastro não encontrado");
    }

    public List<Aplicativo> buscarAplicativo(String aplicativo) throws AppInexistenteException {
        List<Aplicativo> lista = new LinkedList();
        for (Usuario c : cadastros) {
            for (Aplicativo app : c.getAplicativos()) {
                if (app.getNome().equals(aplicativo) || app.getPalavrasChave().contains(aplicativo)) {
                    lista.add(app);
                }
            }
        }
        if (lista != null) {
            lista.sort(Comparator.comparing(Aplicativo::getNota));
            Collections.reverse(lista);
            return lista;
        } else {
            throw new AppInexistenteException("Aplicativo não encontrado");
        }
    }


    public boolean aplicativoExiste(String aplicativo) {
        for (Usuario c: cadastros) {
            for (Aplicativo app : c.getAplicativos()) {
                if (app.getNome().equals(aplicativo)) {
                    return true;
                }
            }
        }
        return false;
    }

}
