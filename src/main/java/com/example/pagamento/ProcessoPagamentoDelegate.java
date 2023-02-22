package com.example.pagamento;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component("processoPagamentoDelegate")
@RequiredArgsConstructor
@Slf4j
public class ProcessoPagamentoDelegate implements JavaDelegate {

    private final PagamentoService pagamentoService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Executando Processo de Pagamento");

        log.info("CPF: {}", delegateExecution.getVariable("cpf"));
        log.info("Endereco: {}", delegateExecution.getVariable("endereco"));
        log.info("Numero Pedido: {}", delegateExecution.getVariable("numeroPedido"));

        delegateExecution.setProcessBusinessKey("Teste -" + UUID.randomUUID());
        String resultadoPagamento = (String) delegateExecution.getVariable("resultadoPagamento");
        delegateExecution.setVariable("respostaPagamento", resultadoPagamento);

    }
}
