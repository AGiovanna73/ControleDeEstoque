import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map; //estrutura chave → valor (categoria → quantidade),Aqui o Map substitui vetores/matrizes, facilitando o cruzamento de dados.

public class Relatorio {

    public static void gerarRelatorioFinal() {

      //  Cada arquivo CSV é lido,Os dados são armazenados tipo mapas:chave vai para categoria,valor vai para quantidade 
        
        Map<String, Integer> estoque = lerCSV("Estoque.csv");
        Map<String, Integer> emprestados = lerCSV("EmprestimosResumo.csv");
        Map<String, Integer> doados = lerCSV("Doacoes.csv");

        try (PrintWriter pw = new PrintWriter(new FileWriter("RelatorioFinal.csv"))) {//Cria o arquivo RelatorioFinal.csv;try-with-resources fecha o arquivo automaticamente

            pw.println("Categoria,Em Estoque,Emprestado,Doado,Disponível");//Define as colunas do relatório

            for (String categoria : estoque.keySet()) {//Percorre todas as categorias existentes no estoque; keySet() retorna apenas as chaves do Map

                int qtdEstoque = estoque.getOrDefault(categoria, 0);//se a categoria existir retorna o valor se nao retorna 0
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

 
    private static Map<String, Integer> lerCSV(String arquivo) {//Método genérico para leitura de qualquer CSV simples


        Map<String, Integer> dados = new HashMap<>();//cria o map(mapa) e armazena os dados do arquivo

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {//Abre o arquivo para leitura.
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String categoria = partes[0];
                int quantidade = Integer.parseInt(partes[1]);

                dados.put(categoria, quantidade);//armazena os dados no Map
            }

        } catch (IOException e) {
            // arquivo pode não existir
        }

        return dados;
    }
}

