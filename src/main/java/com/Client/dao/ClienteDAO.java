package com.Client.dao;

import com.Client.model.Cliente;
import com.Client.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ClienteDAO {

    public void inserir(Cliente cliente) throws SQLException {
        String sqlUsuario = "INSERT INTO public.usuario (nome, telefone, email, senha, cpf, tipo_conta_id) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        String sqlCliente = "INSERT INTO public.cliente (id) VALUES (?)";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            UUID idGerado = null;

            // Insere em usuario
            try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {
                stmtUsuario.setString(1, cliente.getNome());
                stmtUsuario.setString(2, cliente.getTelefone());
                stmtUsuario.setString(3, cliente.getEmail());
                stmtUsuario.setString(4, cliente.getSenha());
                stmtUsuario.setString(5, cliente.getCpf());
                if (cliente.getTipoContaId() != null) {
                    stmtUsuario.setInt(6, cliente.getTipoContaId());
                } else {
                    stmtUsuario.setNull(6, java.sql.Types.INTEGER);
                }

                try (ResultSet rs = stmtUsuario.executeQuery()) {
                    if (rs.next()) {
                        idGerado = (UUID) rs.getObject("id");
                        cliente.setId(idGerado);
                    }
                }
            }

            if (idGerado == null) {
                throw new SQLException("Falha ao obter o ID gerado para o usuário.");
            }

            // Insere em cliente
            try (PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente)) {
                stmtCliente.setObject(1, idGerado);
                stmtCliente.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
