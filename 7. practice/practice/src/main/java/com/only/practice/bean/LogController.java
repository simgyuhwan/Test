package com.only.practice.bean;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Gyuhwan
 */
@RestController
@RequiredArgsConstructor
public class LogController {

  private final ObjectProvider<MyLogger> myLoggerProvider;
  private int count = 0;

  @RequestMapping("/bean-test")
  public String logTest(HttpServletRequest request) throws InterruptedException {
    String requestURL = request.getRequestURL().toString();
    MyLogger myLogger = myLoggerProvider.getObject();
    myLogger.setRequestUrl(requestURL);
    myLogger.log("controller test : " + count++);
    Thread.sleep(1000);

    return "Ok";
  }
}
