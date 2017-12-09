package br.ufla.dcc.ppoo.users;

public  class Cadastro {
    private String login;
    private String senha;
    private String nome;

    public Cadastro(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }
    
    
}



