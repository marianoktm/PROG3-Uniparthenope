create table users_sessions
(
	uid varchar(36) not null,
	session_key varchar(32) not null
		primary key,
	login_time datetime not null,
	constraint users_sessions_user_uid_fk
		foreign key (uid) references user (uid)
			on update cascade on delete cascade
);

