package com.only.practice.context;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Temp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  public Temp(String name) {
    this.name = name;
  }

  public void changeName(String changeName) {
    this.name = changeName;
  }

  public static Temp of(String name) {
    return new Temp(name);
  }
}
