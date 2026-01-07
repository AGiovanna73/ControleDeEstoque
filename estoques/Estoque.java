import java.io.BufferedReader;//ler arquivos texto linha por linha
import java.io.FileReader;//abrir arquivo para leitura
import java.io.FileWriter;//abrir/criar arquivo para escrita
import java.io.IOException;//tratar erros de arquivo
import java.io.PrintWriter;//escrever texto no arquivo de forma simples
import java.util.ArrayList;//vetor dinâmico para armazenar os itens do estoque
import java.util.List;//vetor dinâmico para armazenar os itens do estoque

public class Estoque {

    private List<Itens> itens;//lista guarda todos os itens do estoque,cada  é um objeto da classe Itens,vetor dinamico
    private static final String ARQUIVO = "Estoque.csv";//Nome do arquivo onde o estoque será salvo.

    private static final String[] CATEGORIAS = {
        "Masculinos", "Femininos", "Infantil", "Calçados", "Diversos"
    };

    public Estoque() {//Cria a lista vazia (ArrayList),lê os dados já salvos  CSV,faça q tds as categorias exis
        itens = new ArrayList<>();
        lerArquivo();
        inicializarCategorias();
    }



//Percorre todas as categorias fixas
//Verifica se ela já existe no estoque
//Se não existir cria com qtd 0
//Evita erro quando o usuário tenta usar uma categoria que não existe
//Depois salva tudo no arquivo
    
    private void inicializarCategorias() {
        for (String categoria : CATEGORIAS) {
            if (buscarItem(categoria) == null) {
                itens.add(new Itens(categoria, 0));
            }
        }
        salvarArquivo();
    }

    private Itens buscarItem(String categoria) {
        for (Itens item : itens) {
            if (item.getCategoria().equalsIgnoreCase(categoria)) {
                return item;
            }
        }
        return null;
    }

    
    public void adicionar(String categoria, int quantidade) {
        Itens item = buscarItem(categoria);

        if (item != null && quantidade > 0) {
            item.setQuantidade(item.getQuantidade() + quantidade);
            salvarArquivo();
        }
    }

  
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

    
    public void exibir() {
        System.out.println("\n=== ESTOQUE ATUAL ===");
        for (Itens item : itens) {
            System.out.println(item.getCategoria() + ": " + item.getQuantidade());
        }
    }

    
    private void lerArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String categoria = dados[0];
                int quantidade = Integer.parseInt(dados[1]);
                itens.add(new Itens(categoria, quantidade));
            }
        } catch (IOException e) {//Se o arquivo não existir, o programa continua normalmente
            // arquivo ainda não existe (primeira execução),e sem erro
        }
    }

   
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

//List<Itens> :simula memória
