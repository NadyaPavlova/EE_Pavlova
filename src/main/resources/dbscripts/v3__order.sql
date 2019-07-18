create sequence seq_order;
create table ORDERS(
id_order LONG PRIMARY KEY,
id_user number(10, 0),
sum_price decimal(15,9),
status bit,
foreign key (id_user) references users(id_user),
);