package com.only.practice.testcontainer;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Gyuhwan
 */
@RestController
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerRepository customerRepository;

  @GetMapping("/api/customers")
  public List<Customer> getAll() {
    return customerRepository.findAll();
  }

}
