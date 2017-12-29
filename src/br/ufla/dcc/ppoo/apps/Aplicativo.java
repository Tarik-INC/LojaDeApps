package br.ufla.dcc.ppoo.apps;

import br.ufla.dcc.ppoo.miscellaneous.Comentario;
import br.ufla.dcc.ppoo.users.Usuario;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe Aplicativo.
 * Um recurso do trabalho
 * @author rafael, tarik, william
 */
public class Aplicativo implements Serializable {
    private String nome;
    private String descricao;
    private List<String> palavrasChave;
    private float nota;
    private int numAvaliacoes;
    private List<Comentario> comentarios;
    private Usuario autor;
    
    /**
     * Construtor de um novo app.
     * @param nome Nome do recurso
     * @param descricao Descrição do recurso
     * @param palavrasChave Lista de palavras chaves
     */
    public Aplicativo(String nome, String descricao, List<String> palavrasChave, Usuario autor) {
        this.nome = nome;
        this.descricao = descricao;
        this.palavrasChave = palavrasChave;
        this.nota = 0.0f;
        this.numAvaliacoes = 0;
        this.comentarios = new LinkedList();
        this.autor = autor;
    }

    /**
     * Get Nome.
     * @return Nome do recurso
     */
    public String getNome() {
        return nome;
    }

    /**
     * Get Descrição.
     * @return Descrição completa do recurso
     */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Get Descrição resumida.
     * @return Descrição resumida do recurso
     */
    public String getDescricaoFormatada() {
        if (!descricao.isEmpty()) {
            return descricao;
        }
        else {
            return "Sem descrição.";
        }
    }

    /**
     * Get Palavras-chave.
     * @return Lista imutável de palavras-chave
     */
    public List<String> getPalavrasChave() {
        return Collections.unmodifiableList(palavrasChave);
    }
    
    /**
     * Palavras-chave na forma de string.
     * @return String palavras-chave
     */
    public String getPalavrasChaveString() {
        // Isso aqui que é gambiarra seu mongol
        /*String result = palavrasChave.get(0)+", ";
        for (String palavraChave : palavrasChave) {
            palavraChave = palavraChave + ", ";
            if(!palavraChave.equals(result)) {
                result += palavraChave;
            }
        }
        return result;*/
        String out = palavrasChave.get(0);
        for (int i = 0; i < palavrasChave.size(); i++) {
            out = out.concat(", " + palavrasChave.get(i));
        }
        return out;
        
    }
    
    /**
     * Get Nota.
     * @return Valor da nota média das avaliações
     */
    public float getNota() {
        return nota;
    }
    
    /**
     * Get Nota em string para interface gráfica.
     * @return Nota formatada (Valor real ou "Sem avaliação")
     */
    public String getNotaFormatada() {
        if (nota == 0.0f) {
            return "Sem avaliação";
        }
        else {
            return String.format("%.1f", nota);
        }
    }
    
    /**
     * Cadastra nNova avaliação feita por um usuário.
     * @param nota Nota avaliada (1 a 5 estrelas)
     */
    public void novaAvaliacao(int nota) {
        this.nota = (this.nota * numAvaliacoes + nota) / (++numAvaliacoes);
    }
    
    /**
     * Adiciona novo comentário.
     * @param comentario Novo comentário
     */
    public void addComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    /**
     * Get Lista de comentários.
     * @return Lista de comentários imutável
     */
    public List<Comentario> getComentarios() {
        return Collections.unmodifiableList(comentarios);
    }
    
    /**
     * Atualiza descrição.
     * @param descricao Nova descrição
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Atualiza Nome do app.
     * @param nome Novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Atualiza palavras-chave.
     * @param palavrasChave Nova lista de palavras-chave
     */
    public void setPalavrasChave(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }
    
    /**
     * Get autor.
     * @return Nome do autor do app
     */
    public Usuario getAutor() {
        return autor;
    }
    
}
