create sequence seq_flower;
create table FLOWERS(
idFlower LONG PRIMARY KEY,
nameFlower varchar2,
price decimal(15,9),
qtyStock number(10,0)
);

insert into "FLOWERS"(idFlower, nameFlower, price, qtyStock) values (seq_flower.nextval, 'Ruby rose', 125, 15);
insert into "FLOWERS"(idFlower, nameFlower, price, qtyStock) values (seq_flower.nextval, 'Chamomile', 85, 25);
