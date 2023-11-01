package com.only.practice.jpatest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Gyuhwan
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

  @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
  @Query("select a from Article a left join a.user")
  Page<Article> findAllPage(Pageable pageable);
}
