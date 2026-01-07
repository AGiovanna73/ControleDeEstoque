public class Itens {

    private String categoria;
    private int quantidade;

    public Itens(String categoria, int quantidade) {
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

   // Converte o item para formato CSV
    @Override
    public String toString() {
        return categoria + "," + quantidade;
    }
}

