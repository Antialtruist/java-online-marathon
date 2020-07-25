package com.telukhin.repos;

import com.telukhin.domain.Marathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarathonRepo extends JpaRepository<Marathon,Long> {
}
