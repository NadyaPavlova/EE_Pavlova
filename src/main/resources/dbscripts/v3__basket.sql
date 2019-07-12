create sequence seq_basket;
create table BASKET(
id_basket number(10, 0) PRIMARY KEY,
id_flower number(10, 0),
name_flower varchar2,
price decimal(15,9),
qty number(10,0),
foreign key (id_flower) references flowers (id_flower)
);