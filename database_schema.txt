create database pharmacy;
use pharmacy;


create table user_accounts(
	username varchar(20),
	password varchar(10) not null,
	primary key( username )
);


insert into user_accounts values( 'admin', 'admin' );	


create table drugs(
	item_code char(5) not null,
	item_name varchar( 50 ),
	batch_number char(10),
	exp_date date,
	purchase_date date,
	unit_price float,
	quantity int,
	manufacturer varchar( 30 ),	
	
	primary key( item_code )
); 




insert into drugs values( '12345', 'meth', '1234567890', '1-1-1111', '2-2-2222', 3.34, 10, 'abc' );
insert into drugs values( '12340', 'meth', '1234567890', '7777-7-7', '7777-7-7', 3.34, 10, 'abc' );














insert into drugs values ( '88888', 'hero', '1234567890', '8888-8-8', '9999-9-9', 3, 3, 'a' );



update drugs set item_code='88888',
item_name='heroin',
batch_number='1234567890',
exp_date='8888-8-8',
purchase_date='9999-9-9',
unit_price=3,
quantity=3,
manufacturer='a'
where item_code='88888';







   