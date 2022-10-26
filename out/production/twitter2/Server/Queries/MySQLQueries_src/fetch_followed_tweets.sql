USE twitterdb;

SELECT username, hashtag, message, post_date, tweets.uid, tweet_id
FROM tweets
join follows f on tweets.uid = f.uid_b join user u on u.uid = tweets.uid
where uid_a = ?;