package com.game.br.bancobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Atualizado para o driver correto
    private static final String URL = "jdbc:mysql://localhost:3306/dbnumberguess";
    private static final String USER = "root";
    private static final String PASS = "1323@";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER); // Carrega o driver do banco de dados
            return DriverManager.getConnection(URL, USER, PASS); // Cria e retorna a conexão
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do banco de dados não encontrado", e);
        }
    }
}
