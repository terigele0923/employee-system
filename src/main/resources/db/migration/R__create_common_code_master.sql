CREATE TABLE IF NOT EXISTS common_codes (
    code_type VARCHAR(50) NOT NULL,
    code_value VARCHAR(10) NOT NULL,
    code_name VARCHAR(100) NOT NULL,
    display_order INT NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (code_type, code_value),
    INDEX ix_common_codes_active (
        code_type,
        is_active,
        display_order
    )
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO common_codes (
    code_type,
    code_value,
    code_name,
    display_order
) VALUES
    ('EMPLOYMENT_STATUS', '01', '在籍中', 1),
    ('EMPLOYMENT_STATUS', '02', '休職中', 2),
    ('EMPLOYMENT_STATUS', '09', '退職済み', 9),
    ('WORK_STATUS', '01', '待機中', 1),
    ('WORK_STATUS', '02', '面談調整中', 2),
    ('WORK_STATUS', '03', '稼働中', 3),
    ('WORK_STATUS', '04', '休職中', 4),
    ('WORK_STATUS', '09', '退職済み', 9),
    ('INTERVIEW_TYPE', '01', '対面', 1),
    ('INTERVIEW_TYPE', '02', 'オンライン', 2),
    ('INTERVIEW_TYPE', '03', '電話', 3),
    ('INTERVIEW_STATUS', '01', '予定', 1),
    ('INTERVIEW_STATUS', '02', '実施済み', 2),
    ('INTERVIEW_STATUS', '03', '延期', 3),
    ('INTERVIEW_STATUS', '04', 'キャンセル', 4),
	('ASSIGNMENT_STATUS', '01', '予定', 1),
	('ASSIGNMENT_STATUS', '02', '参画中', 2),
	('ASSIGNMENT_STATUS', '03', '終了', 3)
ON DUPLICATE KEY UPDATE
    code_name = VALUES(code_name),
    display_order = VALUES(display_order),
    is_active = TRUE,
    updated_at = CURRENT_TIMESTAMP;
