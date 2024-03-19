package com.only.practice.context;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TempRepository extends JpaRepository<Temp, Long> {

  Optional<Temp> findByName(String name);

  @Query("select t from Temp t where t.name= :name")
  Optional<Temp> findByNameQuery(@Param("name") String name);
}
