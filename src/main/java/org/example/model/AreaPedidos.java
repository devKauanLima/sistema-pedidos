package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class AreaPedidos {
    private List<SelecaoProduto> selecoes = new ArrayList<>();

    public void adicionarProduto(Produto produto, int quantidade) {
        selecoes.add(new SelecaoProduto(produto, quantidade));
    }

    public void exibirSelecoes() {
        System.out.println("\n=== Produtos Selecionados ===");
        if (selecoes.isEmpty()) {
            System.out.println("Nenhum produto selecionado.");
        } else {
            for (SelecaoProduto selecao : selecoes) {
                System.out.println(selecao);
            }
        }
    }

    public List<SelecaoProduto> getSelecoes() {
        return selecoes;
    }
}