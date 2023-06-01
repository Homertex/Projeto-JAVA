import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class Telefonia{
    private PrePago[] prePagos;
    private int numPrePagos;
    private PosPago[] posPagos;
    private int numPosPagos;

    public Telefonia(){
        prePagos = new PrePago[2];
        numPrePagos = 0;
        posPagos = new PosPago[2];
        numPosPagos = 0;
    }

    public void cadastrarAssinante(){
        Scanner s = new Scanner(System.in);

        System.out.println("Escolha seu tipo de Assinatura: \n1. Pré-Pago \n2. Pós-Pago");
        int tipoAssinatura = s.nextInt();

        if(tipoAssinatura == 1){
            if(numPrePagos >= prePagos.length){
                System.out.println("Não há mais espaço para cadastrar assinantes pré-pagos.");
                return;
            }
            System.out.println("Digite as informações do assinante.");
            System.out.println("CPF: ");
            long cpf = s.nextLong();
            
            System.out.println("Nome: ");
            String nome = s.next();

            System.out.println("Número de telefone: ");
            String numero = s.next();

            prePagos[numPrePagos] = new PrePago(cpf, nome, numero);
            numPrePagos++;

            System.out.println("Assinante pré-pago cadastrado.");

        }else if(tipoAssinatura == 2){
              if(numPosPagos >= posPagos.length){
                System.out.println("Não há mais espaço para cadastrar assinantes pós-pagos.");
                return;
            }
            System.out.println("Digite as informações do assinante.");
            System.out.println("CPF: ");
            long cpf = s.nextLong();

            System.out.println("Nome: ");
            String nome = s.next();
            
            System.out.println("Número de telefone: ");
            String numero = s.next();
            
            System.out.println("Valor da Assinatura: ");
            Double assinatura = s.nextDouble();

            posPagos[numPosPagos] = new PosPago(cpf, nome, numero, assinatura);
            numPosPagos++;

            System.out.println("Assinante pós-pago cadastrado.");
        }else{
            System.out.println("Opção inválida.");
        }
    }

    public void ListarAssinante(){
    	System.out.println("Assinantes Pré-Pagos: ");
    	for(int i = 0; i < numPrePagos; i++) {
    		PrePago assinante = prePagos[i];
    		System.out.println("CPF: " + assinante.getCpf());
    		System.out.println(assinante.toString());
		}
    	System.out.println("Assinantes Pós-Pagos: ");
    	for(int i = 0; i < numPosPagos; i++) {
    		PosPago assinante = posPagos[i];
    		System.out.println("CPF: " + assinante.getCpf());
    		System.out.println(assinante.toString());
		}
    }
    
    public void fazerChamada() {
    	Scanner s = new Scanner(System.in);
    	
    	System.out.println("Escolha seu tipo de Assinatura: \n1. Pré-Pago \n2. Pós-Pago");
        int tipoAssinatura = s.nextInt();
        
        System.out.println("Digite o CPF do assinante: ");
        long cpf = s.nextLong();
        
        Assinante assinante = null;
        
        if(tipoAssinatura == 1) {
        	assinante = localizarPrePago(cpf);
        }else if(tipoAssinatura == 2) {
        	assinante = localizarPosPago(cpf);
        }else {
        	System.out.println("Opção inválida.");
        }
        
        if(assinante != null) {
        	System.out.println("Digite a duração da chamada em minutos: ");
        	int duracao = s.nextInt();
        	
        	System.out.println("Digite a data da chamada(dd/MM/yyyy): ");
        	String dataString = s.next();
        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        	GregorianCalendar data = new GregorianCalendar();
        	try {
        		data.setTime(dateFormat.parse(dataString));
        		
        	}catch(ParseException e) {
        		System.out.println("Formato de data inválido.");
        		return;
        	}
        	
        	if(tipoAssinatura == 1) {
        		((PrePago) assinante).fazerChamada(data, duracao);
        	}else if(tipoAssinatura == 2) {
        		((PosPago) assinante).fazerChamada(data, duracao);
        	}
        }else {
        	System.out.println("Assinante não encontrado.");
        }
        
    }
    
    public void fazerRecarga() {
    	Scanner s = new Scanner(System.in);
    	
    	System.out.println("Digite o CPF do assinante pré-pago: ");
    	long cpf = s.nextLong();
    	
    	PrePago assinantePrePago = localizarPrePago(cpf);
    	
    	if(assinantePrePago != null) {
    		System.out.println("Digite o valor da recarga em reais: ");
    		float valor = s.nextFloat();
    		
    		System.out.println("Digite a data da recarga(dd/MM/yyyy): ");
    		String dataString = s.next();
    		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		GregorianCalendar data = new GregorianCalendar();
    		try {
    			data.setTime(dateFormat.parse(dataString));
    		}catch(ParseException e) {
    			System.out.println("Formato de data inválido.");
    			return;
    		}
    		
    		assinantePrePago.recarregar(data, valor);
    	}else {
    		System.out.println("Assinante pré-pago não encontrado");
    	}
    }
    
    private PrePago localizarPrePago(long cpf){
    	for(int i = 0; i < numPrePagos; i++) {
    		if(prePagos[i].getCpf() == cpf) {
    			return prePagos[i];
    		}	
    	}
    	return null;
    }
    
    private PosPago localizarPosPago(long cpf) {
    	for(int i = 0; i < numPosPagos; i++) {
    		if(posPagos[i].getCpf() == cpf) {
    			return posPagos[i];
    		}
    	}
    	return null;
    }
    
    public void imprimirFaturas() {
    	Scanner s = new Scanner(System.in);
    	
    	System.out.println("Digite o mês (1-12): ");
    	int mes = s.nextInt();
    	
    	System.out.println("Faturas do mês " + mes + ":");
    	
		System.out.println("Assinantes pré-pagos: ");
    	
    	for(int i = 0; i < numPrePagos; i++) {
    		PrePago assinantePrePago = prePagos[i];
    		System.out.println(assinantePrePago.toString());
    		assinantePrePago.imprimirFatura(mes);	
    	}
    	
		System.out.println("Assinantes pós-pagos: ");
    	
    	for(int i = 0; i < numPosPagos; i++) {
    		PosPago assinantePosPago = posPagos[i];
    		System.out.println(assinantePosPago.toString());
    		assinantePosPago.imprimirFatura(mes);	
    	}
    }
    
    public static void main(String[] args) {
		
    	Telefonia telefonia = new Telefonia();
    	Scanner s = new Scanner(System.in);
    	int opcao;
    	
    	do {
    		System.out.println("TELEFONIA");
    		System.out.println("1 - CADASTRAR ASSINANTE.");
    		System.out.println("2 - LISTAR ASSINANTES.");
    		System.out.println("3 - FAZER CHAMADA.");
    		System.out.println("4 - FAZER RECARGA.");
    		System.out.println("5 - IMPRIMIR FATURAS.");
    		System.out.println("6 - SAIR.");
    		opcao = s.nextInt();
    		
    		switch(opcao) {
    		case 1:
    			telefonia.cadastrarAssinante();
    			break;
    		case 2:
    			telefonia.ListarAssinante();
    			break;
    		case 3:
    			telefonia.fazerChamada();
    			break;
    		case 4:
    			telefonia.fazerRecarga();
    			break;
    		case 5:
    			telefonia.imprimirFaturas();
    			break;
    		case 6:
    			System.out.println("Programa encerrado.");
    			break;
    		default:
    			System.out.println("Opção inválida.");
    		}
    		
    		System.out.println();
    	}while(opcao != 6);
	}
    
}