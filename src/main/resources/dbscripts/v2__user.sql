create sequence seq_user;

create table USERS(
id_user number(3, 0) PRIMARY KEY,
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
);
insert into "USERS"(id_user, login, password, last_name, first_name, middle_name, email, phone_number, admin) values (seq_user.nextval, 'login', '1234', 'Pavlova', 'Nadezhda', 'Yurievna', 'ya@ya.ru', '+79520628263', 1);
