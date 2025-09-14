package org.example.model;

public class Pagamento {
    private String dadosPagamento; // Ex: Número do cartão ou chave Pix
    private FormaPagamento forma;

    public Pagamento() {}

    public String getDadosPagamento() { return dadosPagamento; }
    public void setDadosPagamento(String dadosPagamento) { this.dadosPagamento = dadosPagamento; }
    public FormaPagamento getForma() { return forma; }
    public void setForma(FormaPagamento forma) { this.forma = forma; }
}