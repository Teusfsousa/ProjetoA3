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

public class GameGUI extends JFrame {
    private Game jogo; //instancia do jogo
    private JTextField campoPalpite; // campo da entrada do palpite
    private JTextArea areaSaida; // area de saida para mostrar os resultados
    private JButton botaoPalpite;
    private JButton botaoReiniciar;

    public GameGUI(Game jogo){
        this.jogo = jogo;
        configurarUI(); //config interface
    }

    private void configurarUI(){
        setTitle("Number Guess");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        campoPalpite = new JTextField(5); //campo de texto para inserir o palpite
        botaoPalpite = new JButton("Continue"); //botao para dar o palpite
        areaSaida = new JTextArea(10,30); // area para mostrar os resultados
        areaSaida.setEditable(false);

        botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.setEnabled(false); //o botao reiniciar começa desativado

        botaoPalpite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                processarPalpite(); //verifica o palpite quando o botão for clicado
            }
        });

        botaoReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
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

    private void processarPalpite(){
        try {
            int numeroPalpite = Integer.parseInt(campoPalpite.getText()); // pega o numero do palpite
            //verificando se o valor digitado esta correto
            if (numeroPalpite < 1 || numeroPalpite > 100) {
                areaSaida.append("Por favor insira um valor entre 1 e 100\n");
                return;
            }
            Guess palpite = jogo.fazerPalpite(numeroPalpite); // faz o palpite no jogo


            if (palpite.estaCorreto()) {
                areaSaida.append("Parabéns! Você acertou o número!\n"); //mensagem se estiver correto
                campoPalpite.setEnabled(false); // bloqueia o campo de texto após acertar o número
                botaoPalpite.setEnabled(false);//bloqueia o botão palpite
                botaoReiniciar.setEnabled(true); //habilita o botão reiniciar
            }else{
                areaSaida.append("Palpite errado: " + palpite.getNumero() + ". " + palpite.getProximidade() + "\n");
                if (jogo.jogoAcabou()) {
                    if (jogo.jogadorGanhou()) {
                        areaSaida.append("Parabéns, " + jogo.getJogador().getNome() + "! Você ganhou o jogo!\n"); // mensagem de vitoria
                    }else{
                        areaSaida.append("Fim de jogo! Você usou todas as tentativas.\n"); // mensagem de fim de jogo
                    }
                    campoPalpite.setEnabled(false); // desabilita o campo de texto após o fim do jogo
                    botaoPalpite.setEnabled(false);
                    botaoReiniciar.setEnabled(true);// habilita o botao reiniciar
                }
            }
        } catch (NumberFormatException ex){
            areaSaida.append("Por favor, insira um valor válido!\n"); //mensagem caso o jogador coloque um valor indavlido
        }
    }

    private void reiniciarJogo(){
        jogo.resetarJogo(); //reinicar jogo
        campoPalpite.setEnabled(true);//habilita o campo texto
        botaoPalpite.setEnabled(true); //habilita o botao palpite
        botaoReiniciar.setEnabled(false); //desabilita o botao reinicar
        areaSaida.setText("");//limpa a area de saída
        campoPalpite.setText("");//limpa o campo de palpite
    }

    public static void main(String[] args){
        //executar o programa e começar na classe LoginGUI
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
    }
}