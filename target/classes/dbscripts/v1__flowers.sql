create sequence seq_flower;
create table FLOWERS(
idFlower LONG PRIMARY KEY,
nameFlower varchar2(20) NOT NULL,
price decimal(15,2) NOT NULL,
qtyStock number(10,0)
);

insert into "FLOWERS"(idFlower, nameFlower, price, qtyStock) values (seq_flower.nextval, 'Ruby rose', 125, 15);
insert into "FLOWERS"(idFlower, nameFlower, price, qtyStock) values (seq_flower.nextval, 'Chamomile', 85, 25);
insert into "FLOWERS"(idFlower, nameFlower, price, qtyStock) values (seq_flower.nextval, 'Peonies', 155, 35);
insert into "FLOWERS"(idFlower, nameFlower, price, qtyStock) values (seq_flower.nextval, 'Lilys', 205, 15);
insert into "FLOWERS"(idFlower, nameFlower, price, qtyStock) values (seq_flower.nextval, 'Astras', 115, 45);
insert into "FLOWERS"(idFlower, nameFlower, price, qtyStock) values (seq_flower.nextval, 'Chrysanthemums', 90, 45);