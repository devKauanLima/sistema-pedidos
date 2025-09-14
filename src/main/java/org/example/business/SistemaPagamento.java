package org.example.business;

import org.example.model.FormaPagamento;
import org.example.model.Pagamento;

public class SistemaPagamento {

    public boolean autorizar(Pagamento pagamento) {
        if (pagamento == null || pagamento.getForma() == null || pagamento.getDadosPagamento() == null) {
            return false;
        }

        switch (pagamento.getForma()) {
            case CARTAO_CREDITO:
            case PIX:
            case BOLETO:
                return !pagamento.getDadosPagamento().isBlank();

            default:
                return false;
        }
    }
}