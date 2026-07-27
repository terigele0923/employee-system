UPDATE users
SET password_hash = '$2a$10$m2Ci/Fy61Fbl.pKADiIxte.IFJehxkMGGjmS5Wlm9fcArgE9w6hEW',
    updated_at = CURRENT_TIMESTAMP
WHERE login_id IN ('admin', 'sales01', 'sales02');
