-- Dashboard test data
--
-- Expected dashboard totals when logged in as ADMIN or MANAGER:
--   waiting employees             : 6
--   active employees              : 8
--   contracts ending within 30 days: 4
--   open projects                 : 6
--
-- Interview type convention used by this test data:
--   01 = face-to-face, 02 = online, 03 = telephone

-- Test sales users (password: password)
INSERT INTO users (
    role_id,
    login_id,
    user_name,
    email,
    password_hash,
    is_active,
    created_at,
    created_by,
    updated_at,
    updated_by
) VALUES
    (
        (SELECT role_id FROM roles WHERE role_code = 'SALES'),
        'sales01',
        '営業担当 佐藤',
        'sales01@example.local',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
        TRUE,
        CURRENT_TIMESTAMP,
        1,
        CURRENT_TIMESTAMP,
        1
    ),
    (
        (SELECT role_id FROM roles WHERE role_code = 'SALES'),
        'sales02',
        '営業担当 田中',
        'sales02@example.local',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
        TRUE,
        CURRENT_TIMESTAMP,
        1,
        CURRENT_TIMESTAMP,
        1
    );

-- Projects: six open projects and four projects in other states.
INSERT INTO projects (
    project_no,
    client_id,
    project_name,
    project_summary,
    industry_code,
    work_location,
    nearest_station,
    remote_type,
    planned_start_date,
    planned_end_date,
    headcount,
    unit_price,
    commercial_flow,
    interview_count,
    project_status,
    sales_user_id,
    remarks,
    created_at,
    created_by,
    updated_at,
    updated_by
) VALUES
    (
        'P000000001', 1, '販売管理システム改修', '販売管理システムの機能追加と保守',
        '01', '東京都千代田区', '東京駅', '02',
        DATE_ADD(CURRENT_DATE, INTERVAL 20 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 200 DAY),
        3, 700000, 'エンド→元請→当社', 2, '01',
        (SELECT user_id FROM users WHERE login_id = 'sales01'),
        'ダッシュボードテスト用募集中案件',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        'P000000002', 1, '物流システム再構築', '物流管理基盤の再構築',
        '02', '東京都港区', '品川駅', '01',
        DATE_ADD(CURRENT_DATE, INTERVAL 25 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 240 DAY),
        4, 750000, 'エンド→当社', 2, '01',
        (SELECT user_id FROM users WHERE login_id = 'sales02'),
        'ダッシュボードテスト用募集中案件',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        'P000000003', 2, '金融API開発', '金融サービス向けAPI開発',
        '03', '東京都新宿区', '新宿駅', '02',
        DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 180 DAY),
        2, 800000, 'エンド→元請→当社', 1, '01',
        (SELECT user_id FROM users WHERE login_id = 'sales01'),
        'ダッシュボードテスト用募集中案件',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        'P000000004', 2, 'ECサイト機能追加', 'ECサイトの決済・会員機能追加',
        '04', '東京都渋谷区', '渋谷駅', '03',
        DATE_ADD(CURRENT_DATE, INTERVAL 15 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 150 DAY),
        3, 680000, 'エンド→当社', 2, '01',
        (SELECT user_id FROM users WHERE login_id = 'sales02'),
        'ダッシュボードテスト用募集中案件',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        'P000000005', 1, '人事管理クラウド開発', '人事管理クラウドサービスの新規開発',
        '05', '神奈川県横浜市', '横浜駅', '02',
        DATE_ADD(CURRENT_DATE, INTERVAL 35 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 210 DAY),
        5, 720000, 'エンド→元請→当社', 2, '01',
        (SELECT user_id FROM users WHERE login_id = 'sales01'),
        'ダッシュボードテスト用募集中案件',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        'P000000006', 2, '予約管理アプリ開発', '予約管理Webアプリケーション開発',
        '06', '千葉県千葉市', '海浜幕張駅', '03',
        DATE_ADD(CURRENT_DATE, INTERVAL 40 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 220 DAY),
        2, 650000, 'エンド→当社', 1, '01',
        (SELECT user_id FROM users WHERE login_id = 'sales02'),
        'ダッシュボードテスト用募集中案件',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        'P000000007', 1, '会計システム刷新', '会計システムの刷新プロジェクト',
        '01', '東京都中央区', '日本橋駅', '02',
        DATE_ADD(CURRENT_DATE, INTERVAL 10 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 190 DAY),
        2, 780000, 'エンド→元請→当社', 2, '02',
        (SELECT user_id FROM users WHERE login_id = 'sales01'),
        '面談調整中案件',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        'P000000008', 2, '顧客管理システム開発', '顧客管理システムの開発と運用',
        '02', '東京都豊島区', '池袋駅', '01',
        DATE_SUB(CURRENT_DATE, INTERVAL 180 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY),
        6, 690000, 'エンド→当社', 1, '03',
        (SELECT user_id FROM users WHERE login_id = 'sales02'),
        '成約済み案件',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        'P000000009', 1, '基幹システム運用保守', '基幹システムの運用保守',
        '03', '東京都江東区', '豊洲駅', '01',
        DATE_SUB(CURRENT_DATE, INTERVAL 240 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 90 DAY),
        5, 660000, 'エンド→元請→当社', 1, '03',
        (SELECT user_id FROM users WHERE login_id = 'sales01'),
        '成約済み案件',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        'P000000010', 2, '旧勤怠システム改修', '旧勤怠システムの改修案件',
        '04', '埼玉県さいたま市', '大宮駅', '01',
        DATE_SUB(CURRENT_DATE, INTERVAL 300 DAY), DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY),
        2, 600000, 'エンド→当社', 1, '04',
        (SELECT user_id FROM users WHERE login_id = 'sales02'),
        '募集終了案件',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    );

-- Twenty employees:
--   E000000001-E000000006: waiting
--   E000000007-E000000010: arranging interviews
--   E000000011-E000000018: active
--   E000000019: on leave
--   E000000020: retired
INSERT INTO employees (
    employee_no,
    employee_name,
    birth_date,
    phone_no,
    postal_code,
    address,
    nearest_station,
    join_date,
    employment_status,
    work_status,
    sales_user_id,
    remarks,
    created_at,
    created_by,
    updated_at,
    updated_by
) VALUES
    ('E000000001', '山田 太郎', '1990-01-15', '090-1000-0001', '100-0001', '東京都千代田区千代田1-1', '東京駅', '2020-04-01', '01', '01', (SELECT user_id FROM users WHERE login_id = 'sales01'), '待機中テストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000002', '佐々木 花子', '1992-02-20', '090-1000-0002', '105-0001', '東京都港区虎ノ門1-1', '新橋駅', '2021-04-01', '01', '01', (SELECT user_id FROM users WHERE login_id = 'sales02'), '待機中テストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000003', '鈴木 一郎', '1988-03-10', '090-1000-0003', '160-0022', '東京都新宿区新宿1-1', '新宿駅', '2018-10-01', '01', '01', (SELECT user_id FROM users WHERE login_id = 'sales01'), '待機中テストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000004', '高橋 美咲', '1995-04-18', '090-1000-0004', '150-0002', '東京都渋谷区渋谷1-1', '渋谷駅', '2022-04-01', '01', '01', (SELECT user_id FROM users WHERE login_id = 'sales02'), '待機中テストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000005', '田中 健太', '1991-05-25', '090-1000-0005', '220-0011', '神奈川県横浜市西区1-1', '横浜駅', '2019-07-01', '01', '01', (SELECT user_id FROM users WHERE login_id = 'sales01'), '待機中テストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000006', '伊藤 愛', '1994-06-12', '090-1000-0006', '261-0021', '千葉県千葉市美浜区1-1', '海浜幕張駅', '2023-01-01', '01', '01', (SELECT user_id FROM users WHERE login_id = 'sales02'), '待機中テストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000007', '渡辺 翔', '1989-07-08', '090-1000-0007', '103-0027', '東京都中央区日本橋1-1', '日本橋駅', '2017-04-01', '01', '02', (SELECT user_id FROM users WHERE login_id = 'sales01'), '面談調整中テストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000008', '中村 葵', '1993-08-16', '090-1000-0008', '171-0022', '東京都豊島区南池袋1-1', '池袋駅', '2020-10-01', '01', '02', (SELECT user_id FROM users WHERE login_id = 'sales02'), '面談調整中テストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000009', '小林 大輔', '1987-09-22', '090-1000-0009', '135-0061', '東京都江東区豊洲1-1', '豊洲駅', '2016-04-01', '01', '02', (SELECT user_id FROM users WHERE login_id = 'sales01'), '面談調整中テストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000010', '加藤 結衣', '1996-10-05', '090-1000-0010', '330-0853', '埼玉県さいたま市大宮区1-1', '大宮駅', '2023-04-01', '01', '02', (SELECT user_id FROM users WHERE login_id = 'sales02'), '面談調整中テストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000011', '吉田 直樹', '1986-11-11', '090-1000-0011', '100-0002', '東京都千代田区皇居外苑1-1', '東京駅', '2015-04-01', '01', '03', (SELECT user_id FROM users WHERE login_id = 'sales01'), '期限超過契約', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000012', '山本 彩', '1990-12-19', '090-1000-0012', '108-0075', '東京都港区港南1-1', '品川駅', '2018-04-01', '01', '03', (SELECT user_id FROM users WHERE login_id = 'sales02'), '契約終了7日後', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000013', '松本 拓也', '1985-01-28', '090-1000-0013', '160-0023', '東京都新宿区西新宿1-1', '新宿駅', '2014-04-01', '01', '03', (SELECT user_id FROM users WHERE login_id = 'sales01'), '契約終了14日後', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000014', '井上 真央', '1992-02-14', '090-1000-0014', '150-0043', '東京都渋谷区道玄坂1-1', '渋谷駅', '2019-04-01', '01', '03', (SELECT user_id FROM users WHERE login_id = 'sales02'), '契約終了20日後', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000015', '木村 亮', '1988-03-30', '090-1000-0015', '220-0005', '神奈川県横浜市西区南幸1-1', '横浜駅', '2017-10-01', '01', '03', (SELECT user_id FROM users WHERE login_id = 'sales01'), '契約終了30日後', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000016', '林 優奈', '1994-04-09', '090-1000-0016', '261-0011', '千葉県千葉市美浜区真砂1-1', '検見川浜駅', '2021-04-01', '01', '03', (SELECT user_id FROM users WHERE login_id = 'sales02'), '契約終了45日後', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000017', '清水 達也', '1989-05-17', '090-1000-0017', '135-0063', '東京都江東区有明1-1', '有明駅', '2018-07-01', '01', '03', (SELECT user_id FROM users WHERE login_id = 'sales01'), '契約終了90日後', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000018', '斎藤 玲奈', '1993-06-24', '090-1000-0018', '171-0014', '東京都豊島区池袋1-1', '池袋駅', '2020-04-01', '01', '03', (SELECT user_id FROM users WHERE login_id = 'sales02'), '終了日未定契約', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000019', '森 健一', '1984-07-13', '090-1000-0019', '330-0846', '埼玉県さいたま市大宮区大門町1-1', '大宮駅', '2013-04-01', '02', '04', (SELECT user_id FROM users WHERE login_id = 'sales01'), '休職中テストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1),
    ('E000000020', '池田 香織', '1982-08-21', '090-1000-0020', '103-0013', '東京都中央区日本橋人形町1-1', '人形町駅', '2010-04-01', '09', '09', (SELECT user_id FROM users WHERE login_id = 'sales02'), '退職済みテストデータ', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1);

-- Eight active assignments covering every contract warning level.
INSERT INTO assignments (
    employee_id,
    project_id,
    contract_start_date,
    contract_end_date,
    assignment_status,
    role_name,
    processes,
    remarks,
    created_at,
    created_by,
    updated_at,
    updated_by
) VALUES
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000011'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000008'),
        DATE_SUB(CURRENT_DATE, INTERVAL 180 DAY),
        DATE_SUB(CURRENT_DATE, INTERVAL 3 DAY),
        '02', 'メンバー', '開発・テスト', '警告レベル03（期限超過）',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000012'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000008'),
        DATE_SUB(CURRENT_DATE, INTERVAL 150 DAY),
        DATE_ADD(CURRENT_DATE, INTERVAL 7 DAY),
        '02', 'メンバー', '詳細設計・開発・テスト', '警告レベル02',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000013'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000009'),
        DATE_SUB(CURRENT_DATE, INTERVAL 120 DAY),
        DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY),
        '02', 'リーダー', '基本設計・詳細設計・開発', '警告レベル02境界値',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000014'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000008'),
        DATE_SUB(CURRENT_DATE, INTERVAL 100 DAY),
        DATE_ADD(CURRENT_DATE, INTERVAL 20 DAY),
        '02', 'メンバー', '開発・テスト', '警告レベル01',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000015'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000009'),
        DATE_SUB(CURRENT_DATE, INTERVAL 90 DAY),
        DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY),
        '02', 'サブリーダー', '詳細設計・開発・テスト', '警告レベル01境界値',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000016'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000008'),
        DATE_SUB(CURRENT_DATE, INTERVAL 80 DAY),
        DATE_ADD(CURRENT_DATE, INTERVAL 45 DAY),
        '02', 'メンバー', '開発・テスト', '警告レベル00',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000017'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000009'),
        DATE_SUB(CURRENT_DATE, INTERVAL 60 DAY),
        DATE_ADD(CURRENT_DATE, INTERVAL 90 DAY),
        '02', 'リーダー', '要件定義・基本設計', '警告レベル00',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000018'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000008'),
        DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY),
        NULL,
        '02', 'メンバー', '運用・保守', '終了日未定',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    );

-- Scheduled interviews: eight future rows are intentionally inserted so that
-- the dashboard LIMIT can be verified. The first five include all three types.
INSERT INTO interviews (
    employee_id,
    project_id,
    interview_datetime,
    duration_minutes,
    interview_type,
    location_url,
    sales_user_id,
    interview_status,
    interview_result,
    sales_comment,
    next_action,
    created_at,
    created_by,
    updated_at,
    updated_by
) VALUES
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000007'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000001'),
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 2 HOUR),
        60, '01', '東京都千代田区丸の内1-1',
        (SELECT user_id FROM users WHERE login_id = 'sales01'),
        '01', NULL, '対面面談予定', '面談実施',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000008'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000002'),
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 5 HOUR),
        45, '02', 'https://example.local/interviews/002',
        (SELECT user_id FROM users WHERE login_id = 'sales02'),
        '01', NULL, 'オンライン面談予定', '面談実施',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000009'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000003'),
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY),
        30, '03', '03-0000-0000',
        (SELECT user_id FROM users WHERE login_id = 'sales01'),
        '01', NULL, '電話面談予定', '面談実施',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000010'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000004'),
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 2 DAY),
        60, '02', 'https://example.local/interviews/004',
        (SELECT user_id FROM users WHERE login_id = 'sales02'),
        '01', NULL, 'オンライン面談予定', '面談実施',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000003'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000005'),
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 3 DAY),
        60, '01', '神奈川県横浜市西区1-1',
        (SELECT user_id FROM users WHERE login_id = 'sales01'),
        '01', NULL, '対面面談予定', '面談実施',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000004'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000006'),
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 4 DAY),
        45, '02', 'https://example.local/interviews/006',
        (SELECT user_id FROM users WHERE login_id = 'sales02'),
        '01', NULL, '上限確認用予定', '面談実施',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000005'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000007'),
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 5 DAY),
        30, '03', '03-0000-0001',
        (SELECT user_id FROM users WHERE login_id = 'sales01'),
        '01', NULL, '上限確認用予定', '面談実施',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000006'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000002'),
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 6 DAY),
        60, '01', '東京都港区港南1-1',
        (SELECT user_id FROM users WHERE login_id = 'sales02'),
        '01', NULL, '上限確認用予定', '面談実施',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000001'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000001'),
        DATE_SUB(CURRENT_TIMESTAMP, INTERVAL 1 DAY),
        60, '02', 'https://example.local/interviews/past',
        (SELECT user_id FROM users WHERE login_id = 'sales01'),
        '02', '02', '過去・実施済みの除外確認', '対応済み',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    ),
    (
        (SELECT employee_id FROM employees WHERE employee_no = 'E000000002'),
        (SELECT project_id FROM projects WHERE project_no = 'P000000002'),
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY),
        60, '01', '東京都港区港南1-1',
        (SELECT user_id FROM users WHERE login_id = 'sales02'),
        '04', NULL, 'キャンセル済みの除外確認', '対応なし',
        CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 1
    );
