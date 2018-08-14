package br.com.rcc_dev.testes.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.rcc_dev.testes.entities.db.Cep;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CepServiceTest {
  
  @Before
  public void before(){
    
  }
  
  @Test
  public void testarBusca(){
    CepService service = new CepService();
    Cep cep;
    
    cep = service.findCEP("02072-002", true).block();
    assertFalse(cep.isDatabase());
    
    cep = service.findCEP("02072-002", true).block();
    assertTrue(cep.isDatabase());
  }
  
}