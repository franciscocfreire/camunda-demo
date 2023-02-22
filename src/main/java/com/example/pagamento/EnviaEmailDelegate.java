package com.example.pagamento;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("enviaEmailDelegate")
@Slf4j
public class EnviaEmailDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Executando Envia Email");

        delegateExecution.setVariable("statusEnviaEmail", "OK");
    }
}
