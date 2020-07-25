package com.telukhin.service;

import com.telukhin.domain.Marathon;
import com.telukhin.domain.User;

import java.util.List;

public interface MarathonService {

    List<Marathon> getAll();
    Marathon getMarathonById(long id);
    Marathon createOrUpdate(Marathon marathon);
    void deleteMarathonById(long id);

}
