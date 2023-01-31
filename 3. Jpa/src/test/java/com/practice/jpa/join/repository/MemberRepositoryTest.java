package com.practice.jpa.join.repository;

import com.practice.jpa.join.entity.Member;
import com.practice.jpa.join.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * MemberRepositoryTest.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.31
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @DisplayName("LAZY로 member만 조회시 Select 쿼리를 한번만 진행한다.")
    void member_select_one_test(){
        Team team1 = new Team("First Team");
        Team team2 = new Team("Second Team");

        teamRepository.save(team1);
        teamRepository.save(team2);

        Member member1 = new Member("sim", 10, team1);
        Member member2 = new Member("hwan", 20, team2);

        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findAll();
        members.forEach(System.out::println);
    }

    @Test
    @Transactional
    @DisplayName("LAZY로 member와 Team 조회시 Select 쿼리는 Team 개수만큼 진행한다.")
    void member_select_many_test(){
        Team team1 = new Team("First Team");
        Team team2 = new Team("Second Team");

        teamRepository.save(team1);
        teamRepository.save(team2);

        Member member1 = new Member("sim", 10, team1);
        Member member2 = new Member("hwan", 20, team2);

        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findAll();
        members.forEach(m -> {
            System.out.println(m);
            System.out.println(m.getTeam());
        });
    }

    @Test
    @DisplayName("fetch join Test")
    void fetch_join_test() {
        Team team1 = new Team("First Team");
        Team team2 = new Team("Second Team");

        teamRepository.save(team1);
        teamRepository.save(team2);

        Member member1 = new Member("sim", 10, team1);
        Member member2 = new Member("hwan", 20, team2);

        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findAllMembers();
        members.forEach(m -> {
            System.out.println(m);
            System.out.println(m.getTeam());
        });
    }
}