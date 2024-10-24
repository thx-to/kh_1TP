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

