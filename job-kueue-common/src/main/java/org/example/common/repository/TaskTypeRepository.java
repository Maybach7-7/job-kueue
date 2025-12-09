package org.example.common.repository;

import org.example.common.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTypeRepository extends JpaRepository<TaskType, Integer> {
    TaskType findByTypeName(String name);
}
