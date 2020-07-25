package com.telukhin.service.Impl;

import com.telukhin.domain.Sprint;
import com.telukhin.domain.Task;
import com.telukhin.repos.TaskRepo;
import com.telukhin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    final private TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }


    @Override
    public Task addTaskToSprint(Task task, Sprint sprint) {
        sprint.getTasks().add(task);
        return taskRepo.save(task);
    }

    @Override
    public Task getTaskById(long id) {
        Optional<Task> task = taskRepo.findById(id);
        return task.orElse(null);
    }
}
