CREATE TABLE task_types
(
    id   SERIAL PRIMARY KEY ,
    type_name VARCHAR(255) UNIQUE NOT NULL
);

-- Rollback
-- DROP TABLE task_types;