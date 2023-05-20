public class Assinante {
    private long cpf;
    private String nome;
    private String numero;
    private Chamada[] chamadas;
    private int numChamadas;
    
    public Assinante(long cpf, String nome, String numero) {
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        this.chamadas = new Chamada[100]; // Tamanho inicial do vetor de chamadas (pode ser ajustado conforme necessário)
        this.numChamadas = 0;
    }
    
    public long getCpf() {
        return cpf;
    }
    
    public String toString() {
        return "CPF: " + cpf + ", Nome: " + nome + ", Número: " + numero;
    }
}