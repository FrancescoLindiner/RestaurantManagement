CREATE DATABASE IF NOT EXISTS ristorante;
USE ristorante;

create table single_order(
	cod_single_order int(2) not null auto_increment,
	ref_table int(2) not null,
    ref_dish varchar(50) not null,
    primary key (cod_single_order),
    foreign key (ref_dish) references dish (dish) on delete cascade,
    foreign key (ref_table) references orders (table_number) on delete cascade
);

create table dish(
	dish varchar(50) not null,
	cod_dish varchar(5) not null,
    price double not null,
    primary key (cod_dish, dish)
);

create table orders(
	table_number int(2) not null,
    people_number int(2) not null,
    bill double not null,
    primary key (table_number)
);