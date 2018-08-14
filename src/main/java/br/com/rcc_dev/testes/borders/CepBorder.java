package br.com.rcc_dev.testes.borders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rcc_dev.testes.entities.db.Cep;
import br.com.rcc_dev.testes.services.CepService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class CepBorder {
  
  @Autowired
  private CepService service;
  
  // --------------------------------------
  
  @GetMapping("/cep/{cep}")
  public Mono<Cep> finCep(
      @PathVariable("cep") String cep, 
      @RequestParam(value="cache", defaultValue="true") boolean cache){
    log.info("Buscando informações do CEP {}", cep);
    return service.findCEP(cep, cache);
  }
  
}