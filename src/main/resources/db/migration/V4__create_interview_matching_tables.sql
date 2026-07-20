CREATE TABLE interviews (
    interview_id BIGINT NOT NULL AUTO_INCREMENT,
    employee_id BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    interview_datetime DATETIME NOT NULL,
    duration_minutes INT NOT NULL,
    interview_type CHAR(2) NOT NULL,
    location_url VARCHAR(500) NULL,
    sales_user_id BIGINT NOT NULL,
    interview_status CHAR(2) NOT NULL,
    interview_result CHAR(2) NULL,
    sales_comment VARCHAR(1000) NULL,
    next_action VARCHAR(1000) NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT NOT NULL,
    deleted_at DATETIME NULL,
    PRIMARY KEY (interview_id),
    CONSTRAINT fk_interviews_employee FOREIGN KEY (employee_id) REFERENCES employees (employee_id),
    CONSTRAINT fk_interviews_project FOREIGN KEY (project_id) REFERENCES projects (project_id),
    CONSTRAINT fk_interviews_sales_user FOREIGN KEY (sales_user_id) REFERENCES users (user_id),
    CONSTRAINT ck_interviews_duration CHECK (duration_minutes > 0),
    INDEX ix_interviews_schedule (employee_id, interview_datetime, interview_status, deleted_at),
    INDEX ix_interviews_project (project_id, interview_datetime, deleted_at),
    INDEX ix_interviews_sales_user (sales_user_id, interview_datetime, deleted_at),
    INDEX ix_interviews_result (interview_result)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE interview_questions (
    question_id BIGINT NOT NULL AUTO_INCREMENT,
    interview_id BIGINT NOT NULL,
    question_category CHAR(2) NOT NULL,
    question_text VARCHAR(1000) NOT NULL,
    answer_text VARCHAR(2000) NULL,
    sales_comment VARCHAR(1000) NULL,
    display_order INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT NOT NULL,
    deleted_at DATETIME NULL,
    PRIMARY KEY (question_id),
    CONSTRAINT fk_interview_questions_interview FOREIGN KEY (interview_id) REFERENCES interviews (interview_id),
    CONSTRAINT ck_interview_questions_order CHECK (display_order >= 1),
    INDEX ix_interview_questions_order (interview_id, display_order, deleted_at),
    INDEX ix_interview_questions_category (question_category)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE matching_results (
    matching_result_id BIGINT NOT NULL AUTO_INCREMENT,
    project_id BIGINT NOT NULL,
    employee_id BIGINT NOT NULL,
    total_score DECIMAL(5, 2) NOT NULL,
    mandatory_score DECIMAL(5, 2) NOT NULL,
    preferred_score DECIMAL(5, 2) NOT NULL,
    experience_score DECIMAL(5, 2) NOT NULL,
    process_score DECIMAL(5, 2) NOT NULL,
    availability_score DECIMAL(5, 2) NOT NULL,
    location_score DECIMAL(5, 2) NOT NULL,
    industry_score DECIMAL(5, 2) NOT NULL,
    matched_conditions TEXT NULL,
    missing_conditions TEXT NULL,
    executed_by BIGINT NOT NULL,
    executed_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (matching_result_id),
    CONSTRAINT fk_matching_results_project FOREIGN KEY (project_id) REFERENCES projects (project_id),
    CONSTRAINT fk_matching_results_employee FOREIGN KEY (employee_id) REFERENCES employees (employee_id),
    CONSTRAINT fk_matching_results_user FOREIGN KEY (executed_by) REFERENCES users (user_id),
    CONSTRAINT ck_matching_total_score CHECK (total_score BETWEEN 0 AND 100),
    CONSTRAINT ck_matching_component_scores CHECK (
        mandatory_score BETWEEN 0 AND 40
        AND preferred_score BETWEEN 0 AND 15
        AND experience_score BETWEEN 0 AND 15
        AND process_score BETWEEN 0 AND 10
        AND availability_score BETWEEN 0 AND 10
        AND location_score BETWEEN 0 AND 5
        AND industry_score BETWEEN 0 AND 5
    ),
    INDEX ix_matching_rank (project_id, executed_at, total_score),
    INDEX ix_matching_employee (employee_id, executed_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
