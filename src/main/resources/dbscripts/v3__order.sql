create sequence seq_order;
create table ORDERS(
idOrder LONG PRIMARY KEY,
idUser number(10, 0),
priceSum decimal(15,2),
creationDate DATE,
closingDate DATE,
status varchar2 default 'GENERATED' check(status in ('GENERATED', 'PAID', 'CLOSED')),
foreign key (idUser) references users(idUser)
);