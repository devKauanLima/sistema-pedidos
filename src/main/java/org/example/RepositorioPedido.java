package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RepositorioPedido {

    public void salvar(String caminhoCSV, List<SelecaoProduto> selecoes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoCSV))) {
            writer.println("idProduto,nome,quantidade,precoUnitario,precoTotal");

            for (SelecaoProduto selecao : selecoes) {
                Produto p = selecao.getProduto();
                int qtd = selecao.getQuantidade();
                double precoTotal = p.getPreco() * qtd;

                writer.printf("%d,%s,%d,%,2f,%,2f%n",
                        p.getId(),
                        p.getNome(),
                        qtd,
                        p.getPreco(),
                        precoTotal
                );
            }

            System.out.println("\nÁrea de pedidos salva com sucesso em: " + caminhoCSV);

        } catch (IOException e) {
            System.out.println("Erro ao salvar pedido no CSV: " + e.getMessage());
        }
    }
}