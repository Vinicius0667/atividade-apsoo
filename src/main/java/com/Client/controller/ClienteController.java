package com.Client.controller;

import com.Client.dao.ClienteDAO;
import com.Client.model.Cliente;

import java.sql.SQLException;

public class ClienteController {
    
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void cadastrarCliente(String nome, String telefone, String email, String senha, String cpf) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome é obrigatório.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("O e-mail é obrigatório.");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new Exception("A senha é obrigatória.");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setSenha(senha);
        cliente.setCpf(cpf);

        try {
            clienteDAO.inserir(cliente);
        } catch (SQLException e) {
            // Pode fazer tratamento de erro específico para duplicidade de email ou cpf, etc.
            throw new Exception("Erro ao cadastrar no banco de dados: " + e.getMessage(), e);
        }
    }
}
