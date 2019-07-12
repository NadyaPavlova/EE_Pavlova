create sequence seq_flower;
create table FLOWERS(
id_flower number(10, 0) PRIMARY KEY,
name_flower varchar2,
price decimal(15,9),
qty_stock number(10,0)
);

insert into "FLOWERS"(id_flower, name_flower, price, qty_stock) values (seq_flower.nextval, 'Ruby rose', 125, 15);
insert into "FLOWERS"(id_flower, name_flower, price, qty_stock) values (seq_flower.nextval, 'Chamomile', 85, 25);
