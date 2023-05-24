public class PrePago{
    private float recargas;
    private int numRecargas;
    private int creditos;
    private Recarga[] recargas;

    public PrePago(float recargas, int numRecargas, int creditos, long cpf, String nome, String numero){
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        this.recargas = new Recarga[100];
    }
}