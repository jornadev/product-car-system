package entidades;

public class Produto {
    private int id;
    private String nome;
    private String categoria;
    private double valor;
    private int quantidade;

    public Produto() {
    }

    public Produto(int id, String nome, String categoria, double valor, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
