CREATE TABLE goodsinfo(
code CHAR(5) NOT NULL,	--자릿수에 의미가 있다면 char()형식을 사용한다.
name VARCHAR2(30) NOT NULL,
price NUMBER NOT NULL,
maker VARCHAR2(20),
PRIMARY KEY(CODE)
);

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10001', '디지털 TV', 350000, 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10002' , 'DVD 플레이어' , 250000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10003' , '디지털 카메라' , 210000 , '삼성');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10004' , '전자사전' , 180000 , '아이리버');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10005' , '벽걸이 에어컨' , 400000 , '삼성');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10006' , '냉장고' , 1400000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10007' , '컴퓨터' , 800000 , 'APPLE');

COMMIT
INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10008' , '세탁기' , 450000 , '삼성');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10009' , '스마트 TV1' , 2000000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10010' , '스마트 TV2' , 2000000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10011' , '스마트 TV3' , 2000000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10012' , '스마트 TV4' , 2000000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10013' , '스마트 TV5' , 2000000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10014' , '스마트 TV6' , 2000000 , 'LG');

SELECT * FROM goodsinfo;

--pagination을 위한 SQL
select *
from (
	select row_number() over(order by code) as seq, code, name, price, maker
	from goodsinfo
)
where seq between 6 and 10; 

