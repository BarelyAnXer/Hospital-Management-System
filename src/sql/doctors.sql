create table doctors
(
	id int,
	name varchar(255) null,
	address varchar(255) null,
	mobile varchar(255) null,
	department varchar(255) null
);

create unique index doctors_id_uindex
	on doctors (id);

alter table doctors
	add constraint doctors_pk
		primary key (id);

alter table doctors modify id int auto_increment;