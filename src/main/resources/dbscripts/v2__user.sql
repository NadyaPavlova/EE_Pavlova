create sequence seq_user;

create table USERS(
idUser LONG PRIMARY KEY,
login varchar2,
password varchar2,
lastName varchar2,
firstName varchar2,
middleName varchar2,
email varchar2,
phoneNumber varchar2,
money decimal(15,9),
discount number(3, 0),
admin bit,
unique(login)
);
insert into "USERS"(idUser, login, password, lastName, firstName, middleName, email, phoneNumber, money, discount, admin) values (seq_user.nextval, 'admin', 'admin123', 'Pavlova', 'Nadezhda', 'Yurievna', 'ya@ya.ru', '+79520628263', 3000, 3, 1);
