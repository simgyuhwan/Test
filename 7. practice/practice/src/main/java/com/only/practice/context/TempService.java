package com.only.practice.context;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TempService {

  private final TempRepository repository;

  @Transactional
  public boolean question1() {
    String name = "hong";
    Temp temp = Temp.of(name);
    repository.save(temp);
    question1method(name);
    return true;
  }

  @Transactional
  public boolean question1method(String name) {
    Temp temp = repository.findByName(name).orElseThrow(EntityNotFoundException::new);
    temp.changeName("pot");
    return true;
  }

  @Transactional
  public boolean question2() {
    String name = "hong";
    Temp temp = Temp.of(name);
    Temp save = repository.save(temp);
    question2method(save.getId());
    return true;
  }

  @Transactional
  public boolean question2method(Long id) {
    Temp temp = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    temp.changeName("pot");
    return true;
  }

  @Transactional
  public boolean question3() {
    String name = "hong";
    Temp temp = Temp.of(name);
    repository.save(temp);
    question3method(name);
    return true;
  }

  public boolean question3method(String name) {
    Temp temp = repository.findByName(name).orElseThrow(EntityNotFoundException::new);
    temp.changeName("pot");
    return true;
  }

  @Transactional
  public boolean question4() {
    String name = "hong";
    Temp temp = Temp.of(name);
    repository.save(temp);
    question4method(name);
    return true;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public boolean question4method(String name) {
    Temp temp = repository.findByName(name).orElseThrow(EntityNotFoundException::new);
    temp.changeName("pot");
    return true;
  }

  @Transactional
  public boolean question5() {
    String name = "hong";
    Temp temp = Temp.of(name);
    Temp save = repository.save(temp);
    question5method(save.getId());
    return true;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public boolean question5method(Long id) {
    Temp temp = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    temp.changeName("pot");
    return true;
  }

  public boolean question6() {
    String name = "hong";
    Temp temp = Temp.of(name);
    repository.save(temp);
    question6method(name);
    return true;
  }

  public boolean question6method(String name) {
    Temp temp = repository.findByName(name).orElseThrow(EntityNotFoundException::new);
    temp.changeName("pot");
    return true;
  }

  public boolean question7() {
    String name = "hong";
    Temp temp = Temp.of(name);
    repository.save(temp);
    question7method(name);
    return true;
  }

  @Transactional
  public boolean question7method(String name) {
    Temp temp = repository.findByName(name).orElseThrow(EntityNotFoundException::new);
    temp.changeName("pot");
    return true;
  }
}
