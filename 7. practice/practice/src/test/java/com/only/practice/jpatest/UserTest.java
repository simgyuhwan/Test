package com.only.practice.jpatest;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by Gyuhwan
 */
@DataJpaTest
class UserTest {

  @Autowired
  UserRepository userRepository;

  @Autowired
  ArticleRepository articleRepository;

  @Autowired
  EntityManager em;

  @DisplayName("Lazy type N+1 문제 테스트해보기")
  @Test
  void lazy_type_test() {
    saveUser();

    System.out.println("-----------------------------------------start-------------------------");
    List<User> users = userRepository.findAll();
    System.out.println("-----------------------------------------end-------------------------");

    for (User user : users) {
      System.out.println(user.getArticles().size());
    }

  }

  @DisplayName("페치 조인 테스트")
  @Test
  void fetch_join() {
    saveUser();

    System.out.println("---------------------start-----------------------------");
    List<User> users = userRepository.findAllJPQL();
    System.out.println("---------------------end-----------------------------");

    for (User user : users) {
      System.out.println(user.getArticles().size());
    }
  }

  @DisplayName("EntityGraph 테스트")
  @Test
  void EntityGraphTest() {
    // given
    saveUser();

    // when
    List<User> users = userRepository.findAllEntityGraph();

    // then
    for (User user : users) {
      System.out.println(user.getArticles().size());
    }
  }

  @DisplayName("fetch join을 paging 처리해서 사용해도 N+1 문제가 발생한다.")
  @Test
  void fetchJoinPaging() {
    // given
    saveUser();
    System.out.println("=========start===========");
    PageRequest pageRequest = PageRequest.of(0, 4);

    // when
    List<User> users = userRepository.findAllPage(pageRequest);

    // then
    for (User user : users) {
      System.out.println(user.getArticles().size());
    }
  }

  @DisplayName("ToOne테스트")
  @Test
  void toOneTest() {
    saveUser();
    System.out.println("==============start================");
    PageRequest pageRequest = PageRequest.of(0, 2);

    Page<Article> articlesPages = articleRepository.findAllPage(pageRequest);
    List<Article> articles = articlesPages.getContent();

    for (Article article : articles) {
      System.out.println(article.getUser().getName());
    }

  }

  private void saveUser() {
    User user2 = new User("사용자2");
    User user3 = new User("사용자3");
    User user4 = new User("사용자4");
    User user1 = new User("사용자1");
    Article article = new Article("제목1", "컨탠츠츠츠츠츠츠");
    Article article2 = new Article("제목2", "컨탠츠츠츠츠츠츠");
    Article article3 = new Article("제목3", "컨탠츠츠츠츠츠츠");
    Article article4 = new Article("제목4", "컨탠츠츠츠츠츠츠");
    articleRepository.saveAll(List.of(article, article2, article3, article4));

    user1.addArticle(article);
    user2.addArticle(article2);
    user3.addArticle(article3);
    user4.addArticle(article4);
    userRepository.saveAll(List.of(user1, user2, user3, user4));

    em.flush();
    em.clear();
  }

}