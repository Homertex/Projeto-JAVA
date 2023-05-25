public class PrePago{
    private float recargas;
    private int numRecargas;
    private int creditos;
    private Recarga[] recargas;

    public PrePago(float recargas, int numRecargas, int creditos, long cpf, String nome, String numero){
        super(cpf, nome, numero);
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        this.recargas = new Recarga[100];
    }

     public void fazerChamada(GregorianCalendar data, int duracao) {
        Chamada chamada = new Chamada(data, duracao);
        double custoChamada = 1.45 * duracao;

        if (getNumChamadas() < 100 && creditos >= custoChamada) {
            realizarChamada(chamada);
            creditos -= custoChamada;
            System.out.println("SUCESSO");
        } else {
            System.out.println("SALDO INSUFICIENTE");
        }
    }



}