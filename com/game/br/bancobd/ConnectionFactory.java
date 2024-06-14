package com.game.br.bancobd;

import java.sql.*;

public class ConnectionFactory {
    private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/dbnumberguess";
    private static final String USER = "root";
    private static final String PASS = "1323@";

    public static Connection getConnectio(){

        try {
            Class.forName(DRIVE);
            try {
                return DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException e) {
                throw new RuntimeException("Erro na conexão",e);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void closeConnection(Connection conn, PreparedStatement stmt){

        closeConnection(conn);

        try {
            if (stmt != null ){
                stmt.close();
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){

        closeConnection(conn, stmt);

        try {
            if (rs != null){
                rs.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


