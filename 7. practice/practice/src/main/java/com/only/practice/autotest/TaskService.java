package com.only.practice.autotest;

import java.util.List;

/**
 * Created by Gyuhwan
 */
public interface TaskService {

  List<Task> getAllTasks();

  Task getTaskById(Long id);

  Task createTask(Task task);

  Task updateTask(Long id, Task Task);

  void deleteTask(Long id);

}
