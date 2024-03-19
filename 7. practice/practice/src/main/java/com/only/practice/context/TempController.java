package com.only.practice.context;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TempController {
  private final TempService service;

  @PostMapping("/test1")
  public void test1() {
    service.question6();
  }
}
