package org.example.business;

import org.example.model.Produto;
import org.example.repository.CatalogoProdutos;
import java.util.List;

public class ProdutoService {

    private CatalogoProdutos catalogo = new CatalogoProdutos();

    public List<Produto> listarTodos() {
        return catalogo.listarProdutos();
    }

    public Produto buscarPorId(int id) {
        return catalogo.buscarProdutoPorId(id);
    }
}