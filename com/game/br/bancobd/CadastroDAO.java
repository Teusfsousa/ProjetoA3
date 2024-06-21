package com.game.br.bancobd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastroDAO {

    public void create(String id, String senha) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        //Aqui está o banco de dados de cadsatro, onde todas a informações do usuario vai vir para cá

        try {
            stmt = conn.prepareStatement("INSERT INTO Users (id, senha) VALUES (?, ?)");
            stmt.setString(1, id);
            stmt.setString(2, senha);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados", e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean validateUser(String id, String senha) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("SELECT * FROM Users WHERE id = ? AND senha = ?");
            stmt.setString(1, id);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            return rs.next(); // Retorna true se o usuário for encontrado

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao validar usuário", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
