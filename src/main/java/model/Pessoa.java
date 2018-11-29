package model;

import java.time.LocalDate;

public class Pessoa {

    private String nome;
    private String sobrenome;
    private LocalDate dataDeNascimento;
    private String login;
    private String senha;

    public Pessoa(Builder builder){
        this.nome = builder.nome;
        this.sobrenome = builder.sobrenome;
        this.dataDeNascimento = builder.dataDeNascimento;
        this.login = builder.login;
        this.senha = builder.senha;
    }

    public String getSobrenome() {
        return sobrenome;
    }


    public String getNome() {
        return nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getLogin() {
        return login;
    }


    public String getSenha() {

        return senha;
    }
    public static class Builder{

        private String nome;
        private String sobrenome;
        private LocalDate dataDeNascimento;
        private String login;
        private String senha;


        public Builder comNome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder comSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
            return this;
        }

        public Builder comDataDeNascimento(LocalDate dataDeNascimento) {
            this.dataDeNascimento = dataDeNascimento;
            return this;
        }

        public Builder comLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder comSenha(String senha) {
            this.senha = senha;
            return this;
        }

        public Pessoa build(){
            return new Pessoa(this);
        }

    }
}
