create sequence seq_order_item;
create table ORDER_ITEM(
id_order_it LONG PRIMARY KEY,
id_order LONG,
id_flower LONG ,
name_flower varchar2,
qty number(10,0),
price decimal(15,9),
foreign key (id_order) references orders (id_order),
foreign key (id_flower) references flowers (id_flower),
);