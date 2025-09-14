package org.example.model;

public class SelecaoProduto {
    private Produto produto;
    private int quantidade;

    public SelecaoProduto() {
    }

    public SelecaoProduto(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return quantidade + "x " + produto.getNome() + " - R$" + produto.getPreco();
    }
}