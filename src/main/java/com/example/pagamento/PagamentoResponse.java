package com.example.pagamento;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagamentoResponse {
    private String businessKey;
}
