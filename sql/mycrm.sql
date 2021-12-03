create schema `mycrm-schema`;

use `mycrm-schema`;

create table `customer`(
id int primary key not null auto_increment,
first_name varchar(20) default null,
last_name varchar(20) default null,
email varchar(50) default null
);

alter table `customer` auto_increment = 1;

insert into `customer` (first_name, last_name, email) value ('Shyam', 'Nair', 'shyam.nair@mycrm.com');
insert into `customer` (first_name, last_name, email) value ('Shankar', 'Nair', 'shyam.nair@mycrm.com');

select * from `Customer`;

