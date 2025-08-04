package org.example;



import java.io.*;
import java.util.*;

public class CatalogoProdutos {

    public List<Produto> carregarProdutosCSV(String caminhoCSV) {
        List<Produto> produtos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] dados = linha.split(",");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String descricao = dados[2];
                double preco = Double.parseDouble(dados[3]);

                Produto produto = new Produto(id, nome, descricao, preco);
                produtos.add(produto);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }

        return produtos;
    }
}

