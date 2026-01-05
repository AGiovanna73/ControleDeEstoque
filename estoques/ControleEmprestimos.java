import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControleEmprestimos {

    private List<Emprestimo> emprestimos;
    private static final String ARQUIVO = "Emprestimos.csv";

    public ControleEmprestimos() {
        emprestimos = new ArrayList<>();
    }

    
    public void registrar(Emprestimo e, Estoque estoque) {

        // remove do estoque primeiro
        if (estoque.remover(e.getCategoria(), e.getQuantidade())) {
            emprestimos.add(e);
            salvar();
            System.out.println("Empréstimo registrado com sucesso!");
        } else {
            System.out.println("Erro: estoque insuficiente ou categoria inválida!");
        }
    }

   
    public void listarAtrasados() {
        System.out.println("\n=== EMPRÉSTIMOS ATRASADOS ===");

        boolean encontrou = false;

        for (Emprestimo e : emprestimos) {
            if (e.estaAtrasado()) {
                System.out.println(e.toCSV());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum empréstimo atrasado.");
        }
    }

   
    public Map<String, Integer> calcularEmprestadosPorCategoria() {

        Map<String, Integer> emprestados = new HashMap<>();

        for (Emprestimo e : emprestimos) {
            String categoria = e.getCategoria();
            int quantidade = e.getQuantidade();

            int atual = emprestados.getOrDefault(categoria, 0);
            emprestados.put(categoria, atual + quantidade);
        }

        return emprestados;
    }

    
    private void salvar() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Emprestimo e : emprestimos) {
                pw.println(e.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar empréstimos: " + e.getMessage());
        }
    }
}
