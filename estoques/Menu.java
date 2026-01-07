import java.util.Scanner;

public class Menu {

    private Estoque estoque;
    private ControleEmprestimos controle;
    private Scanner scanner;

    public Menu() {
        estoque = new Estoque();
        controle = new ControleEmprestimos();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n=== CONTROLE DE ESTOQUE - LOJA SOLIDÁRIA ===");
            System.out.println("1. Exibir estoque");
            System.out.println("2. Adicionar quantidade ao estoque");
            System.out.println("3. Remover quantidade do estoque");
            System.out.println("4. Registrar empréstimo");
            System.out.println("5. Listar empréstimos atrasados");
            System.out.println("6. Gerar relatório final");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {

                case 1:
                    estoque.exibir();
                    break;

                case 2:
                    adicionarEstoque();
                    break;

                case 3:
                    removerEstoque();
                    break;

                case 4:
                    registrarEmprestimo();
                    break;

                case 5:
                    controle.listarAtrasados();
                    break;

                case 6:
                    Relatorio.gerarRelatorioFinal();
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

            if (opcao != 0) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 0);

        scanner.close();
    }
     private void adicionarEstoque() {
        System.out.println("\n=== ADICIONAR AO ESTOQUE ===");

        System.out.print("Categoria (Masculinos/Femininos/Infantil/Calçados/Diversos): ");
        String categoria = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida!");
            return;
        }

        estoque.adicionar(categoria, quantidade);
        System.out.println("Quantidade adicionada com sucesso!");
    }

    private void removerEstoque() {
        System.out.println("\n=== REMOVER DO ESTOQUE ===");

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = estoque.remover(categoria, quantidade);

        if (sucesso) {
            System.out.println("Quantidade removida com sucesso!");
        } else {
            System.out.println("Erro: categoria inválida ou estoque insuficiente.");
        }
    }

    private void registrarEmprestimo() {
        System.out.println("\n=== REGISTRAR EMPRÉSTIMO ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();

        System.out.print("Dias para devolução: ");
        int dias = scanner.nextInt();
        scanner.nextLine();

        if (quantidade <= 0 || dias <= 0) {
            System.out.println("Dados inválidos!");
            return;
        }

        Emprestimo emprestimo = // Cria o objeto Emprestimo
                new Emprestimo(nome, cpf, categoria, quantidade, dias);

        controle.registrar(emprestimo, estoque);
    }
}


