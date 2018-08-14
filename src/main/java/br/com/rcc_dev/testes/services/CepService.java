package br.com.rcc_dev.testes.services;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.rcc_dev.testes.Utils;
import br.com.rcc_dev.testes.entities.db.Cep;
import io.ebean.Ebean;
import io.ebean.ExpressionFactory;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CepService {
  
  private static final String correiosURL = "https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente";
  private static final String consultaCepTemplate = Utils.fileContentsJar("consultaCEP.xml");
  private WebClient web = WebClient.create(correiosURL);
  
  // ----------------------------------------------------------------
  
  public Mono<Cep> findCEP(String cepStrParam, final boolean cache){
    // get CEP without non-numeric chars
    final String cepStr = cepStrParam.replaceAll("\\D+", "");
    
    if( cache ){
      // Search on database
      ExpressionFactory ef = Ebean.getExpressionFactory();
      List<Cep> cepList = Ebean.find(Cep.class).where(ef.eq("cep", cepStr)).findList();
      if( !cepList.isEmpty() ){
        Cep cep = cepList.get(0);
        cep.setDatabase(true);
        return Mono.just(cep);
      }
    }
    
    // SOAP request to Correios
    String reqStr = consultaCepTemplate.replace("$cep", cepStr);
    
    // HTTP request to Correios
    return web.post().syncBody(reqStr).retrieve().bodyToMono(String.class)
    .doOnError(ex -> log.warn("Não foi possível encontrar o CEP {}", cepStr, ex) )
    .map( body -> {
      try{ 
        JsonNode json = new XmlMapper().readTree(body);
        if (json.findPath("Body").has("Fault")) {
          log.warn("Não foi possível encontrar o CEP {}", cepStr);
        }else{
          JsonNode jsonResp = json.findPath("Body").findPath("consultaCEPResponse").findPath("return");
          Cep cep = new Cep();
          cep.setBairro(        jsonResp.findPath("bairro").asText() );
          cep.setCep(           jsonResp.findPath("cep").asText() );
          cep.setCidade(        jsonResp.findPath("cidade").asText() );
          cep.setComplemento(   jsonResp.findPath("complemento").asText() );
          cep.setComplemento2(  jsonResp.findPath("complemento2").asText() );
          cep.setEndereco(      jsonResp.findPath("end").asText() );
          cep.setUf(            jsonResp.findPath("uf").asText() );
          
          if( cache ) Ebean.save(cep);
          return cep;
        }
      }catch(IOException ex){
        log.error("Erro ao tentar buscar informações do CEP nos Correios!", ex);
      }
      return null;
    });
  }
  
}
