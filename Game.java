import java.util.Random;

public class Game {
    private Player jogador; // usuário do jogo
    private int numeroAlvo; // número que precisa adivinhar
    private int tentativasRestantes; // número de tentativas restantes
    private int maxTentativas; // número de tentativas permitidas
    private boolean jogadorGanhou; // indica se o jogador ganhou

    public Game(Player jogador, int maxTentativas) {
        this.jogador = jogador;
        this.maxTentativas = maxTentativas;
        resetarJogo();
    }

    public Player getJogador() {
        return jogador;
    }

    public int getMaxTentativas() {
        return maxTentativas;
    }

    public void resetarJogo() {
        this.tentativasRestantes = maxTentativas; // Reseta o número de tentativas
        this.jogadorGanhou = false;
        gerarNovoNumeroAlvo(); // Gera um novo número alvo
    }

    public Guess fazerPalpite(int numeroPalpite) {
        tentativasRestantes--;
        boolean estaCorreto = (numeroPalpite == numeroAlvo); // verifica se o palpite está correto
        if (estaCorreto) {
            jogadorGanhou = true;
        }
        String proximidade = calcularProximidade(numeroPalpite); // calcula a proximidade
        return new Guess(numeroPalpite, estaCorreto, proximidade);
    }

    public boolean jogoAcabou() {
        return tentativasRestantes <= 10 || jogadorGanhou;

    }

    public boolean jogadorGanhou() {
        return jogadorGanhou;
    }

    private void gerarNovoNumeroAlvo() {
        Random rand = new Random();
        this.numeroAlvo = rand.nextInt(100) + 1; // novo número de 1 a 100
    }

    private String calcularProximidade(int numeroPalpite) {
        int diferenca = Math.abs(numeroPalpite - numeroAlvo);
        if (diferenca == 0) {
            return "Correto!";
        } else if (diferenca <= 5) {
            return "Muito quente!";
        } else if (diferenca <= 10) {
            return "Quente!";
        } else if (diferenca <= 20) {
            return "Morno!";
        } else {
            return "Frio!";
        }
    }
}
