package br.ufla.dcc.ppoo.modeling;

import br.ufla.dcc.ppoo.exceptions.AvaliacaoNotaInvalidaException;
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
    private List<Avaliacao> avaliacoes;
    
    /**
     * Construtor de um novo app.
     * @param nome Nome do recurso
     * @param descricao Descrição do recurso
     * @param palavrasChave Lista de palavras chaves
     * @param autor Usuário dono do app
     */
    public Aplicativo(String nome, String descricao, List<String> palavrasChave, Usuario autor) {
        this.nome = nome;
        this.descricao = descricao;
        this.palavrasChave = palavrasChave;
        this.nota = 0.0f;
        this.numAvaliacoes = 0;
        this.comentarios = new LinkedList();
        this.autor = autor;
        this.avaliacoes = new LinkedList();
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
     * @return Descrição do recurso
     */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Get Descrição formatada para interface gráfica.
     * @return Descrição do recurso ou "sem descrição" se estiver vazia.
     */
    public String getDescricaoFormatada() {
        if (!descricao.isEmpty()) {
            return descricao;
        }
        else {
            return "(Sem descrição)";
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
        String out = palavrasChave.get(0);
        for (int i = 1; i < palavrasChave.size(); i++) {
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
     * Cadastra nova avaliação feita por um usuário.
     * @param nota Nota avaliada (1 a 5 estrelas)
     */
    private void novaAvaliacao(int nota) {
        if (nota > 5 || nota < 1) {
            throw new AvaliacaoNotaInvalidaException("Esperado valor de nota entre 1 e 5.");
        }
        
        this.nota = (this.nota * numAvaliacoes + nota) / (++numAvaliacoes);
    }
    
    /**
     * Remove avaliação feita por um usuário.
     * @param nota Nota avaliada (1 a 5 estrelas)
     */
    private void removeAvaliacao(int nota) {
        if (nota > 5 || nota < 1) {
            throw new AvaliacaoNotaInvalidaException("Esperado valor de nota entre 1 e 5.");
        }
        
        if (numAvaliacoes >= 2) {
            this.nota = (this.nota * numAvaliacoes - nota) / (--numAvaliacoes);
        }
        else { 
            this.nota = 0;
            numAvaliacoes = 0;
        }
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
    
    /**
     * Get lista Avaliações.
     * @return Lista imutável de usuário que já avaliaram
     */
    public List<Avaliacao> getAvaliacoes() {
        return Collections.unmodifiableList(avaliacoes);
    }
    
    /**
     * Método para avaliar o app, insere nova avaliação ou modifica se já existir.
     * @param user Usuário que avaliou
     * @param nota Nota da avalição
     */
    public void setNotaAvaliacao(Usuario user, int nota) {
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getUsuario().equals(user)) {
                removeAvaliacao(avaliacao.getNota());
                avaliacao.setNota(nota);
                novaAvaliacao(nota);
                return;
            }
        }
        
        avaliacoes.add( new Avaliacao(user, nota) );
        novaAvaliacao(nota);
    }
    
    /**
     * Get Nota que o usuário deu para o app.
     * @param user Usuário a ser buscado
     * @return Valor da nota dada, se não for econtrada, 0
     */
    public int getNotaUsuario(Usuario user) {
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getUsuario().equals(user)) {
                return avaliacao.getNota();
            }
        }
        
        return 0;
    }
    
    /**
     * Verifica se uma palavra chave está contida no nome do app, para buscas.
     * @param string Palavra chave a ser buscada
     * @return Verdadeiro ou Falso
     */
    public boolean nomeContem(String string) {
        String key = string.toLowerCase();
        
        return nome.toLowerCase().contains(key);
    }
    
    /**
     * Verifica se uma palavra chave está contida nas palavras-chave, para buscas.
     * @param string Palavra chave a ser buscada
     * @return Verdadeiro ou Falso
     */
    public boolean palavrasChaveContem(String string) {
        String key = string.toLowerCase();
        
        for (String palavra : palavrasChave) {
            if (palavra.toLowerCase().contains(key)) {
                return true;
            }
        }
        
        return false;
    }
    
}
