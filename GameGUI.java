import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameGUI extends LoginGUI{
    private Game jogo;
    private JTextField campoPalpite;
    private JTextArea areaSaida;
    private JButton botaoPalpite;
    private JButton botaoReiniciar;

    public GameGUI(Game jogo) {
        this.jogo = jogo;
        configurarUI();
    }

    private void configurarUI() {
        setTitle("Number Guess");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        campoPalpite = new JTextField(5);
        botaoPalpite = new JButton("Continue");
        areaSaida = new JTextArea(10, 30);
        areaSaida.setEditable(false);

        botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.setEnabled(false);

        botaoPalpite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processarPalpite();
            }
        });

        botaoReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJogo();
            }
        });

        JPanel painelEntrada = new JPanel();
        painelEntrada.add(new JLabel("Digite seu palpite: "));
        painelEntrada.add(campoPalpite);
        painelEntrada.add(botaoPalpite);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoReiniciar);

        JPanel painelSaida = new JPanel();
        painelSaida.add(new JScrollPane(areaSaida));

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);
        getContentPane().add(painelBotoes, BorderLayout.SOUTH);
    }

    private void processarPalpite() {
        try {
            int numeroPalpite = Integer.parseInt(campoPalpite.getText());
            if (numeroPalpite < 1 || numeroPalpite > 100) {
                areaSaida.append("Por favor, insira um valor entre 1 e 100\n");
                return;
            }

            Guess palpite = jogo.fazerPalpite(numeroPalpite);
            if (palpite.estaCorreto()) {
                areaSaida.append("Parabéns!" + " " + jogo.getJogador().getId() + " " + "Você acertou o número!\n");
                campoPalpite.setEnabled(false);
                botaoPalpite.setEnabled(false);
                botaoReiniciar.setEnabled(true);
            } else {
                areaSaida.append("Palpite errado: " + palpite.getNumero() + ". " + palpite.getProximidade() + "\n");
            }

            if (jogo.jogoAcabou()) {
                if (jogo.jogadorGanhou()) {
                    areaSaida.append("Parabéns!" +  " " + jogo.getJogador().getId() + " " + "Você ganhou o jogo!\n");
                } else {
                    areaSaida.append("Fim de jogo! Você usou todas as tentativas.\n");
                }

                campoPalpite.setEnabled(false);
                botaoPalpite.setEnabled(false);
                botaoReiniciar.setEnabled(true);
            }
        } catch (NumberFormatException ex) {
            areaSaida.append("Por favor, insira um valor válido!\n");
        }
    }

    private void reiniciarJogo() {
        jogo.resetarJogo();
        campoPalpite.setEnabled(true);
        botaoPalpite.setEnabled(true);
        botaoReiniciar.setEnabled(false);
        areaSaida.setText("");
        campoPalpite.setText("");
    }

    public static void main(String[] args) {
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
    }
}
