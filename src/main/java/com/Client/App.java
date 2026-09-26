package com.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        System.out.println("Tentando conectar ao banco de dados...");

        String url = System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://db:5432/client");
        String user = System.getenv().getOrDefault("DB_USER", "postgres");
        String password = System.getenv().getOrDefault("DB_PASSWORD", "postgres");

        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            if (conexao != null && !conexao.isClosed()) {
                System.out.println("SUCESSO! Conectado ao PostgreSQL com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("FALHA na conexão com o banco de dados:");
            e.printStackTrace();
        }
    }
}