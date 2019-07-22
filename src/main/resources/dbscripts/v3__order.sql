create sequence seq_order;
create table ORDERS(
idOrder LONG PRIMARY KEY,
idUser number(10, 0),
sumPrice decimal(15,2),
status bit,
foreign key (idUser) references users(idUser),
);