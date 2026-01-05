import java.time.LocalDate;

public class Emprestimo {

    private String nome;
    private String cpf;
    private String categoria;
    private int quantidade;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    public Emprestimo(String nome, String cpf, String categoria,
                      int quantidade, int dias) {

        this.nome = nome;
        this.cpf = cpf;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.dataRetirada = LocalDate.now();
        this.dataDevolucao = dataRetirada.plusDays(dias);
        this.devolvido = false;
    }

    
    public String getCategoria() {
        return categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

   
    public boolean estaAtrasado() {
        return !devolvido && LocalDate.now().isAfter(dataDevolucao);
    }

    
    public String toCSV() {
        return nome + "," + cpf + "," + categoria + "," +
               quantidade + "," + dataRetirada + "," +
               dataDevolucao + "," + devolvido;
    }
}
