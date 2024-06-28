use cmsplusmain;

-- auto_payment 테이블 생성
CREATE TABLE auto_payment (
                              auto_payment_simpconsent_requset_date DATE COMMENT '자동결제 정보 간편동의 마지막 요청일',
                              payment_id BIGINT NOT NULL,
                              auto_payment_consent_img_url VARCHAR(2000) COMMENT '자동결제 정보 고객이 등록한 동의서 이미지 URL',
                              auto_payment_sign_img_url VARCHAR(2000) COMMENT '자동결제 정보 회원 간편동의 서명 이미지 URL',
                              PRIMARY KEY (payment_id)
) ENGINE=InnoDB;

-- billing 테이블 생성
CREATE TABLE billing (
                         billing_id BIGINT NOT NULL,
                         billing_standard_id BIGINT COMMENT '청구 설정 정보',
                         created_datetime DATETIME(6),
                         updated_datetime DATETIME(6),
                         billing_memo VARCHAR(2000) COMMENT '청구서 메시지',
                         billing_status ENUM ('CREATED','NON_PAID','PAID','REMOVED','WAITING_PAYMENT') NOT NULL COMMENT '청구 상태',
                         PRIMARY KEY (billing_id)
) ENGINE=InnoDB;

-- billing_product 테이블 생성
CREATE TABLE billing_product (
                                 billing_product_discount_price INTEGER,
                                 billing_product_extra_price INTEGER,
                                 billing_product_quantity INTEGER,
                                 billing_product_billing BIGINT,
                                 billing_product_id BIGINT NOT NULL,
                                 billing_product_product BIGINT,
                                 PRIMARY KEY (billing_product_id)
) ENGINE=InnoDB;

-- billing_standard 테이블 생성
CREATE TABLE billing_standard (
                                 billing_standard_contract_date DATE NOT NULL COMMENT '청구의 약정일',
                                 billing_standard_end_date DATE NOT NULL COMMENT '청구 기간 - 종료일',
                                 billing_standard_month TINYINT NOT NULL COMMENT '청구월',
                                 billing_standard_start_date DATE NOT NULL COMMENT '청구 기간 - 시작일',
                                 billing_standard_contract BIGINT NOT NULL COMMENT '청구 생성 기반이된 계약',
                                 billing_standard_id BIGINT NOT NULL,
                                 billing_standard_member BIGINT NOT NULL COMMENT '청구한 회원',
                                 created_datetime DATETIME(6),
                                 updated_datetime DATETIME(6),
                                 billing_standard_status ENUM ('DISABLED','ENABLED','REMOVED') NOT NULL COMMENT '청구 설정 상태',
                                 billing_standard_type ENUM ('IRREGULAR','REGULAR') NOT NULL COMMENT '청구 타입 [정기 | 추가]',
                                 PRIMARY KEY (billing_standard_id)
) ENGINE=InnoDB;

-- buyer_payment 테이블 생성
CREATE TABLE buyer_payment (
                               buyer_payment_account_enabled BIT NOT NULL COMMENT '납부자 결제 계좌 가능여부',
                               buyer_payment_card_enabled BIT NOT NULL COMMENT '납부자 결제 CARD 가능여부',
                               payment_id BIGINT NOT NULL,
                               PRIMARY KEY (payment_id)
) ENGINE=InnoDB;

-- card_auto_payment 테이블 생성
CREATE TABLE card_auto_payment (
                                   card_auto_payment_card_expire_date DATE,
                                   card_auto_payment_card_owner_birth_date DATE,
                                   payment_id BIGINT NOT NULL,
                                   card_auto_payment_card_number VARCHAR(16),
                                   card_auto_payment_card_owner VARCHAR(40) NOT NULL,
                                   PRIMARY KEY (payment_id)
) ENGINE=InnoDB;

-- cms_auto_payment 테이블 생성
CREATE TABLE cms_auto_payment (
                                  cms_auto_payment_bank_name TINYINT NOT NULL COMMENT 'CMS 계좌 은행명',
                                  cms_auto_payment_owner_birthdate DATE,
                                  payment_id BIGINT NOT NULL,
                                  cms_auto_payment_account_owner VARCHAR(40) NOT NULL,
                                  PRIMARY KEY (payment_id),
                                  CHECK (cms_auto_payment_bank_name BETWEEN 0 AND 1)
) ENGINE=InnoDB;

-- contract 테이블 생성
CREATE TABLE contract (
                          contract_date DATE NOT NULL COMMENT '계약 약정일',
                          contract_id BIGINT NOT NULL,
                          contract_member BIGINT NOT NULL COMMENT '계약한 회원',
                          contract_payment BIGINT NOT NULL COMMENT '계약 결제정보',
                          contract_vendor BIGINT NOT NULL COMMENT '계약한 회원의 고객',
                          created_datetime DATETIME(6),
                          updated_datetime DATETIME(6),
                          contract_name VARCHAR(40) NOT NULL COMMENT '계약 이름',
                          contract_status ENUM ('ACTIVATE','DEACTIVATE','REMOVED') NOT NULL COMMENT '계약 상태',
                          PRIMARY KEY (contract_id)
) ENGINE=InnoDB;

-- contract_product 테이블 생성
CREATE TABLE contract_product (
                                  contract_product_price INTEGER NOT NULL COMMENT '계약_상품 가격',
                                  contract_product_quantity INTEGER NOT NULL COMMENT '계약_상품 수량',
                                  contract_id BIGINT NOT NULL,
                                  contract_product_id BIGINT NOT NULL,
                                  product_id BIGINT NOT NULL,
                                  PRIMARY KEY (contract_product_id)
) ENGINE=InnoDB;

-- member 테이블 생성
CREATE TABLE member (
                        member_auto_billing BIT NOT NULL COMMENT '회원 청구 자동 생성 여부',
                        member_auto_invoice_send BIT NOT NULL COMMENT '회원 청구서 자동 발송 여부',
                        member_enroll_date DATE NOT NULL COMMENT '회원 등록일',
                        created_datetime DATETIME(6),
                        member_id BIGINT NOT NULL,
                        member_vendor BIGINT NOT NULL COMMENT '회원의 고객',
                        updated_datetime DATETIME(6),
                        member_phone VARCHAR(20) NOT NULL COMMENT '회원 휴대전화',
                        member_address VARCHAR(40) COMMENT '회원 주소',
                        member_name VARCHAR(40) NOT NULL COMMENT '회원 이름',
                        member_email VARCHAR(100) NOT NULL COMMENT '회원 이메일',
                        member_memo VARCHAR(2000) COMMENT '회원 메모',
                        member_invoice_send_method ENUM ('EMAIL','SMS') NOT NULL COMMENT '회원 청구서 발송 수단',
                        member_status ENUM ('DISABLED','ENABLED','REMOVED') NOT NULL COMMENT '회원 상태',
                        PRIMARY KEY (member_id)
) ENGINE=InnoDB;

-- payment 테이블 생성
CREATE TABLE payment (
                         created_datetime DATETIME(6),
                         payment_id BIGINT NOT NULL,
                         updated_datetime DATETIME(6),
                         payment_type VARCHAR(31) NOT NULL,
                         payment_status ENUM ('DISABLED','ENABLED','REMOVED') NOT NULL COMMENT '결제정보 상태',
                         PRIMARY KEY (payment_id)
) ENGINE=InnoDB;

-- product 테이블 생성
CREATE TABLE product (
                         product_price INTEGER NOT NULL COMMENT '상품 금액',
                         created_datetime DATETIME(6),
                         product_id BIGINT NOT NULL,
                         product_vendor_id BIGINT NOT NULL COMMENT '상품 고객',
                         updated_datetime DATETIME(6),
                         product_name VARCHAR(20) NOT NULL COMMENT '상품 이름',
                         product_memo VARCHAR(2000) COMMENT '상품 비고',
                         product_status ENUM ('DISABLED','ENABLED','REMOVED') NOT NULL COMMENT '상품 상태',
                         PRIMARY KEY (product_id)
) ENGINE=InnoDB;

-- simpconsent_vendor_payment 테이블 생성
CREATE TABLE simpconsent_vendor_payment (
                                            payment_id BIGINT NOT NULL,
                                            vendor_id BIGINT NOT NULL,
                                            PRIMARY KEY (payment_id, vendor_id)
) ENGINE=InnoDB;

-- simpconsent_vendor_product 테이블 생성
CREATE TABLE simpconsent_vendor_product (
                                            product_id BIGINT NOT NULL,
                                            vendor_id BIGINT NOT NULL,
                                            PRIMARY KEY (product_id, vendor_id)
) ENGINE=InnoDB;

-- vendor 테이블 생성
CREATE TABLE vendor (
                        created_datetime DATETIME(6),
                        updated_datetime DATETIME(6),
                        vendor_id BIGINT NOT NULL,
                        vendor_password VARCHAR(16) NOT NULL COMMENT '고객 로그인 비밀번호',
                        vendor_homephone VARCHAR(20) COMMENT '고객 유선전화',
                        vendor_phone VARCHAR(20) NOT NULL COMMENT '고객 휴대전화',
                        vendor_username VARCHAR(20) NOT NULL COMMENT '고객 로그인 아이디',
                        vendor_dept VARCHAR(40) NOT NULL COMMENT '고객 부서명',
                        vendor_name VARCHAR(40) NOT NULL COMMENT '고객 이름',
                        vendor_email VARCHAR(100) NOT NULL COMMENT '고객 이메일',
                        PRIMARY KEY (vendor_id)
) ENGINE=InnoDB;

-- virtual_payment 테이블 생성
CREATE TABLE virtual_payment (
                                 virtual_payment_bank_name TINYINT NOT NULL COMMENT '가상계좌 은행명',
                                 payment_id BIGINT NOT NULL,
                                 virtual_payment_account_number VARCHAR(14) NOT NULL COMMENT '가상계좌 계좌번호',
                                 virtual_payment_account_owner VARCHAR(40) NOT NULL COMMENT '가상계좌 소유자명',
                                 PRIMARY KEY (payment_id),
                                 CHECK (virtual_payment_bank_name BETWEEN 0 AND 1)
) ENGINE=InnoDB;

-- 외래 키 제약 조건 추가
ALTER TABLE contract ADD CONSTRAINT UK9ge544mb6sbbqkoxh9c56mm4g UNIQUE (contract_payment);
ALTER TABLE member ADD CONSTRAINT UNIQUE_MEMBER_EMAIL UNIQUE (member_email);
ALTER TABLE member ADD CONSTRAINT UNIQUE_MEMBER_PHONE UNIQUE (member_phone);
ALTER TABLE vendor ADD CONSTRAINT UNIQUE_VENDOR_USERNAME UNIQUE (vendor_username);
ALTER TABLE vendor ADD CONSTRAINT UNIQUE_VENDOR_EMAIL UNIQUE (vendor_email);
ALTER TABLE vendor ADD CONSTRAINT UNIQUE_VENDOR_PHONE UNIQUE (vendor_phone);

ALTER TABLE auto_payment ADD CONSTRAINT FKym1nm1kabudvbh69af1rbruo
    FOREIGN KEY (payment_id) REFERENCES payment (payment_id);

ALTER TABLE billing ADD CONSTRAINT FKbdpuwj43e379p54osn3xn4jdx
    FOREIGN KEY (billing_standard_id) REFERENCES billing_standard (billing_standard_id);

ALTER TABLE billing_product ADD CONSTRAINT FK16n4l2odmvpg82bllt50ima69
    FOREIGN KEY (billing_product_billing) REFERENCES billing (billing_id);

ALTER TABLE billing_product ADD CONSTRAINT FKb408vo273jvol5oo74d8cfqnm
    FOREIGN KEY (billing_product_product) REFERENCES product (product_id);

ALTER TABLE billing_standard ADD CONSTRAINT FK7r7x0qr2glgpwutl2ub57v7js
    FOREIGN KEY (billing_standard_contract) REFERENCES contract (contract_id);

ALTER TABLE billing_standard ADD CONSTRAINT FKhclrj3bew37vmecvgu555ycv
    FOREIGN KEY (billing_standard_member) REFERENCES member (member_id);

ALTER TABLE buyer_payment ADD CONSTRAINT FKcmqsd7vcdqq0nm653dl0w387y
    FOREIGN KEY (payment_id) REFERENCES payment (payment_id);

ALTER TABLE card_auto_payment ADD CONSTRAINT FK1p8lptm06wfpaj9it2bawmops
    FOREIGN KEY (payment_id) REFERENCES auto_payment (payment_id);

ALTER TABLE cms_auto_payment ADD CONSTRAINT FKidaf77lrqfkq6rdc1uyfoy0o1
    FOREIGN KEY (payment_id) REFERENCES auto_payment (payment_id);

ALTER TABLE contract ADD CONSTRAINT FKcr757t9o03guqnqxi4rjrogmx
    FOREIGN KEY (contract_member) REFERENCES member (member_id);

ALTER TABLE contract ADD CONSTRAINT FK9i6ykio7dbx3clnlb937m385o
    FOREIGN KEY (contract_payment) REFERENCES payment (payment_id);

ALTER TABLE contract ADD CONSTRAINT FK4o55bhhevo84ahvfto82pbo4q
    FOREIGN KEY (contract_vendor) REFERENCES vendor (vendor_id);

ALTER TABLE contract_product ADD CONSTRAINT FKcypveko4d778htqy57j8fpvqq
    FOREIGN KEY (contract_id) REFERENCES contract (contract_id);

ALTER TABLE contract_product ADD CONSTRAINT FKmygg675doa79tb14x7lhgwxy8
    FOREIGN KEY (product_id) REFERENCES product (product_id);

ALTER TABLE member ADD CONSTRAINT FK3ukhrw29bi17922kwujwloc55
    FOREIGN KEY (member_vendor) REFERENCES vendor (vendor_id);

ALTER TABLE product ADD CONSTRAINT FKdgtklokc1k1onec71gobclsje
    FOREIGN KEY (product_vendor_id) REFERENCES vendor (vendor_id);

ALTER TABLE simpconsent_vendor_payment ADD CONSTRAINT FKsws2r117j28crdnayox6notpe
    FOREIGN KEY (payment_id) REFERENCES payment (payment_id);

ALTER TABLE simpconsent_vendor_payment ADD CONSTRAINT FKmhe8k0gwq73pe5xtdxolurvvl
    FOREIGN KEY (vendor_id) REFERENCES vendor (vendor_id);

ALTER TABLE simpconsent_vendor_product ADD CONSTRAINT FKdjtgm2s2vb24dhk9b2xw2n9tu
    FOREIGN KEY (product_id) REFERENCES product (product_id);

ALTER TABLE simpconsent_vendor_product ADD CONSTRAINT FK1qy7eqx796bqwdf0pdlb7ixpy
    FOREIGN KEY (vendor_id) REFERENCES vendor (vendor_id);

ALTER TABLE virtual_payment ADD CONSTRAINT FK50sibqlk986l384iwk3ebtn01
    FOREIGN KEY (payment_id) REFERENCES payment (payment_id);

-- Vendor 데이터 삽입 (10개)
INSERT INTO vendor (vendor_id, vendor_password, vendor_phone, vendor_username, vendor_dept, vendor_name, vendor_email, created_datetime, updated_datetime)
VALUES
    (1, 'password1', '010-1111-1111', 'vendor1', '영업부', '홍길동', 'vendor1@example.com', NOW(), NOW()),
    (2, 'password2', '010-2222-2222', 'vendor2', '마케팅부', '김영희', 'vendor2@example.com', NOW(), NOW()),
    (3, 'password3', '010-3333-3333', 'vendor3', '개발부', '이철수', 'vendor3@example.com', NOW(), NOW()),
    (4, 'password4', '010-4444-4444', 'vendor4', '인사부', '박지영', 'vendor4@example.com', NOW(), NOW()),
    (5, 'password5', '010-5555-5555', 'vendor5', '재무부', '최민수', 'vendor5@example.com', NOW(), NOW()),
    (6, 'password6', '010-6666-6666', 'vendor6', '고객지원부', '정수연', 'vendor6@example.com', NOW(), NOW()),
    (7, 'password7', '010-7777-7777', 'vendor7', 'R&D부', '강동원', 'vendor7@example.com', NOW(), NOW()),
    (8, 'password8', '010-8888-8888', 'vendor8', '품질관리부', '송혜교', 'vendor8@example.com', NOW(), NOW()),
    (9, 'password9', '010-9999-9999', 'vendor9', '생산부', '유재석', 'vendor9@example.com', NOW(), NOW()),
    (10, 'password10', '010-1010-1010', 'vendor10', '물류부', '김태희', 'vendor10@example.com', NOW(), NOW());

-- Member 데이터 삽입 (10개)
INSERT INTO member (member_id, member_auto_billing, member_auto_invoice_send, member_enroll_date, member_vendor, member_phone, member_name, member_email, member_invoice_send_method, member_status, created_datetime, updated_datetime)
VALUES
    (1, true, true, '2023-01-01', 1, '010-1234-5671', '김회원1', 'member1@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (2, false, true, '2023-01-02', 2, '010-1234-5672', '이회원2', 'member2@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (3, true, false, '2023-01-03', 3, '010-1234-5673', '박회원3', 'member3@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (4, false, false, '2023-01-04', 4, '010-1234-5674', '최회원4', 'member4@example.com', 'SMS', 'DISABLED', NOW(), NOW()),
    (5, true, true, '2023-01-05', 5, '010-1234-5675', '정회원5', 'member5@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (6, false, true, '2023-01-06', 6, '010-1234-5676', '강회원6', 'member6@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (7, true, false, '2023-01-07', 7, '010-1234-5677', '조회원7', 'member7@example.com', 'EMAIL', 'DISABLED', NOW(), NOW()),
    (8, false, false, '2023-01-08', 8, '010-1234-5678', '윤회원8', 'member8@example.com', 'SMS', 'ENABLED', NOW(), NOW()),
    (9, true, true, '2023-01-09', 9, '010-1234-5679', '장회원9', 'member9@example.com', 'EMAIL', 'ENABLED', NOW(), NOW()),
    (10, false, true, '2023-01-10', 10, '010-1234-5680', '임회원10', 'member10@example.com', 'SMS', 'ENABLED', NOW(), NOW());

-- Product 데이터 삽입 (10개)
INSERT INTO product (product_id, product_price, product_vendor_id, product_name, product_status, created_datetime, updated_datetime)
VALUES
    (1, 10000, 1, '상품A', 'ENABLED', NOW(), NOW()),
    (2, 20000, 2, '상품B', 'ENABLED', NOW(), NOW()),
    (3, 30000, 3, '상품C', 'DISABLED', NOW(), NOW()),
    (4, 15000, 4, '상품D', 'ENABLED', NOW(), NOW()),
    (5, 25000, 5, '상품E', 'ENABLED', NOW(), NOW()),
    (6, 35000, 6, '상품F', 'DISABLED', NOW(), NOW()),
    (7, 40000, 7, '상품G', 'ENABLED', NOW(), NOW()),
    (8, 50000, 8, '상품H', 'ENABLED', NOW(), NOW()),
    (9, 60000, 9, '상품I', 'DISABLED', NOW(), NOW()),
    (10, 70000, 10, '상품J', 'ENABLED', NOW(), NOW());

-- Payment 데이터 삽입 (10개)
INSERT INTO payment (payment_id, payment_type, payment_status, created_datetime, updated_datetime)
VALUES
    (1, 'AUTO_PAYMENT', 'ENABLED', NOW(), NOW()),
    (2, 'BUYER_PAYMENT', 'ENABLED', NOW(), NOW()),
    (3, 'VIRTUAL_PAYMENT', 'DISABLED', NOW(), NOW()),
    (4, 'AUTO_PAYMENT', 'ENABLED', NOW(), NOW()),
    (5, 'BUYER_PAYMENT', 'ENABLED', NOW(), NOW()),
    (6, 'VIRTUAL_PAYMENT', 'DISABLED', NOW(), NOW()),
    (7, 'AUTO_PAYMENT', 'ENABLED', NOW(), NOW()),
    (8, 'BUYER_PAYMENT', 'ENABLED', NOW(), NOW()),
    (9, 'VIRTUAL_PAYMENT', 'DISABLED', NOW(), NOW()),
    (10, 'AUTO_PAYMENT', 'ENABLED', NOW(), NOW());

-- Auto Payment 데이터 삽입 (5개)
INSERT INTO auto_payment (payment_id, auto_payment_simpconsent_requset_date)
VALUES
    (1, '2023-06-01'),
    (4, '2023-06-02'),
    (7, '2023-06-03'),
    (10, '2023-06-04'),
    (2, '2023-06-05');

-- Card Auto Payment 데이터 삽입 (3개)
INSERT INTO card_auto_payment (payment_id, card_auto_payment_card_number, card_auto_payment_card_owner, card_auto_payment_card_expire_date)
VALUES
    (1, '1111222233334444', '김카드', '2025-12-31'),
    (4, '5555666677778888', '이카드', '2024-06-30'),
    (7, '9999000011112222', '박카드', '2026-03-31');

-- CMS Auto Payment 데이터 삽입 (2개)
INSERT INTO cms_auto_payment (payment_id, cms_auto_payment_bank_name, cms_auto_payment_account_owner, cms_auto_payment_owner_birthdate)
VALUES
    (10, 0, '최은행', '1990-01-01'),
    (2, 1, '정은행', '1985-05-05');

-- Contract 데이터 삽입 (10개)
INSERT INTO contract (contract_id, contract_date, contract_member, contract_payment, contract_vendor, contract_name, contract_status, created_datetime, updated_datetime)
VALUES
    (1, '2023-01-15', 1, 1, 1, '계약A', 'ACTIVATE', NOW(), NOW()),
    (2, '2023-02-15', 2, 2, 2, '계약B', 'ACTIVATE', NOW(), NOW()),
    (3, '2023-03-15', 3, 3, 3, '계약C', 'DEACTIVATE', NOW(), NOW()),
    (4, '2023-04-15', 4, 4, 4, '계약D', 'ACTIVATE', NOW(), NOW()),
    (5, '2023-05-15', 5, 5, 5, '계약E', 'ACTIVATE', NOW(), NOW()),
    (6, '2023-06-15', 6, 6, 6, '계약F', 'DEACTIVATE', NOW(), NOW()),
    (7, '2023-07-15', 7, 7, 7, '계약G', 'ACTIVATE', NOW(), NOW()),
    (8, '2023-08-15', 8, 8, 8, '계약H', 'ACTIVATE', NOW(), NOW()),
    (9, '2023-09-15', 9, 9, 9, '계약I', 'DEACTIVATE', NOW(), NOW()),
    (10, '2023-10-15', 10, 10, 10, '계약J', 'ACTIVATE', NOW(), NOW());

-- Contract Product 데이터 삽입 (10개)
INSERT INTO contract_product (contract_product_id, contract_id, product_id, contract_product_price, contract_product_quantity)
VALUES
    (1, 1, 1, 10000, 2),
    (2, 2, 2, 20000, 1),
    (3, 3, 3, 30000, 3),
    (4, 4, 4, 15000, 2),
    (5, 5, 5, 25000, 1),
    (6, 6, 6, 35000, 2),
    (7, 7, 7, 40000, 1),
    (8, 8, 8, 50000, 3),
    (9, 9, 9, 60000, 2),
    (10, 10, 10, 70000, 1);

-- Billing standard 데이터 삽입 (10개)
INSERT INTO billing_standard (billing_standard_id, billing_standard_contract_date, billing_standard_end_date, billing_standard_month, billing_standard_start_date, billing_standard_contract, billing_standard_member, billing_standard_status, billing_standard_type, created_datetime, updated_datetime)
VALUES
    (1, '2023-01-15', '2023-12-31', 1, '2023-01-01', 1, 1, 'ENABLED', 'REGULAR', NOW(), NOW()),
    (2, '2023-02-15', '2023-12-31', 2, '2023-02-01', 2, 2, 'ENABLED', 'IRREGULAR', NOW(), NOW()),
    (3, '2023-03-15', '2023-12-31', 3, '2023-03-01', 3, 3, 'DISABLED', 'REGULAR', NOW(), NOW()),
    (4, '2023-04-15', '2023-12-31', 4, '2023-04-01', 4, 4, 'ENABLED', 'REGULAR', NOW(), NOW()),
    (5, '2023-05-15', '2023-12-31', 5, '2023-05-01', 5, 5, 'ENABLED', 'IRREGULAR', NOW(), NOW()),
    (6, '2023-06-15', '2023-12-31', 6, '2023-06-01', 6, 6, 'DISABLED', 'REGULAR', NOW(), NOW()),
    (7, '2023-07-15', '2023-12-31', 7, '2023-07-01', 7, 7, 'ENABLED', 'REGULAR', NOW(), NOW()),
    (8, '2023-08-15', '2023-12-31', 8, '2023-08-01', 8, 8, 'ENABLED', 'IRREGULAR', NOW(), NOW()),
    (9, '2023-09-15', '2023-12-31', 9, '2023-09-01', 9, 9, 'DISABLED', 'REGULAR', NOW(), NOW()),
    (10, '2023-10-15', '2023-12-31', 10, '2023-10-01', 10, 10, 'ENABLED', 'REGULAR', NOW(), NOW());

-- Billing 데이터 삽입 (10개)
INSERT INTO billing (billing_id, billing_standard_id, billing_memo, billing_status, created_datetime, updated_datetime)
VALUES
    (1, 1, '1월 정기 청구', 'CREATED', NOW(), NOW()),
    (2, 2, '2월 추가 청구', 'NON_PAID', NOW(), NOW()),
    (3, 3, '3월 정기 청구', 'PAID', NOW(), NOW()),
    (4, 4, '4월 정기 청구', 'CREATED', NOW(), NOW()),
    (5, 5, '5월 추가 청구', 'WAITING_PAYMENT', NOW(), NOW()),
    (6, 6, '6월 정기 청구', 'REMOVED', NOW(), NOW()),
    (7, 7, '7월 정기 청구', 'CREATED', NOW(), NOW()),
    (8, 8, '8월 추가 청구', 'NON_PAID', NOW(), NOW()),
    (9, 9, '9월 정기 청구', 'PAID', NOW(), NOW()),
    (10, 10, '10월 정기 청구', 'CREATED', NOW(), NOW());

-- Billing Product 데이터 삽입 (10개)
INSERT INTO billing_product (billing_product_id, billing_product_billing, billing_product_product, billing_product_quantity, billing_product_discount_price, billing_product_extra_price)
VALUES
    (1, 1, 1, 2, 1000, 500),
    (2, 2, 2, 1, 2000, 0),
    (3, 3, 3, 3, 3000, 1000),
    (4, 4, 4, 2, 1500, 0),
    (5, 5, 5, 1, 2500, 500),
    (6, 6, 6, 2, 3500, 0),
    (7, 7, 7, 1, 4000, 1000),
    (8, 8, 8, 3, 5000, 0),
    (9, 9, 9, 2, 6000, 500),
    (10, 10, 10, 1, 7000, 0);