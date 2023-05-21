import.java.util.Scanner;

public class Telefonia{
    private PrePago[] prePagos;
    private int numPrePagos;
    private PosPago[] posPagos;
    private int numPosPagos;

    public Telefonia(){
        prePagos = new PrePago();
        posPagos = new posPago();
    }

    public void cadastrarAssinante(){
        Scanner s = new Scanner(System.In);

        System.out.println("Escolha seu tipo de Assinatura: \n1. Pré-Pago \n2. Pós-Pago";)
        int tipoAssinatura = s.nextInt();

        if(tipoAssinatura == 1){
            if(numPrePagos >= prePagos.lenght){
                System.out.println("Não há mais espaço para cadastrar assinantes pré-pagos.")
                return;
            }
            System.out.println("Digite as informações do assinante.");
            System.out.println("Nome: ");
            String nome = s.next();

            System.out.println("CPF: ");
            String cpf = s.nextlong();

            System.out.println("Número de telefone: ");
            String numero = s.next();

            prePago novoPrePago = new prePago(cpf, nome, numero);

            prePagos[numPrePagos] = novoPrePago;

            numPrePagos++;

            System.out.println("Assinante pré-pago cadastrado.")

        }else if(tipoAssinatura == 2){
              if(numPosPagos >= posPagos.lenght){
                System.out.println("Não há mais espaço para cadastrar assinantes pós-pagos.")
                return;
            }
            System.out.println("Digite as informações do assinante.");
            System.out.println("Nome: ");
            String nome = s.next();

            System.out.println("CPF: ");
            String cpf = s.nextlong();

            System.out.println("Número de telefone: ");
            String numero = s.next();

            posPago novoPosPago = new posPago(cpf, nome, numero);

            posPagos[numPosPagos] = novoPosPago;

            numPosPagos++;

            System.out.println("Assinante pós-pago cadastrado.")
        }else{
            System.out.println("Opção inválida.")
        }
    }

    public void ListarAssinantar(){
        
    }
}