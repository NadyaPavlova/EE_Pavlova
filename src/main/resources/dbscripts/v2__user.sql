create sequence seq_user;

create table USERS(
id_user LONG PRIMARY KEY,
login varchar2,
password varchar2,
last_name varchar2,
first_name varchar2,
middle_name varchar2,
email varchar2,
phone_number varchar2,
money decimal(15,9),
discount number(3, 0),
admin bit,
unique(login)
);
insert into "USERS"(id_user, login, password, last_name, first_name, middle_name, email, phone_number, money, discount, admin) values (seq_user.nextval, 'admin', 'admin123', 'Pavlova', 'Nadezhda', 'Yurievna', 'ya@ya.ru', '+79520628263', 3000, 3, 1);
