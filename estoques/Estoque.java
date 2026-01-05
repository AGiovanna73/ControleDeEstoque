import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private List<Itens> itens;
    private static final String ARQUIVO = "Estoque.csv";

    private static final String[] CATEGORIAS = {
        "Masculinos", "Femininos", "Infantil", "Calçados", "Diversos"
    };

    public Estoque() {
        itens = new ArrayList<>();
        lerArquivo();
        inicializarCategorias();
    }

    // =========================
    // GARANTE QUE TODAS AS CATEGORIAS EXISTAM
    // =========================
    private void inicializarCategorias() {
        for (String categoria : CATEGORIAS) {
            if (buscarItem(categoria) == null) {
                itens.add(new Itens(categoria, 0));
            }
        }
        salvarArquivo();
    }

    // =========================
    // BUSCA ITEM POR CATEGORIA
    // =========================
    private Itens buscarItem(String categoria) {
        for (Itens item : itens) {
            if (item.getCategoria().equalsIgnoreCase(categoria)) {
                return item;
            }
        }
        return null;
    }

    // =========================
    // ADICIONAR AO ESTOQUE
    // =========================
    public void adicionar(String categoria, int quantidade) {
        Itens item = buscarItem(categoria);

        if (item != null && quantidade > 0) {
            item.setQuantidade(item.getQuantidade() + quantidade);
            salvarArquivo();
        }
    }

    // =========================
    // REMOVER DO ESTOQUE (RETORNA BOOLEAN)
    // =========================
    public boolean remover(String categoria, int quantidade) {

        Itens item = buscarItem(categoria);

        if (item != null && quantidade > 0) {

            if (item.getQuantidade() >= quantidade) {
                item.setQuantidade(item.getQuantidade() - quantidade);
                salvarArquivo();
                return true; // sucesso
            }
        }
        return false; // erro (categoria inválida ou estoque insuficiente)
    }

    // =========================
    // EXIBIR ESTOQUE
    // =========================
    public void exibir() {
        System.out.println("\n=== ESTOQUE ATUAL ===");
        for (Itens item : itens) {
            System.out.println(item.getCategoria() + ": " + item.getQuantidade());
        }
    }

    // =========================
    // LER CSV
    // =========================
    private void lerArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String categoria = dados[0];
                int quantidade = Integer.parseInt(dados[1]);
                itens.add(new Itens(categoria, quantidade));
            }
        } catch (IOException e) {
            // arquivo ainda não existe (primeira execução)
        }
    }

    // =========================
    // SALVAR CSV
    // =========================
    private void salvarArquivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Itens item : itens) {
                pw.println(item.getCategoria() + "," + item.getQuantidade());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar estoque: " + e.getMessage());
        }
    }
}
