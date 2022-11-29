create table tweets
(
	tweet_id varchar(36) not null
		primary key,
	hashtag varchar(16) null,
	message varchar(140) not null,
	uid varchar(36) not null,
	post_date datetime not null,
	constraint sent_by
		foreign key (uid) references user (uid)
			on update cascade on delete cascade
);

