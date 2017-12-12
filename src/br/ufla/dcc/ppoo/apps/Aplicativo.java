package br.ufla.dcc.ppoo.apps;

import br.ufla.dcc.ppoo.miscellaneous.Comentario;
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
    
    /**
     * Construtor de um novo app.
     * @param nome Nome do recurso
     * @param descricao Descrição do recurso
     * @param palavrasChave Lista de palavras chaves
     */
    public Aplicativo(String nome, String descricao, List<String> palavrasChave) {
        this.nome = nome;
        this.descricao = descricao;
        this.palavrasChave = palavrasChave;
        this.nota = 0.0f;
        this.numAvaliacoes = 0;
        this.comentarios = new LinkedList();
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
    public String getDescricaoResumido() {
        if (descricao.length() > 15) {
            return descricao.substring(0, 15) + "...";
        }
        else if (!descricao.isEmpty()) {
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
        String result = palavrasChave.get(0)+", ";
        for (String palavraChave : palavrasChave) {
            palavraChave = palavraChave + ", ";
            if(!palavraChave.equals(result)) {
                result += palavraChave;
            }
        }
        return result;
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
    public String getNotaString() {
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
     * Tamanho da lista de comentários.
     * @return Tamanho
     */
    public int getComentariosSize() {
        return comentarios.size();
    }

    /**
     * Get i-ésimo comentário 
     * @param i Índice
     * @return Referência para o comentário
     */
    public String getComentario(int i) {
        String comentario = comentarios.get(i).getUsuario() + ": " + comentarios.get(i).getComentario();
        return comentario;
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
     * Atualiza palavras-chave
     * @param palavrasChave Nova lista de palavras-chave
     */
    public void setPalavrasChave(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }
    
}
