import com.game.br.bancobd.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cadastroDAO {

    public void create(){
        Connection conn = ConnectionFactory.getConnectio();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
