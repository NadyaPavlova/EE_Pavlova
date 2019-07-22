create sequence seq_order_item;
create table ORDER_ITEM(
idOrderIt LONG PRIMARY KEY,
idOrder LONG,
idFlower LONG ,
nameFlower varchar2,
qty number(10,0),
price decimal(15,2),
foreign key (idOrder) references orders (idOrder),
foreign key (idFlower) references flowers (idFlower),
);