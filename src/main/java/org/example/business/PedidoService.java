package org.example.business;

import org.example.model.Notificacao;
import org.example.model.Pedido;
import org.example.repository.RepositorioPedido;

public class PedidoService {

    private RepositorioPedido repositorioPedido = new RepositorioPedido();
    private SistemaPagamento sistemaPagamento = new SistemaPagamento();

    public Notificacao criarPedido(Pedido pedido) {
        boolean pagamentoAutorizado = sistemaPagamento.autorizar(pedido.getPagamento());

        if (pagamentoAutorizado) {
            repositorioPedido.salvar("pedidos_finalizados.csv", pedido.getProdutos(), pedido.getCliente());
            return new Notificacao(true, "Pedido realizado e pagamento confirmado com sucesso!");
        } else {
            return new Notificacao(false, "Pagamento não confirmado. Pedido cancelado.");
        }
    }
}