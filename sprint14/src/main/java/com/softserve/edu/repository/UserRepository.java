package com.softserve.edu.repository;


import com.softserve.edu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> getAllByRole(User.Role role);

    @Query(value =
            "SELECT u.* " +
            "FROM users u" +
            "   JOIN  marathon_user mu on mu.user_id = u.id " +
            "WHERE mu.marathon_id = :marathon_id " +
            "   AND u.role = 'TRAINEE'",
    nativeQuery = true)
    List<User> getStudentByMarathonId(@Param("marathon_id") Long marathonId);

    @Query(value =
            "DELETE FROM marathon_user " +
            "WHERE user_id = :user_id " +
            "   AND marathon_id = :marathon_id",
            nativeQuery = true)
    boolean removeFromMarathon(@Param("user_id") Long studentId, @Param("marathon_id") Long marathonId);

}
