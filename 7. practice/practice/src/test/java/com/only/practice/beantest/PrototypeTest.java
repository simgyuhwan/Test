package com.only.practice.beantest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

/**
 * Created by Gyuhwan
 */
@SpringBootTest
class PrototypeTest {

  @Test
  void singletonClientUserPrototype() {
    final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        PrototypeBean.class, ClientBean.class);
    final ClientBean clientBean1 = ac.getBean(ClientBean.class);
    final int count1 = clientBean1.logic();
    assertThat(count1, equalTo(1));

    final ClientBean clientBean2 = ac.getBean(ClientBean.class);
    final int count2 = clientBean2.logic();
    assertThat(count2, equalTo(1));

    ac.close();
  }

  @Test
  void prototypeBeanFind() {
    ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
    System.out.println("find prototypeBean1");
    final PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    System.out.println("find prototypeBean2");
    final PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

    assertThat(prototypeBean1, not(prototypeBean2));
    ac.close();
  }

  @Scope("singleton")
  static class ClientBean {

    private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

    public ClientBean(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
      this.prototypeBeanProvider = prototypeBeanProvider;
    }

    //    private final PrototypeBean prototypeBean;
//
//    public ClientBean(final PrototypeBean prototypeBean) {
//      this.prototypeBean = prototypeBean;
//    }

    public int logic() {
      PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("prototype")
  static class PrototypeBean {

    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}
