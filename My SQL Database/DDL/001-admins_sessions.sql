create table admins_sessions
(
	admin_id varchar(8) not null,
	session_key varchar(32) not null
		primary key,
	login_time datetime default CURRENT_TIMESTAMP not null
);

