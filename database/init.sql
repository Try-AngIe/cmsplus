use cmsplusmain;

-- Vendor 테이블에 10개의 레코드 삽입
INSERT INTO vendor (vendor_id, vendor_password, vendor_phone, vendor_username, vendor_dept, vendor_name, vendor_email, created_datetime, updated_datetime)
VALUES
    (1, 'password1', '01012345678', 'vendor1', '영업부', '김영희', 'vendor1@example.com', NOW(), NOW()),
    (2, 'password2', '01023456789', 'vendor2', '마케팅부', '이철수', 'vendor2@example.com', NOW(), NOW()),
    (3, 'password3', '01034567890', 'vendor3', '인사부', '박지영', 'vendor3@example.com', NOW(), NOW()),
    (4, 'password4', '01045678901', 'vendor4', '개발부', '정민수', 'vendor4@example.com', NOW(), NOW()),
    (5, 'password5', '01056789012', 'vendor5', '고객지원부', '송미라', 'vendor5@example.com', NOW(), NOW()),
    (6, 'password6', '01067890123', 'vendor6', '재무부', '강동훈', 'vendor6@example.com', NOW(), NOW()),
    (7, 'password7', '01078901234', 'vendor7', '기획부', '윤서연', 'vendor7@example.com', NOW(), NOW()),
    (8, 'password8', '01089012345', 'vendor8', '연구개발부', '임재현', 'vendor8@example.com', NOW(), NOW()),
    (9, 'password9', '01090123456', 'vendor9', '디자인부', '한소희', 'vendor9@example.com', NOW(), NOW()),
    (10, 'password10', '01001234567', 'vendor10', '총무부', '최준호', 'vendor10@example.com', NOW(), NOW());

-- Member 테이블에 20개의 레코드 삽입
INSERT INTO member (member_id, member_auto_billing, member_auto_invoice_send, member_enroll_date, member_vendor, member_phone, member_name, member_email, member_invoice_send_method, member_status, created_datetime, updated_datetime)
VALUES
    (1, 1, 1, '2023-01-01', 1, '01111111111', '학생1', 'student1@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (2, 0, 1, '2023-01-02', 1, '01111111112', '학생2', 'student2@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (3, 1, 0, '2023-01-03', 2, '01111111113', '학생3', 'student3@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (4, 0, 0, '2023-01-04', 2, '01111111114', '학생4', 'student4@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (5, 1, 1, '2023-01-05', 3, '01111111115', '학생5', 'student5@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (6, 0, 1, '2023-01-06', 3, '01111111116', '학생6', 'student6@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (7, 1, 0, '2023-01-07', 4, '01111111117', '학생7', 'student7@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (8, 0, 0, '2023-01-08', 4, '01111111118', '학생8', 'student8@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (9, 1, 1, '2023-01-09', 5, '01111111119', '학생9', 'student9@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (10, 0, 1, '2023-01-10', 5, '01111111120', '학생10', 'student10@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (11, 1, 0, '2023-01-11', 6, '01111111121', '학생11', 'student11@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (12, 0, 0, '2023-01-12', 6, '01111111122', '학생12', 'student12@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (13, 1, 1, '2023-01-13', 7, '01111111123', '학생13', 'student13@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (14, 0, 1, '2023-01-14', 7, '01111111124', '학생14', 'student14@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (15, 1, 0, '2023-01-15', 8, '01111111125', '학생15', 'student15@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (16, 0, 0, '2023-01-16', 8, '01111111126', '학생16', 'student16@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (17, 1, 1, '2023-01-17', 9, '01111111127', '학생17', 'student17@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (18, 0, 1, '2023-01-18', 9, '01111111128', '학생18', 'student18@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (19, 1, 0, '2023-01-19', 10, '01111111129', '학생19', 'student19@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (20, 0, 0, '2023-01-20', 10, '01111111130', '학생20', 'student20@example.com', 'SMS', 'ENABLED', NOW(), NOW());

-- Product 테이블에 10개의 레코드 삽입
INSERT INTO product (product_id, product_price, product_vendor_id, product_name, product_status, created_datetime, updated_datetime)
VALUES
    (1, 100000, 1, '수학 기초', 'ENABLED', NOW(), NOW()),
    (2, 150000, 1, '영어 회화', 'ENABLED', NOW(), NOW()),
    (3, 120000, 2, '과학 실험', 'ENABLED', NOW(), NOW()),
    (4, 200000, 2, '코딩 기초', 'ENABLED', NOW(), NOW()),
    (5, 180000, 3, '미술 창작', 'ENABLED', NOW(), NOW()),
    (6, 130000, 3, '음악 이론', 'ENABLED', NOW(), NOW()),
    (7, 90000, 4, '체육 기초', 'ENABLED', NOW(), NOW()),
    (8, 160000, 4, '역사 탐구', 'ENABLED', NOW(), NOW()),
    (9, 140000, 5, '문학 감상', 'ENABLED', NOW(), NOW()),
    (10, 110000, 5, '외국어 기초', 'ENABLED', NOW(), NOW());

-- Payment 테이블에 10개의 레코드 삽입
INSERT INTO payment (payment_id, payment_type, payment_status, created_datetime, updated_datetime)
VALUES
    (1, 'AUTO_PAYMENT', 'ENABLED', NOW(), NOW()),
    (2, 'BUYER_PAYMENT', 'ENABLED', NOW(), NOW()),
    (3, 'AUTO_PAYMENT', 'ENABLED', NOW(), NOW()),
    (4, 'BUYER_PAYMENT', 'ENABLED', NOW(), NOW()),
    (5, 'AUTO_PAYMENT', 'ENABLED', NOW(), NOW()),
    (6, 'BUYER_PAYMENT', 'ENABLED', NOW(), NOW()),
    (7, 'AUTO_PAYMENT', 'ENABLED', NOW(), NOW()),
    (8, 'BUYER_PAYMENT', 'ENABLED', NOW(), NOW()),
    (9, 'AUTO_PAYMENT', 'ENABLED', NOW(), NOW()),
    (10, 'BUYER_PAYMENT', 'ENABLED', NOW(), NOW());

-- 이 부분에서 나머지 테이블에 대한 INSERT 문을 추가할 수 있습니다.