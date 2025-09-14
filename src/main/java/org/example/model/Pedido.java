package org.example.model;

import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<SelecaoProduto> produtos;
    private EnderecoEntrega endereco;
    private Pagamento pagamento;

    public Pedido() {}

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<SelecaoProduto> getProdutos() { return produtos; }
    public void setProdutos(List<SelecaoProduto> produtos) { this.produtos = produtos; }
    public EnderecoEntrega getEndereco() { return endereco; }
    public void setEndereco(EnderecoEntrega endereco) { this.endereco = endereco; }
    public Pagamento getPagamento() { return pagamento; }
    public void setPagamento(Pagamento pagamento) { this.pagamento = pagamento; }
}