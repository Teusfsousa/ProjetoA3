public class Guess {
    private int numero; //numero de tentativas
    private boolean estaCorreto; //fala se o palpite esta certo
    private String proximidade; //fala se o jogador esta perto do numero

    //contrutor para comecar um palpite
    public Guess(int numero, boolean estaCorreto, String proximidade){
        this.numero = numero;
        this.estaCorreto = estaCorreto;
        this.proximidade = proximidade;
    }

    //getter para o numero de tentativas
    public int getNumero() {
        return numero;
    }
    //getter para verificar se esta correto
    public boolean estaCorreto() {
        return estaCorreto;
    }
    //getter para ver se esta perto do numero correto
    public String getProximidade() {
        return proximidade;
    }
}
