package org.example.common.repository;

import org.example.common.entity.Task;
import org.example.common.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Modifying
    @Transactional
    @Query("update Task t SET t.taskStatus = :newStatus WHERE t.id = :taskId")
    void updateStatus(@Param("taskId") Integer taskId, @Param("newStatus") TaskStatus newStatus);
}
