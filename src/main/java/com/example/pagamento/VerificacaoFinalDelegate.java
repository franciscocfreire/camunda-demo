package com.example.pagamento;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("verificacaoFinalDelegate")
@Slf4j
public class VerificacaoFinalDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Verificações Finais");

        log.info("Numero Pedido: {}", delegateExecution.getVariable("numeroPedido"));
        log.info("Email enviado: {}", delegateExecution.getVariable("statusEnviaEmail"));

    }
}
