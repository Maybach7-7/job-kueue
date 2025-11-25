CREATE TABLE tasks
(
    id           bigint PRIMARY KEY,
    task_type_id bigint      NOT NULL,
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    created_at   TIMESTAMP WITHOUT TIME ZONE DEFAULT (NOW() AT TIME ZONE 'utc'),
    FOREIGN KEY (task_type_id) REFERENCES task_types(id)
);

-- Rollback
-- DROP TABLE tasks;