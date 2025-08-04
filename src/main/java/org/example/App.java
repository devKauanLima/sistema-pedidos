package org.example;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CatalogoProdutos catalogo = new CatalogoProdutos();
        AreaPedidos areaPedidos = new AreaPedidos();
        RepositorioPedido repositorio = new RepositorioPedido();

        String caminhoCSVProdutos = "produtos.csv";
        String caminhoCSVPedido = "area_pedidos.csv";


        List<Produto> produtos = catalogo.carregarProdutosCSV(caminhoCSVProdutos);

        System.out.println("=== Área de Produtos ===");
        for (Produto produto : produtos) {
            System.out.println(produto);
        }


        while (true) {
            System.out.print("\nDigite o ID do produto para selecionar (ou 0 para finalizar): ");
            int id = Integer.parseInt(scanner.nextLine());

            if (id == 0) break;

            Optional<Produto> produtoEscolhido = produtos.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst();

            if (produtoEscolhido.isPresent()) {
                System.out.print("Quantidade: ");
                int qtd = Integer.parseInt(scanner.nextLine());

                areaPedidos.adicionarProduto(produtoEscolhido.get(), qtd);
                System.out.println("Produto adicionado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        }


        areaPedidos.exibirSelecoes();


        repositorio.salvar(caminhoCSVPedido, areaPedidos.getSelecoes());
    }
}