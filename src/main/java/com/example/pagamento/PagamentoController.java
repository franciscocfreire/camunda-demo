package com.example.pagamento;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PagamentoController {

    private final ProcessEngine processEngine;


    @RequestMapping(value = "/inicia-pagamento", produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.POST)
    public ResponseEntity<PagamentoResponse> iniciaProcessoPagamento(@RequestBody PagamentoRequest request) throws IllegalAccessException {

        log.info("Inicia Controller de Pagamento");

        Map<String, Object> variables = new HashMap<>();
        for(Field field: request.getClass().getDeclaredFields()){
            field.setAccessible(true);
            variables.put(field.getName(), field.get(request));
        }

        variables.put("resultadoPagamento", request.getResultadoPagamento());
        ProcessInstance processoPagamento = processEngine.getRuntimeService().startProcessInstanceByKey("processoPagamento", variables);

        return ResponseEntity.ok(PagamentoResponse.builder()
                .businessKey(processoPagamento.getBusinessKey()).build());
    }

    @RequestMapping(value = "/respostaGerente", produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<?> respostaGerente(@RequestBody PagamentoRequest request){

        log.info("Resposta ao Gerente");
        processEngine.getRuntimeService().createMessageCorrelation("RESPOSTA_GERENTE")
                .processInstanceBusinessKey(request.getIdRespostaGerente())
                .correlate();
        return ResponseEntity.ok().build();
    }

}
