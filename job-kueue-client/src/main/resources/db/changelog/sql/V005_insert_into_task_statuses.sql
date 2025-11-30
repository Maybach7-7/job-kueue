INSERT INTO task_statuses(status_name) VALUES ('CREATED'),
                                              ('IN_PROGRESS'),
                                              ('FINISHED'),
                                              ('FAILED');

-- Rollback
-- DELETE FROM task_types WHERE name IN ('SEND_EMAIL', 'IN_PROGRESS', 'FINISHED', 'FAILED');