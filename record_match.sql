#
# Stored procedure used to record a completed match
#
DELIMITER $$

USE `accesso_table_tennis`$$

DROP PROCEDURE IF EXISTS `record_match`$$

CREATE DEFINER=`dba`@`172.16.%` PROCEDURE `record_match`(IN creatorUserId INT, IN winnerUserId INT, IN loserUserId INT, IN winnerScore INT, IN loserScore INT)
BEGIN 

DECLARE match_id INT; 
DECLARE initial_loser_rank INT; 
DECLARE initial_winner_rank INT; 
DECLARE initial_creator_rank INT; 
DECLARE initial_opponent_rank INT; 

# make sure it is a valid match (i.e. winner is moving up) 
SELECT rank_id FROM ranking WHERE user_id=winnerUserId INTO initial_winner_rank; 
SELECT rank_id FROM ranking WHERE user_id=loserUserId INTO initial_loser_rank; 

# not working :(
# IF creatorUserId = winnerUserId THEN BEGIN SET initial_creator_rank=initial_winner_rank; SET initial_opponent_rank=initial_loser_rank; END; ELSEIF creatorUserId = loserUserId THEN BEGIN SET initial_creator_rank=initial_loser_rank; SET initial_opponent_rank=initial_winner_rank; END; ELSE signal SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid creatorUserId'; END IF; IF initial_creator_rank >= initial_opponent_rank THEN signal SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid match, creator is higher rank'); END IF; 

# create the match 
INSERT INTO `match` (match_timestamp, victor_user_id, status_id, creator_user_id) VALUES (NOW(), winnerUserId, 2, creatorUserId); 
SET match_id = LAST_INSERT_ID(); 

# match result for both players 
INSERT INTO match_user (match_id, user_id, score) VALUES (match_id, winnerUserId, winnerScore); 
INSERT INTO match_user (match_id, user_id, score) VALUES (match_id, loserUserId, loserScore); 

# do we need to adjust rank? 
IF winnerUserId = creatorUserId THEN 
BEGIN 
    UPDATE ranking SET user_id=winnerUserId,TIMESTAMP=NOW() WHERE rank_id=initial_loser_rank; 
    UPDATE ranking SET user_id=loserUserId,TIMESTAMP=NOW() WHERE rank_id=initial_winner_rank; 

    # add to rank history 
    INSERT INTO ranking_history (ranking, user_id, match_id) VALUES (initial_loser_rank, winnerUserId, match_id); 
    INSERT INTO ranking_history (ranking, user_id, match_id) VALUES (initial_winner_rank, loserUserId, match_id); 
END; 
END IF;
 
END$$

DELIMITER ;