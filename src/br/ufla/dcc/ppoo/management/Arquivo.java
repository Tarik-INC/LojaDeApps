package br.ufla.dcc.ppoo.management;

import br.ufla.dcc.ppoo.miscellaneous.Data;
import br.ufla.dcc.ppoo.users.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    private List<Cadastro> cadastros;
    private File file = new File("ArqCadastro.bin");

    public Arquivo() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            cadastros = new ArrayList<Cadastro>();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public void AdicionarCadastro(Cadastro cads) {
        cadastros.add(cads);
    }

    public void escrever() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("ArqCadastro.bin"));
            oos.writeObject(cadastros);
        } catch (IOException e) {
            System.out.println("Erro de escrita no arquivo 1!");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ioex) {
                    System.out.println("Erro de escrita no arquivo 2");
                }
            }
        }
    }

    private void ler() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("ArqCadastro.bin"));
            List<Cadastro> cadastros = (List<Cadastro>) ois.readObject();
            for (Cadastro c:  cadastros) {
                System.out.println(c);
            }
        } catch (IOException|ClassNotFoundException e) {
            System.out.println("Erro de escrita no arquivo 3!");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ioex) {
                    System.out.println("Erro de escrita no arquivo 4");
                }
            }
        }
    }

//nao funcional, ele encontra caso login e senha sejam iguais, alem de nao encontrar todos os casos
    public boolean buscar(String login, String senha) {
        boolean encontrou = false;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("ArqCadastro.bin"));
            List<Cadastro> cadastros = (List<Cadastro>) ois.readObject();
            for (Cadastro c:  cadastros) {
                if (c.isLogin(login) && c.isSenha(senha)) {
                    encontrou = true;
                    break;
                }
            }
        } catch (IOException|ClassNotFoundException e) {
            System.out.println("Erro de escrita no arquivo 3!");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ioex) {
                    System.out.println("Erro de escrita no arquivo 4");
                }
            }
        }
        return encontrou;
    }

}
