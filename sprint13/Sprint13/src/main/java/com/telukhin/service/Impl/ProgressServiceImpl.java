package com.telukhin.service.Impl;

import com.telukhin.domain.Progress;
import com.telukhin.domain.Task;
import com.telukhin.domain.User;

import com.telukhin.repos.ProgressRepo;
import com.telukhin.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    final private ProgressRepo progressRepo;

    public ProgressServiceImpl(ProgressRepo progressRepo) {
        this.progressRepo = progressRepo;

    }

    @Override
    public Progress getProgressById(long id) {
        Optional<Progress> progress = progressRepo.findById(id);
        return progress.orElse(null);
    }

    @Override
    public Progress addTaskForStudent(Task task, User user) {
        if (user != null && task != null) {
            Progress progress = new Progress();
            progress.setTrainee(user);
            progress.setTask(task);
            progress.setStarted(LocalDate.now());
            progress.setStatus(Progress.TaskStatus.PENDING);
            progress = progressRepo.save(progress);
            return progress;
        } else {
            return new Progress();
        }
    }

        @Override
    public Progress addOrUpdateProgress(Progress progress) {
        if (progress.getId() != null) {
            Optional<Progress> entity = progressRepo.findById(progress.getId());
            if (entity.isPresent()) {
                Progress newProgress = entity.get();
                newProgress.setTrainee(progress.getTrainee());
                newProgress.setTask(progress.getTask());
                newProgress.setStarted(progress.getStarted());
                newProgress.setUpdated(progress.getUpdated());
                newProgress = progressRepo.save(newProgress);
                return newProgress;
            }

        }
            progress = progressRepo.save(progress);
            return progress;
    }


    @Override
    public boolean setStatus(Progress.TaskStatus taskStatus, Progress progress) {
        if (progress.getStatus() != taskStatus) {
            progress.setStatus(taskStatus);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Progress> allProgressByUserIdAndMarathonId(long userId, long marathonId) {
        List<Long> id = progressRepo.allProgressesByUserIdAndMarathonId(userId, marathonId);
        return id.stream().map(this::getProgressById).collect(Collectors.toList());
    }

    @Override
    public List<Progress> allProgressByUserIdAndSprintId(long userId, long sprintId) {
        List<Long> id = progressRepo.allProgressesByUserIdAndSprintId(userId, sprintId);
        return id.stream().map(this::getProgressById).collect(Collectors.toList());
    }
}
