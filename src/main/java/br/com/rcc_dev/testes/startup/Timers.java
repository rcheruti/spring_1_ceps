package br.com.rcc_dev.testes.startup;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@EnableScheduling
@Slf4j
public class Timers {
  
  @Scheduled(cron = "*/2 * * * * *")
  public void fazer(){
    log.info("Estamos fazendo esta tarefa.");
  }
  
}
