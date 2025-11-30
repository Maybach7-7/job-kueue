CREATE TABLE task_statuses
(
    id   SERIAL PRIMARY KEY,
    status_name VARCHAR(255) UNIQUE NOT NULL
);

-- Rollback
-- DROP TABLE task_statuses;