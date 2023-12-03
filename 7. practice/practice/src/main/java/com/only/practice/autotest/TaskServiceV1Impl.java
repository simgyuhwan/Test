package com.only.practice.autotest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * Created by Gyuhwan
 */
@Service("v1")
public class TaskServiceV1Impl implements TaskService {

  List<Task> tasks = new ArrayList<>();

  @Override
  public List<Task> getAllTasks() {
    return tasks;
  }

  @Override
  public Task getTaskById(Long id) {
    return tasks.stream()
        .filter(t -> Objects.equals(t.getId(), id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public Task createTask(Task task) {
    tasks.add(task);
    return task;
  }

  @Override
  public Task updateTask(Long id, Task task) {
    for (Task task1 : tasks) {
      if (Objects.equals(task1.getId(), id)) {
        tasks.remove(task1);
      }
    }
    tasks.add(task);
    return task;
  }

  @Override
  public void deleteTask(Long id) {
    for (int i = 0; i < tasks.size(); i++) {
      if (Objects.equals(tasks.get(i).getId(), id)) {
        tasks.remove(i);
        break;
      }
    }
  }
}
