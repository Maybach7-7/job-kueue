CREATE TABLE tasks
(
    id             serial PRIMARY KEY,
    task_type_id   int NOT NULL,
    task_status_id int NOT NULL,
    created_time   TIMESTAMP WITHOUT TIME ZONE DEFAULT (NOW() AT TIME ZONE 'utc'),
    start_time     TIMESTAMP WITHOUT TIME ZONE,
    end_time       TIMESTAMP WITHOUT TIME ZONE,
    FOREIGN KEY (task_type_id) REFERENCES task_types (id),
    FOREIGN KEY (task_status_id) REFERENCES task_statuses (id)
);

-- Rollback
-- DROP TABLE tasks;