package org.example.controller;

import io.javalin.http.Context;
import org.example.business.PedidoService;
import org.example.model.Notificacao;
import org.example.model.Pedido;

public class PedidoController {

    private static PedidoService pedidoService = new PedidoService();

    public static void criarPedido(Context ctx) {
        try {
            Pedido pedido = ctx.bodyAsClass(Pedido.class);
            Notificacao notificacao = pedidoService.criarPedido(pedido);

            if (notificacao.isSucesso()) {
                ctx.status(201).json(notificacao);
            } else {
                ctx.status(400).json(notificacao);
            }
        } catch (Exception e) {
            ctx.status(500).result("Erro interno no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}