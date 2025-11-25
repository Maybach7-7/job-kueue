package org.example.client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_type_id", nullable = false)
    private TaskType taskType;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
