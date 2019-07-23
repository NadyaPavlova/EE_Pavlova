create sequence seq_order_item;
create table ORDERITEM(
idItemOrder LONG primary key,
idOrder LONG,
idFlower LONG ,
qty number(10,0),
price decimal(15,2),
foreign key (idOrder) references orders (idOrder),
foreign key (idFlower) references flowers (idFlower)
);