package com.only.practice.autotest;

import org.springframework.stereotype.Service;

/**
 * Created by Gyuhwan
 */
@Service("dog")
public class Dog implements Animal {

  @Override
  public void makeSound() {
    System.out.println("Woof!!!!!");
  }
}
