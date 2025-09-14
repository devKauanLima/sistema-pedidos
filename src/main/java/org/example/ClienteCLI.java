package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class ClienteCLI {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String API_URL = "http://localhost:7070/api";
    private static final List<SelecaoProduto> areaPedidos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("\n--- Clientes ---");
            System.out.println("1. Listar todos os produtos");
            System.out.println("2. Adicionar produto ao carrinho");
            System.out.println("3. Ver carrinho (" + areaPedidos.size() + " item(ns))");
            System.out.println("4. Remover item do carrinho");
            System.out.println("5. Finalizar Pedido");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: listarProdutos(); break;
                case 2: adicionarProduto(); break;
                case 3: verCarrinho(); break;
                case 4: removerProduto(); break;
                case 5: finalizarPedido(); break;
                case 0: System.out.println("Saindo..."); return;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private static void listarProdutos() throws Exception {
        System.out.println("\nBuscando produtos...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/produtos"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Produtos encontrados:");
            List<Produto> produtos = objectMapper.readValue(response.body(), new TypeReference<>() {});
            for (Produto produto : produtos) {
                System.out.printf("ID: %d | Nome: %s | Preço: R$ %.2f%n",
                        produto.getId(), produto.getNome(), produto.getPreco());
            }
        } else {
            System.out.println("Erro ao buscar produtos: " + response.body());
        }
    }

    private static void adicionarProduto() throws Exception {
        System.out.print("Digite o ID do produto a ser adicionado: ");
        int produtoId = scanner.nextInt();
        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL + "/produtos")).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            List<Produto> produtos = objectMapper.readValue(response.body(), new TypeReference<>() {});
            Produto produtoEncontrado = produtos.stream().filter(p -> p.getId() == produtoId).findFirst().orElse(null);

            if (produtoEncontrado != null) {
                areaPedidos.add(new SelecaoProduto(produtoEncontrado, quantidade));
                System.out.println("Produto adicionado ao carrinho!");
            } else {
                System.out.println("Produto com ID " + produtoId + " não encontrado.");
            }
        }
    }

    private static void verCarrinho() {
        System.out.println("\n--- Itens no Carrinho ---");
        if (areaPedidos.isEmpty()) {
            System.out.println("O carrinho está vazio.");
            return;
        }
        double total = 0;
        for (int i = 0; i < areaPedidos.size(); i++) {
            SelecaoProduto item = areaPedidos.get(i);
            double subtotal = item.getProduto().getPreco() * item.getQuantidade();
            System.out.printf(Locale.US, "%d. %s (Qtd: %d) - Subtotal: R$ %.2f%n",
                    i + 1, item.getProduto().getNome(), item.getQuantidade(), subtotal);
            total += subtotal;
        }
        System.out.printf(Locale.US, "----------------------------%nTotal do Carrinho: R$ %.2f%n", total);
    }

    private static void removerProduto() {
        verCarrinho();
        if (areaPedidos.isEmpty()) return;
        System.out.print("Digite o número do item para remover (ou 0 para cancelar): ");
        int itemIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (itemIndex >= 0 && itemIndex < areaPedidos.size()) {
            SelecaoProduto removido = areaPedidos.remove(itemIndex);
            System.out.println("Produto '" + removido.getProduto().getNome() + "' removido do carrinho.");
        } else {
            System.out.println("Nenhum item removido.");
        }
    }

    private static void finalizarPedido() throws Exception {
        verCarrinho();
        if (areaPedidos.isEmpty()) {
            System.out.println("Não é possível finalizar o pedido com o carrinho vazio.");
            return;
        }

        Cliente cliente = new Cliente(1, "Kauan Lima", "kauan@email.com");

        System.out.println("\n--- Finalizando Pedido ---");

        EnderecoEntrega endereco = new EnderecoEntrega();
        System.out.print("Rua: ");
        endereco.setRua(scanner.nextLine());
        System.out.print("Número: ");
        endereco.setNumero(scanner.nextLine());
        System.out.print("Bairro: ");
        endereco.setBairro(scanner.nextLine());
        System.out.print("Cidade: ");
        endereco.setCidade(scanner.nextLine());
        System.out.print("Estado: ");
        endereco.setEstado(scanner.nextLine());
        System.out.print("CEP: ");
        endereco.setCep(scanner.nextLine());

        Pagamento pagamento = new Pagamento();
        System.out.println("Escolha a Forma de Pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. PIX");
        System.out.println("3. Boleto");
        System.out.print("Opção: ");
        int formaPagamentoOpcao = scanner.nextInt();
        scanner.nextLine();

        boolean pagamentoConfirmado = false;

        switch (formaPagamentoOpcao) {
            case 1:
                pagamento.setForma(FormaPagamento.CARTAO_CREDITO);
                System.out.print("Confirmar pagamento com Cartão de Crédito? (S/N): ");
                if (scanner.nextLine().equalsIgnoreCase("S")) {
                    pagamento.setDadosPagamento("Pagamento com cartão confirmado");
                    pagamentoConfirmado = true;
                }
                break;
            case 2:
                pagamento.setForma(FormaPagamento.PIX);
                String chavePix = UUID.randomUUID().toString();
                System.out.println("Chave PIX (copia e cola): " + chavePix);
                pagamento.setDadosPagamento(chavePix);
                System.out.print("Pagamento realizado? (S/N): ");
                if (scanner.nextLine().equalsIgnoreCase("S")) {
                    pagamentoConfirmado = true;
                }
                break;
            case 3:
                pagamento.setForma(FormaPagamento.BOLETO);
                String codigoBoleto = "23793.38128 60073.701234 5 94430000019999";
                System.out.println("Código de Barras: " + codigoBoleto);
                pagamento.setDadosPagamento(codigoBoleto);
                System.out.print("Pagamento realizado? (S/N): ");
                if (scanner.nextLine().equalsIgnoreCase("S")) {
                    pagamentoConfirmado = true;
                }
                break;
            default:
                System.out.println("Opção de pagamento inválida. Pedido cancelado.");
                return;
        }

        if (!pagamentoConfirmado) {
            System.out.println("Pagamento não confirmado. Pedido cancelado.");
            return;
        }

        Pedido pedidoFinal = new Pedido();
        pedidoFinal.setCliente(cliente);
        pedidoFinal.setProdutos(new ArrayList<>(areaPedidos));
        pedidoFinal.setEndereco(endereco);
        pedidoFinal.setPagamento(pagamento);

        String jsonBody = objectMapper.writeValueAsString(pedidoFinal);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/pedidos"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201 || response.statusCode() == 400) {
            Notificacao notificacao = objectMapper.readValue(response.body(), Notificacao.class);
            System.out.println("\n>> MENSAGEM DO SISTEMA: " + notificacao.getMensagem());
            if (notificacao.isSucesso()) {
                areaPedidos.clear();
            }
        } else {
            System.out.println("\n>> OCORREU UM ERRO INESPERADO: " + response.body());
        }
    }
}