create sequence seq_order;
create table ORDERS(
id_order number(10, 0) PRIMARY KEY,
id_user number(10, 0),
id_basket number(10, 0),
sum_price decimal(15,9),
status bit,
foreign key (id_user) references users(id_user),
foreign key (id_basket) references basket(id_basket)
);