package com.only.practice.jpatest;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Gyuhwan
 */
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select distinct u from User u join fetch u.articles")
  List<User> findAllJPQL();

  @EntityGraph(attributePaths = {"articles"}, type = EntityGraphType.FETCH)
  @Query("select distinct u from User u left join u.articles")
  List<User> findAllEntityGraph();

  @EntityGraph(attributePaths = {"articles"}, type = EntityGraphType.FETCH)
  @Query("select distinct u from User u left join u.articles")
  List<User> findAllPage(PageRequest pageable);
}
