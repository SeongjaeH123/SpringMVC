--table 생성
create table fullstackMember (
	email varchar(100) primary key,
	pw varchar(100) not null,
	tel varchar(100) not null,
	address varchar(100)
);