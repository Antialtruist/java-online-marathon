package com.telukhin.service;

import com.telukhin.domain.Sprint;
import com.telukhin.domain.Task;

public interface TaskService {

    Task addTaskToSprint(Task task, Sprint sprint);
    Task getTaskById(long id);
}
