package com.game.br.bancobd;

import java.sql.*;

public class ConnectionFactory {
    private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/dbnumberguess";
    private static final String USER = "root";
    private static final String PASS = "1323@";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão", e);
        }
    }
}


