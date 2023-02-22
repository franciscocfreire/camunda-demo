package com.example.pagamento;

import lombok.Data;

@Data
public class PagamentoRequest {
    private String resultadoPagamento;
    private String idRespostaGerente;
    private String cpf;
    private String endereco;
    private String numeroPedido;
}
