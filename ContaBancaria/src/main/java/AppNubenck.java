import java.util.Scanner;

public class AppNubenck {

    public static void main(String[] args) {
            Scanner leitura = new Scanner(System.in);

            System.out.println("=== BEM-VINDO AO NUBENCK ===");
            System.out.print("Usuário: ");
            String user = leitura.nextLine();
            System.out.print("Senha: ");
            String senha = leitura.nextLine();

            if (user.equalsIgnoreCase("Claudia") && senha.equals("1234")) {
                ContaBancaria minhaConta = new ContaBancaria("Claudia", 1599.99, 5000.00);
                int opcao = 0;

                while (opcao != 5) {
                    System.out.printf("\nOlá, %s! | Saldo: R$ %.2f%n", minhaConta.getTitular(), minhaConta.getSaldo());
                    System.out.println("1- Saldo | 2- Transferir | 3- Receber | 4- Extrato | 5- Sair");
                    System.out.print("Opção: ");
                    opcao = leitura.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.printf("Saldo: R$ %.2f | Crédito: R$ %.2f%n",
                                    minhaConta.getSaldo(), minhaConta.getCredito());
                            break;
                        case 2:
                            System.out.print("Valor: ");
                            double v = leitura.nextDouble();
                            if (minhaConta.transferir(v)) {
                                System.out.println("Sucesso!");
                            } else {
                                System.out.println("Saldo insuficiente!");
                            }
                            break;
                        case 3:
                            System.out.print("Valor: ");
                            minhaConta.receber(leitura.nextDouble());
                            break;

                            case 4:
                                System.out.println(minhaConta.getExtrato());
                                break;

                            case 5:
                                System.out.println("Deseja salvar seu extra antes de sair? (1-Sim / 2-Não)");
                                int salvar = leitura.nextInt();
                                if (salvar == 1) {
                                    minhaConta.salvarExtratoNoArquivo();
                                }
                                System.out.println("Encerrando... Volte sempre!");
                                break;
                        }
                    }
                }else {

                System.out.println("Acesso Negado!");
            }
            leitura.close();
        }
    }
    
