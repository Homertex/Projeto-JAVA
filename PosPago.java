import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class PosPago extends Assinante {
    private double assinatura;

    public PosPago(long cpf, String nome, String numero, double assinatura) {
        super(cpf, nome, numero);
        this.assinatura = assinatura;
    }   

    public void fazerChamada(GregorianCalendar data, int duracao) {
        
        if (numChamadas < chamadas.length) {
        	Chamada chamada = new Chamada(data, duracao);
        	chamadas[numChamadas] = chamada;
            numChamadas++;
            System.out.println("Chamada registrada com sucesso");
        } else {
            System.out.println("Não é possível registrar a chamada. Limite de chamadas atingido");
        }
    }

    public void imprimirFatura(int mes) {
        double totalCustoChamadas = 0.0f;

        System.out.println("Fatura do mês: " + mes);
        System.out.println("CPF: " + getCpf());
        System.out.println(toString());
        System.out.println("Chamadas realizadas no mês:");

        for (int i = 0; i < numChamadas; i++) {
            Chamada chamada = chamadas[i];
            int chamadaMes = chamada.getData().get(GregorianCalendar.MONTH);
            if (chamadaMes == mes) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = dateFormat.format(chamada.getData().getTime());

                System.out.println("Data: " + formattedDate);
                System.out.println("Duração: " + chamada.getDuracao() + " minutos");

                double custoChamada = 1.04 * chamada.getDuracao();
                totalCustoChamadas += custoChamada;
            }
        }

        System.out.println("Valor total da fatura: " + (assinatura + totalCustoChamadas));
    }
}