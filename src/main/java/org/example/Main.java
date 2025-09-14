package org.example;

import io.javalin.Javalin;
import org.example.controller.PedidoController;
import org.example.controller.ProdutoController;
import org.example.repository.DatabaseManager;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.setupDatabase();

        var app = Javalin.create(config -> {
            config.router.apiBuilder(() -> {
                path("/api", () -> {
                    path("/produtos", () -> {
                        get(ProdutoController::listarTodos);
                    });
                    path("/pedidos", () -> {
                        post(PedidoController::criarPedido);
                    });
                });
            });
        }).start(7070);

        System.out.println("Servidor API iniciado em http://localhost:7070");
    }
}