package com.telukhin.service;

import com.telukhin.domain.Marathon;
import com.telukhin.domain.Sprint;

import java.util.List;

public interface SprintService {

    List<Sprint> getSprintByMarathonId(long id);
    boolean addSprintToMarathon(Sprint sprint, Marathon marathon);
    boolean updateSprint(Sprint sprint);
    Sprint getSprintById (long id);
}
