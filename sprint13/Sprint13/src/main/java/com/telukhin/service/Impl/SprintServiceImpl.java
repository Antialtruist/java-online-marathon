package com.telukhin.service.Impl;

import com.telukhin.domain.Marathon;
import com.telukhin.domain.Sprint;
import com.telukhin.repos.MarathonRepo;
import com.telukhin.repos.SprintRepo;
import com.telukhin.service.Impl.MarathonServiceImpl;
import com.telukhin.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SprintServiceImpl implements SprintService {

    @Autowired
    final private SprintRepo sprintRepo;
    final private MarathonRepo marathonRepo;
    final private MarathonServiceImpl marathonService;

    public SprintServiceImpl(SprintRepo sprintRepo, MarathonRepo marathonRepo, MarathonServiceImpl marathonService) {
        this.sprintRepo = sprintRepo;
        this.marathonRepo = marathonRepo;
        this.marathonService = marathonService;
    }

    @Override
    public List<Sprint> getSprintByMarathonId(long id) {
        Marathon marathon = marathonService.getMarathonById(id);
        return marathon.getSprints();
    }

    @Override
    public boolean addSprintToMarathon(Sprint sprint, Marathon marathon) {
        if (sprint != null && marathon != null) {
            List<Sprint> sprintList = marathon.getSprints();
            sprintList.add(sprint);
            marathon.setSprints(sprintList);
            sprintRepo.save(sprint);
            marathonRepo.save(marathon);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateSprint(Sprint sprint) {
        if (sprint != null) {
            Sprint newSprint = new Sprint();
            newSprint.setFinish(sprint.getFinish());
            newSprint.setMarathon(sprint.getMarathon());
            newSprint.setStartDate(sprint.getStartDate());
            newSprint.setTasks(sprint.getTasks());
            newSprint.setTitle(sprint.getTitle());
            sprintRepo.save(newSprint);
            return true;
        }
        return false;
    }

    @Override
    public Sprint getSprintById(long id) {
        Optional<Sprint> sprint = sprintRepo.findById(id);
        return sprint.orElse(null);
    }
}
