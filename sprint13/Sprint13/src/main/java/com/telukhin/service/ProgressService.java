package com.telukhin.service;

import com.telukhin.domain.Progress;
import com.telukhin.domain.Task;
import com.telukhin.domain.User;

import java.util.List;

public interface ProgressService {

    Progress getProgressById(long id);
    Progress addTaskForStudent(Task task, User user);
    Progress addOrUpdateProgress(Progress progress);
    boolean setStatus(Progress.TaskStatus taskStatus, Progress progress);
    List<Progress> allProgressByUserIdAndMarathonId(long userId, long marathonId);
    List<Progress> allProgressByUserIdAndSprintId(long userId, long sprintId);

}
