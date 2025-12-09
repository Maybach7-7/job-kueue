package org.example.common.repository;

import org.example.common.entity.Task;
import org.example.common.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Modifying
    @Transactional
    @Query("update Task t SET t.taskStatus = :newStatus WHERE t.id = :taskId")
    void updateStatus(@Param("taskId") Integer taskId, @Param("newStatus") TaskStatus newStatus);

    @Modifying
    @Transactional
    @Query("update Task t SET t.startTime = CURRENT_TIMESTAMP WHERE t.id = :taskId")
    void updateStartTime(@Param("taskId") Integer taskId);

    @Modifying
    @Transactional
    @Query("update Task t SET t.endTime = CURRENT_TIMESTAMP WHERE t.id = :taskId")
    void updateEndTime(@Param("taskId") Integer taskId);

    @Query("SELECT t FROM Task t JOIN FETCH t.taskType JOIN FETCH t.taskStatus WHERE t.id = :id")
    Optional<Task> findByIdWithDetails(@Param("id") Integer id);
}
