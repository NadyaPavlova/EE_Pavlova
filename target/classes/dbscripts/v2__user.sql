create sequence seq_user;

create table USERS(
idUser LONG PRIMARY KEY,
login varchar2(20) NOT NULL,
password varchar2(30) NOT NULL,
lastName varchar2(30) NOT NULL,
firstName varchar2(30) NOT NULL,
middleName varchar2(30),
email varchar2(20 )NOT NULL,
phoneNumber varchar2(20),
money decimal(15,2),
discount number(3, 0),
admin bit,
unique(login)
);
insert into "USERS"(idUser, login, password, lastName, firstName, middleName, email, phoneNumber, money, discount, admin) values (seq_user.nextval, 'admin', 'admin123', 'Pavlova', 'Nadezhda', 'Yurievna', 'ya@ya.ru', '+79520628263', 3000, 3, 1);
