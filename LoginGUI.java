import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class LoginGUI extends JFrame {
    private JTextField campoID; // Entrada do ID, para o usuario digitar
    private JPasswordField campoSenha; // Entrada da senha, para o usuario digitar
    private JButton botaoLogin; // Botão de login
    private Map<String, String> usuarios;

    public LoginGUI() {
        usuarios = new HashMap<>();
        configurarUI(); // Configuração de login

    }

    private void configurarUI() {
        setTitle("Login Number Guess");
        setSize(350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centralizar a janela no meio


        campoID = new JTextField(15);
        campoSenha = new JPasswordField(15);
        botaoLogin = new JButton("LOGIN");
        botaoLogin.setFont(new Font("Arial", Font.BOLD, 15));
        botaoLogin.setBackground(Color.yellow);

        JButton botaoCadastro = new JButton();
        botaoCadastro.setText("CADASTRAR");


        botaoLogin.addActionListener(e -> {
            processarLogin(); // Botão e acionado quando o "Login" for clicado
        });

        botaoCadastro.addActionListener(e -> processarCadastro());

        JPanel painel = new JPanel(new GridLayout(8, 2));
        painel.add(new JLabel("Digite seu ID: "));
        painel.add(campoID);
        painel.add(new JLabel("Digite sua senha: "));
        painel.add(campoSenha);
        painel.add(botaoLogin);
        painel.add(botaoCadastro);

        getContentPane().add(painel);
    }

    private void processarCadastro() {
        String id = campoID.getText();
        String senha = new String(campoSenha.getPassword());

        if (id.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Os campos não podem estar vazios");

        } else if (usuarios.containsKey(id)) {
            JOptionPane.showMessageDialog(this, "ID ja cadastrado");

        }else {
            usuarios.put(id, senha);
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso");
        }

    }

    private void processarLogin() {
        String id = campoID.getText();
        String senha = new String(campoSenha.getPassword());

        if (usuarios.containsKey(id) && usuarios.get(id).equals(senha)){
            JOptionPane.showMessageDialog(this, "logado com sucesso!");

            Player jogador = new Player(id, senha, "Jogador1");
            Game jogo = new Game(jogador, 20);
            GameGUI gui = new GameGUI(jogo);
            gui.setVisible(true);
            dispose(); // Fecha a tela do login
        } else {
            JOptionPane.showMessageDialog(this, "ID ou senha incorretos.");
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginGUI login = new LoginGUI();
            login.setVisible(true);
        });
    }
}
