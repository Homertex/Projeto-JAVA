import java.util.GregorianCalendar;
public class PrePago extends Assinante {
    private int numRecargas;
    private int creditos;
    private Recarga[] recargas;

    public PrePago(float recargas, int numRecargas, int creditos, long cpf, String nome, String numero){
        super(cpf, nome, numero);
        this.recargas = new Recarga[100];
        this.numRecargas = 0;
        this.creditos = 0;
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

    public void recarregar(GregorianCalendar data, float valor) {
        Recarga recarga = new Recarga(data, valor);

        if (numRecargas < 100) {
            recargas[numRecargas] = recarga;
            numRecargas++; 
            creditos += valor;
            System.out.println("RECARREGADO");
        } else {
            System.out.println("LIMITE ALCANÇADO, RECARGA NÃO FINALIZADA");
        }
    }
    
    }

    public void imprimirFatura(int mes) {
        double totalCustoChamadas = 0.0;
        float totalValorRecargas = 0.0f;

        System.out.println("Fatura do mês: " + mes);
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Número: " + getNumero());
        System.out.println("Chamadas realizadas no mês:");

        for (int i = 0; i < getNumChamadas(); i++) {
            Chamada chamada = getChamada(i);
            int chamadaMes = chamada.getData().get(GregorianCalendar.MONTH);
            if (chamadaMes == mes) {
                double custoChamada = 1.45 * chamada.getDuracao();
                totalCustoChamadas += custoChamada;

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = dateFormat.format(chamada.getData().getTime());

                System.out.println("Data: " + formattedDate);
                System.out.println("Duração: " + chamada.getDuracao() + " minutos");
                System.out.println("Valor: R$" + custoChamada);
            }
        }

}