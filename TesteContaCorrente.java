import java.util.ArrayList;
import java.util.Scanner;

public class TesteContaCorrente {
    
    public static ArrayList<ContaCorrente> clientes = new ArrayList<ContaCorrente>();
    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int op = 0;

        do{
            System.out.println("\n[1] Cadastro");
            System.out.println("[2] Depósito");
            System.out.println("[3] Saque");
            System.out.println("[4] Ver saldo");
            System.out.println("[5] Somatório dos clientes que não estão no especial");
            System.out.println("[6] Sair");

            op = (Integer.parseInt(s.nextLine()));

            if(op==1){
                ContaCorrente c = new ContaCorrente();

                c.setNumero(clientes.size() + 1001);
                c.setSaldo(0);
                
                System.out.println("\nDigite seu nome:");
                c.setNome(s.nextLine());

                do{
                    System.out.println("\nDigite o limite do especial:");
                    c.setLimite(Double.parseDouble(s.nextLine()));

                    if(c.getLimite() < 0){
                        System.out.println("\nValor inválido!");
                    }

                }while(c.getLimite() < 0);

                clientes.add(c);

                System.out.println("\nO número da sua conta é: " + c.getNumero());
            }
            else if(op==2){
                ContaCorrente cont;
                double dep;

                cont = getClienteByNumero();
                
                if(cont != null){

                    do{
                        System.out.println("\nDigite o valor a ser depositado:");
                        dep = (Double.parseDouble(s.nextLine()));

                        if(dep < 0){
                            System.out.println("\nValor inválido!");
                        }
                    }while(dep < 0);
                    
                    cont.setSaldo(cont.getSaldo() + dep);
                }
            }
            else if(op == 3)
            {
                ContaCorrente cont;
                double saque;

                cont = getClienteByNumero();
                
                if(cont != null){
                    do{
                        System.out.println("\nDigite o valor a ser sacado:");
                        saque = (Double.parseDouble(s.nextLine()));

                        if(saque < 0){
                            System.out.println("\nValor inválido!");
                        }

                    }while(saque < 0);
                                    
                    if(cont.getSaldo() + cont.getLimite() < saque){
                        System.out.println("\nO valor digitado ultrapassou seu limite do especial\n");
                        System.out.println("Foi possível sacar apenas R$" + (cont.getSaldo() + cont.getLimite()));
                        cont.setSaldo(cont.getLimite() * (-1));                        
                    }
                    else{
                        cont.setSaldo(cont.getSaldo() - saque);
                    }

                    if(cont.getSaldo() < 0){
                        System.out.println("Seu saldo está no especial!");
                    }
                }
            }
            else if(op == 4)
            {
                ContaCorrente cont;

                cont = getClienteByNumero();
                
                if(cont != null){
                    System.out.println("\nConta: " + cont.getNumero());
                    System.out.println("Nome: " + cont.getNome());
                    System.out.println("Saldo: R$" + cont.getSaldo());
                }
            }
            else if(op == 5)
            {
                System.out.println("\nSomatório do saldo das contas que não estão no especial:\nR$" + somaNaoEspecial());
            }

        }while(op != 6);
        s.close();
    }

    public static ContaCorrente getClienteByNumero(){
        int n;

        System.out.println("\nDigite o número da conta:");
        n = (Integer.parseInt(s.nextLine()));

        for(ContaCorrente c : clientes){
            if(c.getNumero() == n)
            {
                return c;
            }
        }

        System.out.println("\nNão há uma conta com esse número!");
        return null;
    }

    public static double somaNaoEspecial(){
        double soma=0;

        for(ContaCorrente c : clientes){
            if(c.getSaldo() > 0){
                soma += c.getSaldo();
            }
        }

        return soma;
    }
}