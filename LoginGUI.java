import com.game.br.bancobd.CadastroDAO;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class LoginGUI extends JFrame {
    private JTextField campoID; // Entrada do ID, para o usuário digitar
    private JPasswordField campoSenha; // Entrada da senha, para o usuário digitar
    private JButton botaoLogin; // Botão de login
    private Map<String, String> usuarios;

    public LoginGUI() {
        usuarios = new HashMap<>();
        configurarUI(); // Configuração de login
    }

    private void configurarUI() {
        setTitle("Login Number Guess");
        setSize(400, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela no meio
        setResizable(false); // Para bloquear a tela cheia do jogo

        campoID = new JTextField(15);
        campoSenha = new JPasswordField(15);
        botaoLogin = new JButton("JOGAR");
        botaoLogin.setFont(new Font("Arial", Font.BOLD, 15));
        botaoLogin.setBackground(Color.YELLOW);

        JButton botaoCadastro = new JButton("CRIAR ID");
        botaoCadastro.setBackground(Color.YELLOW);
        botaoCadastro.setFont(new Font("Arial", Font.BOLD, 15));

        botaoLogin.addActionListener(e -> processarLogin());
        botaoCadastro.addActionListener(e -> processarCadastro());

        JPanel painel = new JPanel(new GridLayout(8, 2));
        painel.add(new JLabel("Digite seu ID: "));
        painel.add(campoID);
        painel.add(new JLabel("Digite sua senha ID: "));
        painel.add(campoSenha);
        painel.add(botaoLogin);
        painel.add(botaoCadastro);
        painel.setBackground(Color.WHITE);

        getContentPane().add(painel);
    }

    private void processarCadastro() {
        String id = campoID.getText();
        String senha = new String(campoSenha.getPassword());

        if (id.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Os campos não podem estar vazios");
        } else if (usuarios.containsKey(id)) {
            JOptionPane.showMessageDialog(this, "ID já cadastrado");
        } else {
            usuarios.put(id, senha);
            JOptionPane.showMessageDialog(this, "ID criado com sucesso");

            // Inserir no banco de dados usando CadastroDAO
            CadastroDAO cadastroDAO = new CadastroDAO();
            cadastroDAO.create(id, senha);  // Adiciona o novo usuário ao banco de dados
        }
    }

    private void processarLogin() {
        String id = campoID.getText();
        String senha = new String(campoSenha.getPassword());

        if (id.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Os campos não podem estar vazios");
        } else {
            CadastroDAO cadastroDAO = new CadastroDAO();
            boolean isValidUser = cadastroDAO.validateUser(id, senha);

            if (isValidUser) {
                JOptionPane.showMessageDialog(this, "Logado com sucesso, agora vamos jogar!");
                Player jogador = new Player(id, senha, "Jogador1");
                Game jogo = new Game(jogador, 20);
                GameGUI gui = new GameGUI(jogo);
                gui.setVisible(true);
                dispose(); // Fecha a tela do login
            } else {
                JOptionPane.showMessageDialog(this, "ID ou senha incorretos.");
            }
        }
    }

    public static void main(String[] args) {
        {
            LoginGUI login = new LoginGUI();
            login.setVisible(true);

        }
    }
}

