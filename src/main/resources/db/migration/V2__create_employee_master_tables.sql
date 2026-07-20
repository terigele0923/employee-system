CREATE TABLE skills (
    skill_id BIGINT NOT NULL AUTO_INCREMENT,
    category_code CHAR(2) NOT NULL,
    skill_name VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT NOT NULL,
    deleted_at DATETIME NULL,
    PRIMARY KEY (skill_id),
    CONSTRAINT uk_skills_skill_name UNIQUE (skill_name),
    INDEX ix_skills_category_active (category_code, is_active, deleted_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE clients (
    client_id BIGINT NOT NULL AUTO_INCREMENT,
    client_code VARCHAR(20) NOT NULL,
    client_name VARCHAR(200) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT NOT NULL,
    deleted_at DATETIME NULL,
    PRIMARY KEY (client_id),
    CONSTRAINT uk_clients_client_code UNIQUE (client_code),
    INDEX ix_clients_name_active (client_name, is_active, deleted_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE employees (
    employee_id BIGINT NOT NULL AUTO_INCREMENT,
    employee_no VARCHAR(10) NOT NULL,
    employee_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    phone_no VARCHAR(20) NOT NULL,
    postal_code VARCHAR(8) NULL,
    address VARCHAR(200) NOT NULL,
    nearest_station VARCHAR(100) NOT NULL,
    join_date DATE NOT NULL,
    employment_status CHAR(2) NOT NULL,
    work_status CHAR(2) NOT NULL,
    sales_user_id BIGINT NOT NULL,
    remarks VARCHAR(500) NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT NOT NULL,
    deleted_at DATETIME NULL,
    PRIMARY KEY (employee_id),
    CONSTRAINT uk_employees_employee_no UNIQUE (employee_no),
    CONSTRAINT fk_employees_sales_user FOREIGN KEY (sales_user_id) REFERENCES users (user_id),
    INDEX ix_employees_search (work_status, sales_user_id, nearest_station, deleted_at),
    INDEX ix_employees_employment_status (employment_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE employee_skills (
    employee_skill_id BIGINT NOT NULL AUTO_INCREMENT,
    employee_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,
    experience_months INT NOT NULL,
    skill_level TINYINT NOT NULL,
    last_used_ym CHAR(6) NULL,
    remarks VARCHAR(500) NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT NOT NULL,
    deleted_at DATETIME NULL,
    PRIMARY KEY (employee_skill_id),
    CONSTRAINT uk_employee_skills UNIQUE (employee_id, skill_id),
    CONSTRAINT fk_employee_skills_employee FOREIGN KEY (employee_id) REFERENCES employees (employee_id),
    CONSTRAINT fk_employee_skills_skill FOREIGN KEY (skill_id) REFERENCES skills (skill_id),
    CONSTRAINT ck_employee_skills_experience CHECK (experience_months >= 0),
    CONSTRAINT ck_employee_skills_level CHECK (skill_level BETWEEN 1 AND 5),
    CONSTRAINT ck_employee_skills_last_used CHECK (last_used_ym IS NULL OR last_used_ym REGEXP '^[0-9]{6}$'),
    INDEX ix_employee_skills_skill (skill_id, experience_months, deleted_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
