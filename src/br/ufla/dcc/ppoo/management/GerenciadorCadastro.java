/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.ppoo.management;

import br.ufla.dcc.ppoo.users.Cadastro;
import br.ufla.dcc.ppoo.users.Cadastro;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tarik
 */
public class GerenciadorCadastro {

    private List<Cadastro> cadastros;
    private File file = new File("ArqDeCadastro.txt");

    public GerenciadorCadastro() {
        try {
            IniciarCadastros();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    private void IniciarCadastros() throws IOException {

        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedReader br = null;

        cadastros = new ArrayList<Cadastro>();
        br = new BufferedReader(new FileReader(file));
        String linha = br.readLine();
        while (linha != null) {
            String[] atributos = linha.split(",");
            Cadastro cadastro = new Cadastro(atributos[0], atributos[1], atributos[2]);
            cadastros.add(cadastro);
            linha = br.readLine();

            br.close();

        }

    }

    public void SalvarCadastrosArq() throws IOException {

        FileWriter fw = new FileWriter(file);

        // essa droga apaga tudo de uma vez, sou ignorante mesmo        
        fw.write("");
        for (Cadastro cadastro : cadastros) {

            fw.write(String.format("%s,%s,%s\n", cadastro.getLogin(),
                    cadastro.getNome(), cadastro.getSenha()));

        }

    }

    public void AdicionarCadastro(Cadastro cads) {

        cadastros.add(cads);

    }

}
