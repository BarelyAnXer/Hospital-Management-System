create table if not exists users
(
	username varchar(255) null,
	password varchar(255) null
);

INSERT INTO mydb.users (username, password) VALUES ('admin', 'admin');
