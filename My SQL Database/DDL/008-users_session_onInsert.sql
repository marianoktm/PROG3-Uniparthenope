create definer = root@localhost trigger users_session_onInsert
	before insert
	on users_sessions
	for each row
	BEGIN
        SET NEW.login_time = NOW();
    END;

