package com.Client.model;

import java.util.UUID;

public class Cliente extends Usuario {
    
    public Cliente() {
        super();
        this.setTipoContaId(1);
    }

    public Cliente(UUID id, String nome, String telefone, String email, String senha, String cpf) {
        super(id, nome, telefone, email, senha, cpf, 1);
    }
}
