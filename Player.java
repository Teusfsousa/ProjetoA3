public class Player extends LoginGUI {
    private String id; //id do jogador
    private String senha; //senha do jogador
    private String nome; //nome do jogador
    private int pontuacao; //pontuação do jogador
    private int tentativas; // numero de tentativas do jogador

    // construtor para inicar com senha, id e nome
    public Player (String id, String senha, String nome){
        this.id = id;
        this.senha = senha;
        this.nome = nome;
        this.pontuacao = 0;
        this.tentativas = 0;
    }

    public void setId(String id) {
        this.id = id;
    }

    // getters and setters
    public String getId() {
        return id;
    }
    public String getSenha() {
        return senha;
    }
    public String getNome() {
       return nome;
    }
    public int getPontuacao() {
        return pontuacao;
    }
    public int getTentativas() {
        return tentativas;
    }
    public void incrementarPontuacao() {
        pontuacao++;
    }
    public void incrementarTentativas() {
        tentativas++;
    }
}