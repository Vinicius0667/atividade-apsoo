package com.Client.model;

import java.util.UUID;

public class Usuario {
    private UUID id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private String cpf;
    private Integer tipoContaId;

    public Usuario() {}

    public Usuario(UUID id, String nome, String telefone, String email, String senha, String cpf, Integer tipoContaId) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.tipoContaId = tipoContaId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getTipoContaId() {
        return tipoContaId;
    }

    public void setTipoContaId(Integer tipoContaId) {
        this.tipoContaId = tipoContaId;
    }
}
