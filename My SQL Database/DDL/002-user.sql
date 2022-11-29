create table user
(
	username varchar(25) not null,
	email varchar(255) not null,
	password varchar(64) not null,
	create_time timestamp default CURRENT_TIMESTAMP null,
	uid varchar(36) not null
		primary key,
	is_banned char default 'f' not null,
	constraint user_username_uindex
		unique (username)
);

