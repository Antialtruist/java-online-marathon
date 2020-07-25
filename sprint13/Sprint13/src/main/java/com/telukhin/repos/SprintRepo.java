package com.telukhin.repos;

import com.telukhin.domain.Marathon;
import com.telukhin.domain.Sprint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepo extends JpaRepository<Sprint,Long> {
    List<Sprint> getAllByMarathon(Marathon marathon);
}
