package com.only.practice.nullcheck;


import lombok.NonNull;

/**
 * Created by Gyuhwan
 */
public class AnnotationCheck {

  private String name;

  public AnnotationCheck(@NonNull String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
