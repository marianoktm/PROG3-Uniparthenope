create table follows
(
	uid_a varchar(36) not null,
	uid_b varchar(36) not null,
	primary key (uid_a, uid_b),
	constraint follows_user_uid_fk
		foreign key (uid_a) references user (uid)
			on update cascade on delete cascade,
	constraint follows_user_uid_fk_2
		foreign key (uid_b) references user (uid)
			on update cascade on delete cascade
);

