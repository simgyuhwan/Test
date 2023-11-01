package com.only.practice.jpatest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

/**
 * Created by Gyuhwan
 */
@Entity
@Getter
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 10, nullable = false)
  private String name;

  @OneToMany(mappedBy = "user")
  private Set<Article> articles = new HashSet<>();

  public User(String name) {
    this.name = name;
  }

  public User() {

  }

  public void addArticle(Article article) {
    this.articles.add(article);
    article.setUser(this);
  }
}

