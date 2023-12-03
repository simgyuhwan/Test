package com.only.practice.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Gyuhwan
 */
@Component
@Scope("request")
public class MyLogger {

  private String uuid;
  private String requestUrl;

  public void setRequestUrl(final String requestUrl) {
    this.requestUrl = requestUrl;
  }

  public void log(String message) {
    System.out.println("[" + uuid + "]" + "[" + requestUrl + "] " + message);
  }

  @PostConstruct
  public void init() {
    uuid = UUID.randomUUID().toString();
    System.out.println("[" + uuid + "] request scope bean created : " + this);
  }

  @PreDestroy
  public void close() {
    System.out.println("[" + uuid + "] request scope bean closed : " + this);
  }

}
