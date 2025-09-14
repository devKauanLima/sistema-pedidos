package org.example.model;

import java.util.Locale;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;

    public Produto() {
    }

    public Produto(int id, String nome, String descricao, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "ID: %d, Nome: %s, Descrição: %s, Preço: R$ %.2f",
                this.id, this.nome, this.descricao, this.preco);
    }
}