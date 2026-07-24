ALTER TABLE matching_results
    ADD COLUMN deleted_at DATETIME NULL AFTER executed_at,
    ADD INDEX ix_matching_results_deleted_at (deleted_at);
