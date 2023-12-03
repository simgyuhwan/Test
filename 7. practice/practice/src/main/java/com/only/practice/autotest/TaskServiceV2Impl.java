package com.only.practice.autotest;

import org.springframework.stereotype.Service;

/**
 * Created by Gyuhwan
 */
@Service("v2")
public class TaskServiceV2Impl extends TaskServiceV1Impl {

  @Override
  public Task createTask(Task task) {
    System.out.println("v2 test");
    tasks.add(task);
    return task;
  }
}
