package br.com.rcc_dev.testes.entities.db;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "person")
public class Person {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  
  @Column(name = "name")
  private String name;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "birthdate")
  private LocalDate birthdate;
  
  @Column(name = "sex")
  private boolean sex;
  
}
