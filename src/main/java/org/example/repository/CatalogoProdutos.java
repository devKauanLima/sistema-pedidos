package org.example.repository;

import org.example.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogoProdutos {

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTO";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public Produto buscarProdutoPorId(int id) {
        String sql = "SELECT * FROM PRODUTO WHERE id = ?";
        Produto produto = null;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }
}