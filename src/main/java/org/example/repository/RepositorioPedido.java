package org.example.repository;

import org.example.model.Cliente;
import org.example.model.Produto;
import org.example.model.SelecaoProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RepositorioPedido {

    public void salvar(String ignoredPath, List<SelecaoProduto> selecoes, Cliente cliente) {
        String sql = "INSERT INTO PEDIDO (idCliente, idProduto, nomeProduto, quantidade, precoUnitario, precoTotal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (SelecaoProduto selecao : selecoes) {
                Produto p = selecao.getProduto();
                int qtd = selecao.getQuantidade();
                double precoTotal = p.getPreco() * qtd;
                int idCliente = (cliente != null) ? cliente.getId() : -1;

                pstmt.setInt(1, idCliente);
                pstmt.setInt(2, p.getId());
                pstmt.setString(3, p.getNome());
                pstmt.setInt(4, qtd);
                pstmt.setDouble(5, p.getPreco());
                pstmt.setDouble(6, precoTotal);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}