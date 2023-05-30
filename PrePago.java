import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class PrePago extends Assinante {
	private Recarga[] recargas;
    private int numRecargas;
    private float creditos;

    public PrePago(long cpf, String nome, String numero){
        super(cpf, nome, numero);
        this.recargas = new Recarga[100];
        this.numRecargas = 0;
        this.creditos = 0;
    }

    public void fazerChamada(GregorianCalendar data, int duracao) {
        
        double custoChamada = 1.45 * duracao;
        
        if(numChamadas >= 100) {
        	System.out.println("ESPAÇO DE ARMAZENAMENTO DE CHAMADAS INSUFICIENTE");
        	return;
        }
        
        if(creditos >= custoChamada) {
        	Chamada chamada = new Chamada(data, duracao);
        	chamadas[numChamadas] = chamada;
        	numChamadas++;
        	creditos -= custoChamada;
        	System.out.println("CHAMADA CRIADA COM SUCESSO.");
        } else {
        	System.out.println("SALDO INSUFICIENTE");
        }
    }

    public void recarregar(GregorianCalendar data, float valor) {
    	if (numRecargas >= recargas.length) {
    		System.out.println("LIMITE ALCANÇADO, RECARGA NÃO FINALIZADA");
    		return;
    	}
    	
    	Recarga recarga = new Recarga(data, valor);
    	recargas[numRecargas] = recarga;
        numRecargas++; 
        creditos += valor;
        System.out.println("RECARREGADO");
    }

    public void imprimirFatura(int mes) {
        double totalCustoChamadas = 0.0;
        float totalValorRecargas = 0.0f;

        System.out.println("Fatura do mês: " + mes);
        System.out.println("CPF: " + getCpf());
        System.out.println(toString());
        System.out.println("Chamadas realizadas no mês:");

        for (int i = 0; i < numChamadas; i++) {
            Chamada chamada = chamadas[i];
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
        System.out.println("Recargas realizadas no mês:");
        for (int i = 0; i < numRecargas; i++) {
            Recarga recarga = recargas[i];
            int recargaMes = recarga.getData().get(GregorianCalendar.MONTH);
            if (recargaMes == mes) {
                totalValorRecargas += recarga.getValor();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = dateFormat.format(recarga.getData().getTime());

                System.out.println("Data: " + formattedDate);
                System.out.println("Valor: R$" + recarga.getValor());
            }
        }   
    }
    
}