package com.only.practice.autotest;

import org.springframework.stereotype.Service;

/**
 * Created by Gyuhwan
 */
@Service("cat")
public class Cat implements Animal {

  @Override
  public void makeSound() {
    System.out.println("Meow!!");
  }
}
