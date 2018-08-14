package br.com.rcc_dev.testes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
public class App {

  public static void main( String[] args ){
    SpringApplication.run(App.class, args);
  }
  
  // ----------------------------------------------
  
}
