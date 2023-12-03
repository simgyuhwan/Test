package com.only.practice.locktest;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Gyuhwan
 */
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from Team t join fetch t.members where t.teamName = :teamName")
    Team findByTeamNameWithLock(@Param("teamName") String teamName);

}
