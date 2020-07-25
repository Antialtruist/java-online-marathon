package com.telukhin.service.Impl;

import com.telukhin.domain.Marathon;

import com.telukhin.repos.MarathonRepo;
import com.telukhin.service.MarathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarathonServiceImpl implements MarathonService {

    @Autowired
    final private MarathonRepo marathonRepo;

    public MarathonServiceImpl(MarathonRepo marathonRepo) {
        this.marathonRepo = marathonRepo;
    }

    @Override
    public List<Marathon> getAll() {
        return marathonRepo.findAll();
    }

    @Override
    public Marathon getMarathonById(long id) {
        Optional<Marathon> marathon = marathonRepo.findById(id);
        return marathon.orElse(null);
    }

    @Override
    public Marathon createOrUpdate(Marathon marathon) {
        if (marathon.getId() != null) {
            Optional<Marathon> entity = marathonRepo.findById(marathon.getId());

            if (entity.isPresent()) {
                Marathon newMarathon = entity.get();
                newMarathon.setSprints(marathon.getSprints());
                newMarathon.setUsers(marathon.getUsers());
                newMarathon.setTitle(marathon.getTitle());
            }
        }
        marathon = marathonRepo.save(marathon);
        return marathon;
    }
    @Override
    public void deleteMarathonById(long id) {
        marathonRepo.delete(marathonRepo.getOne(id));
    }
}
