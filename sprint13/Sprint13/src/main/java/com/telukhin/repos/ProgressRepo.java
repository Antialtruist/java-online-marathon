package com.telukhin.repos;

import com.telukhin.domain.Progress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepo extends JpaRepository<Progress,Long> {

    @Query(value = "SELECT p.id FROM " +
            "`progress` p " +
            "LEFT JOIN `task` t " +
            "on p.task_id = t.id " +
            "WHERE p.user_id =:userId AND t.sprint_id IN ( " +
            "   SELECT id " +
            "   FROM `sprint`" +
            "   WHERE marathon_id =:marathonId" +
            ")",
            nativeQuery = true)
    List<Long> allProgressesByUserIdAndMarathonId(@Param("userId") Long userId, @Param("marathonId") Long marathonId);

    @Query(value = "SELECT p.id " +
            "FROM `progress` p " +
            "LEFT JOIN `task` t ON p.task_id = t.id " +
            "WHERE t.sprint_id =:sprintId  AND p.user_id =:userId",
            nativeQuery = true)
    List<Long> allProgressesByUserIdAndSprintId(Long userId, Long sprintId);
}

