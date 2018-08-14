package br.com.rcc_dev.testes.borders;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.rcc_dev.testes.entities.db.Person;
import io.ebean.Ebean;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PersonBorder {
  
  @RequestMapping(path = "/person", method = RequestMethod.POST)
  public Person create(@RequestBody Person person){
    log.info("Recebemos a pessoa: {}", person);
    Ebean.save(person);
    return person;
  }
  
  @RequestMapping(path = "/person/{id}", method = RequestMethod.GET)
  public Person find(@PathVariable int id){
    log.info("Bucaremos a pessoa de ID: {}", id);
    Person person = Ebean.find(Person.class, id);
    return person;
  }
  
  @RequestMapping(path = "/person", method = RequestMethod.GET)
  public List<Person> findAll(){
    log.info("Bucaremos todas as pessoas");
    List<Person> person = Ebean.find(Person.class).findList();
    return person;
  }
  
}