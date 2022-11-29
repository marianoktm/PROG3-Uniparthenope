create definer = root@localhost trigger user_onInsert
	before insert
	on user
	for each row
	BEGIN
        -- DECLARE max_id integer;

        -- SET max_id := (SELECT MAX(CAST(uid as unsigned)) from user);

        -- SET max_id = max_id + 1;

        -- SET NEW.uid = LPAD( CAST(max_id as char(8)), 8, 0);

        SET NEW.uid = UUID();
        SET NEW.create_time = NOW();
    END;

