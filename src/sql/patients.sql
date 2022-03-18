create table patients
(
	id int,
	name varchar(255) null,
	address varchar(255) null,
	bill varchar(255) null,
	type varchar(255) null
);

create unique index patients_id_uindex
	on patients (id);

alter table patients
	add constraint patients_pk
		primary key (id);

alter table patients modify id int auto_increment;