import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Relatorio {

    public static void gerarRelatorioFinal() {

        Map<String, Integer> estoque = lerCSV("Estoque.csv");
        Map<String, Integer> emprestados = lerCSV("EmprestimosResumo.csv");
        Map<String, Integer> doados = lerCSV("Doacoes.csv");

        try (PrintWriter pw = new PrintWriter(new FileWriter("RelatorioFinal.csv"))) {

            pw.println("Categoria,Em Estoque,Emprestado,Doado,Disponível");

            for (String categoria : estoque.keySet()) {

                int qtdEstoque = estoque.getOrDefault(categoria, 0);
                int qtdEmprestado = emprestados.getOrDefault(categoria, 0);
                int qtdDoado = doados.getOrDefault(categoria, 0);

                int disponivel = qtdEstoque - qtdEmprestado - qtdDoado;

                pw.println(categoria + "," + qtdEstoque + "," +
                           qtdEmprestado + "," + qtdDoado + "," +
                           disponivel);
            }

            System.out.println("Relatório final gerado com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

 
    private static Map<String, Integer> lerCSV(String arquivo) {

        Map<String, Integer> dados = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String categoria = partes[0];
                int quantidade = Integer.parseInt(partes[1]);

                dados.put(categoria, quantidade);
            }

        } catch (IOException e) {
            // arquivo pode não existir
        }

        return dados;
    }
}
