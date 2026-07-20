INSERT INTO roles (role_id, role_code, role_name, created_at, updated_at) VALUES
    (1, 'ADMIN', '管理者', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'MANAGER', '営業責任者', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'SALES', '営業担当者', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Local development account: login_id=admin / password=password
-- Change this password before using the system outside local development.
INSERT INTO users (
    user_id, role_id, login_id, user_name, email, password_hash,
    is_active, created_at, created_by, updated_at, updated_by
) VALUES (
    1, 1, 'admin', 'システム管理者', 'admin@example.local',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
);

INSERT INTO skills (
    skill_id, category_code, skill_name, is_active,
    created_at, created_by, updated_at, updated_by
) VALUES
    (1, '01', 'Java', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    (2, '01', 'PHP', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    (3, '01', 'JavaScript', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    (4, '02', 'Spring Boot', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    (5, '02', 'Laravel', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    (6, '02', 'MyBatis', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    (7, '03', 'MySQL', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    (8, '03', 'Oracle', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    (9, '04', 'AWS', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    (10, '05', 'Docker', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    (11, '05', 'Git', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1);

INSERT INTO clients (
    client_id, client_code, client_name, is_active,
    created_at, created_by, updated_at, updated_by
) VALUES
    (1, 'C0001', 'サンプル顧客A', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    (2, 'C0002', 'サンプル顧客B', TRUE, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1);
