package org.example.controller;

import io.javalin.http.Context;
import org.example.business.ProdutoService;
import org.example.model.Produto;
import java.util.List;

public class ProdutoController {

    private static ProdutoService produtoService = new ProdutoService();

    public static void listarTodos(Context ctx) {
        List<Produto> produtos = produtoService.listarTodos();
        if (produtos != null && !produtos.isEmpty()) {
            ctx.json(produtos);
        } else {
            ctx.status(404).result("Nenhum produto encontrado.");
        }
    }
}