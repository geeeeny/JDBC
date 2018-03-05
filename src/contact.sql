CREATE TABLE goodsinfo(
code CHAR(5) NOT NULL,	--�ڸ����� �ǹ̰� �ִٸ� char()������ ����Ѵ�.
name VARCHAR2(30) NOT NULL,
price NUMBER NOT NULL,
maker VARCHAR2(20),
PRIMARY KEY(CODE)
);

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10001', '������ TV', 350000, 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10002' , 'DVD �÷��̾�' , 250000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10003' , '������ ī�޶�' , 210000 , '�Ｚ');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10004' , '���ڻ���' , 180000 , '���̸���');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10005' , '������ ������' , 400000 , '�Ｚ');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10006' , '�����' , 1400000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10007' , '��ǻ��' , 800000 , 'APPLE');

COMMIT
INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10008' , '��Ź��' , 450000 , '�Ｚ');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10009' , '����Ʈ TV1' , 2000000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10010' , '����Ʈ TV2' , 2000000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10011' , '����Ʈ TV3' , 2000000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10012' , '����Ʈ TV4' , 2000000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10013' , '����Ʈ TV5' , 2000000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10014' , '����Ʈ TV6' , 2000000 , 'LG');

SELECT * FROM goodsinfo;

--pagination�� ���� SQL
select *
from (
	select row_number() over(order by code) as seq, code, name, price, maker
	from goodsinfo
)
where seq between 6 and 10; 

