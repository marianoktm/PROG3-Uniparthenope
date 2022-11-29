create definer = root@localhost trigger tweets_onInsert
	before insert
	on tweets
	for each row
	BEGIN
        -- DECLARE max_id integer;

        -- SET max_id := (SELECT MAX(CAST(tweet_id as unsigned)) from tweets);

        -- SET max_id = max_id + 1;

        -- SET NEW.tweet_id = LPAD( CAST(max_id as char(16)), 16, 0);

        SET NEW.tweet_id = UUID();
        SET NEW.post_date = NOW();

    END;

