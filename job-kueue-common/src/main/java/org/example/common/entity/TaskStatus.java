package org.example.common.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "task_statuses")
@Data
public class TaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status_name", unique = true, nullable = false)
    private String statusName;
}
