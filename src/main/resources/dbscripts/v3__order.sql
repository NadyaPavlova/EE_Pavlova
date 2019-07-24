create sequence seq_order;
create table ORDERS(
idOrder LONG PRIMARY KEY,
idUser number(10, 0),
priceSum decimal(15,2),
creationDate date,
closingDate date,
status varchar2 default 'generated' check(status in ('generated', 'paid', 'closed')),
foreign key (idUser) references users(idUser)
);