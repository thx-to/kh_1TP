-- 테이블 생성용 쿼리들 (2-4-3-1-5 순서로 추가)
CREATE TABLE ACC_INFO(
	user_id varchar2(20) PRIMARY KEY,
	user_pw varchar2(20) CHECK(LENGTH(user_pw) > 7),
	user_name varchar2(15) NOT NULL,
	user_phone char(13) UNIQUE,
	join_date DATE DEFAULT SYSDATE,
	auth_lv NUMBER(1) NOT NULL,
	store_id varchar2(20) REFERENCES STORE (store_id)
);

CREATE TABLE INV_ORDER(
	menu_name varchar2(20) PRIMARY KEY,
	price number(5) NOT NULL,
	category varchar2(10) CHECK (category IN ('버거', '사이드', '음료')),
	descr varchar2(30)
);

CREATE TABLE INV(
	menu_name varchar2(20) REFERENCES INV_ORDER(menu_name),
	store_id varchar2(20) REFERENCES STORE(store_id),
	PRIMARY KEY (menu_name, store_id),
	price number(5) NOT NULL,
	stock number(4) NOT NULL,
	descr varchar2(30)
);

CREATE TABLE STORE(
	store_id varchar2(20) PRIMARY KEY,
	sales number(10) NOT NULL,
	capital number(10) NOT NULL
);

CREATE TABLE ORDER_RECORD(
	order_code varchar(20) PRIMARY KEY,
	order_list varchar(20) NOT NULL,
	order_time DATE NOT NULL,
	order_price number(10) NOT NULL,
	user_id varchar(20) REFERENCES ACC_INFO(user_id),
	store_id varchar(20) REFERENCES STORE(store_ID)
);

-- INV_ORDER 테이블 테스트 값
-- 버거, 사이드, 음료 카테고리 각 3개 제품 추가
INSERT INTO INV_ORDER VALUES ('빅맥', 6300, '버거', '맥도날드의 간판 메뉴');
INSERT INTO INV_ORDER VALUES ('슈비버거', 6600, '버거', '슈림프와 비프');
INSERT INTO INV_ORDER VALUES ('치즈버거', 3600, '버거', '패티에 슬라이스 치즈');
INSERT INTO INV_ORDER VALUES ('맥너겟', 3400, '사이드', '맥도날드의 치킨너겟');
INSERT INTO INV_ORDER VALUES ('후렌치후라이', 2300, '사이드', '바싹 튀긴 감자튀김');
INSERT INTO INV_ORDER VALUES ('치즈스틱', 3600, '사이드', '치즈가 쭈욱 늘어나요');
INSERT INTO INV_ORDER VALUES ('코카콜라', 2600, '음료', '콜라는 역시 COKE');
INSERT INTO INV_ORDER VALUES ('아메리카노', 3300, '음료', '얼죽아 아시죠?');
INSERT INTO INV_ORDER VALUES ('바닐라쉐이크', 3500, '음료', '감튀 디핑소스');

-- STORE 데이터 생성 쿼리 (점주용 지점처리)
INSERT INTO STORE (store_id, sales, capital)
	VALUES ('구로점', 0, 3000000);
INSERT INTO STORE (store_id, sales, capital)
	VALUES ('화곡점', 0, 3000000);
INSERT INTO STORE (store_id, sales, capital)
	VALUES ('시청점', 0, 3000000);
INSERT INTO STORE
	VALUES ('역삼점', 100000, 3000000);

-- ACC_INFO 데이터 생성 쿼리 (ADMIN)
INSERT INTO ACC_INFO (USER_ID, USER_PW, USER_NAME, USER_PHONE, AUTH_LV, STORE_ID)
	VALUES('RED', 'red12345', 'redMonkey', '010-1234-5678', 1, '구로점');
INSERT INTO ACC_INFO (USER_ID, USER_PW, USER_NAME, USER_PHONE, AUTH_LV, STORE_ID)
	VALUES('BLUE', 'blue12345', 'blueMonkey', '010-4567-8912', 1, '화곡점');
INSERT INTO ACC_INFO (USER_ID, USER_PW, USER_NAME, USER_PHONE, AUTH_LV, STORE_ID)
	VALUES('YELLOW', 'yellow12345', 'yellowMonkey', '010-7891-2345', 1, '시청점');

-- STORE 데이터 생성 쿼리 (본사용 지점처리)
INSERT INTO STORE (store_id, sales, capital)
	VALUES ('본사', 0, 1000000000);

-- ACC_INFO 데이터 생성 쿼리 (HQ)
INSERT INTO ACC_INFO (USER_ID, USER_PW, USER_NAME, USER_PHONE, AUTH_LV, STORE_ID)
	VALUES('HQ', 'headquarter12345', '본사', '010-9999-9999', 2, '본사');
	
INSERT INTO ACC_INFO (USER_ID, USER_PW, USER_NAME, USER_PHONE, AUTH_LV, STORE_ID)
	VALUES('PINK', 'pink12345', 'pinkMonkey', '010-8888-8888', 1, '역삼점');



UPDATE INV SET STOCK = 30 WHERE STORE_ID ='역삼점';
UPDATE INV SET STOCK = 30 WHERE STORE_ID ='화곡점';

-- INV 테이블 테스트 값

INSERT INTO INV VALUES ('빅맥', '역삼점', 6300, 10, '맥도날드의 간판 메뉴');
INSERT INTO INV VALUES ('슈비버거', '역삼점', 6600, 10, '슈림프와 비프');
INSERT INTO INV VALUES ('치즈버거', '역삼점', 3600, 10, '패티에 슬라이스 치즈');
INSERT INTO INV VALUES ('맥너겟', '역삼점', 3400, 10, '맥도날드의 치킨너겟');
INSERT INTO INV VALUES ('후렌치후라이', '역삼점', 2300, 10, '바싹 튀긴 감자튀김');
INSERT INTO INV VALUES ('치즈스틱', '역삼점', 3600, 10, '치즈가 쭈욱 늘어나요');
INSERT INTO INV VALUES ('코카콜라', '역삼점', 2600, 10, '콜라는 역시 COKE');
INSERT INTO INV VALUES ('아메리카노', '역삼점', 3300, 10, '얼죽아 아시죠?');
INSERT INTO INV VALUES ('바닐라쉐이크', '역삼점', 3500, 10, '감튀 디핑소스');

SELECT i.MENU_NAME, i.PRICE, o.DESCR, o.CATEGORY 
FROM INV i JOIN INV_ORDER o ON i.MENU_NAME = o.MENU_NAME WHERE STORE_ID = '역삼점';

-- ORDER_RECORD 테이블의 제약조건중 참조(REFERENCE; R)을 쓰는 제약 조건 검색
SELECT constraint_name
FROM user_constraints
WHERE table_name = 'ORDER_RECORD'
AND constraint_type = 'R';

-- 제약 이름이 시스템에서 생성되서 뭐가 뭔지 모를 때 쓰는 쿼리
SELECT 
    uc.constraint_name, 
    uc.constraint_type, 
    ucc.table_name, 
    ucc.column_name, 
    uc.r_constraint_name AS referenced_constraint, 
    ur.table_name AS referenced_table
FROM 
    user_constraints uc
JOIN 
    user_cons_columns ucc ON uc.constraint_name = ucc.constraint_name
LEFT JOIN 
    user_constraints ur ON uc.r_constraint_name = ur.constraint_name
WHERE 
    uc.constraint_name = 'SYS_C007136';
-- 저의 경우는 위에서 검색시
-- 해당 sys-gen처리된 제약조건의 이름이 위와 같아서 이것으로 검색
  
  --해당 제약 조건으로 이게 ORDER_RECORD가 ACC_INFO 테이블의 USER_ID를
  --받아서 쓴다는 것을 확인.
  
  -- 그 해당 제약을 DROP 처리
  ALTER TABLE order_record
	DROP CONSTRAINT SYS_C007136;
	
	--그 이후, CASCADE DELETE 제약 조건을 다시 걸어준다.
	-- 이게 적용되면 ACC_INFO에서 짤라버리면 ORDER_RECORD에서
	--그 user_id를 쓰던 열을 지워준다.
ALTER TABLE order_record
ADD CONSTRAINT fk_user_id
FOREIGN KEY (user_id)
REFERENCES acc_info(user_id)
ON DELETE CASCADE;
