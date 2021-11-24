-- Create Database
CREATE DATABASE TheGolfClub;
-- Create user for database access
CREATE USER 'the_golf_club' @'localhost' IDENTIFIED BY 'the_golf_club';
-- grant privileges to database
GRANT ALL PRIVILEGES ON TheGolfClub.* TO 'the_golf_club' @'localhost';
-- flush privileges to make sure they work
FLUSH PRIVILEGES;
-- Table for membership information
CREATE TABLE `TheGolfClub`.`memberships` (
  `member_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` INT(10) NOT NULL,
  `membership_start_date` DATE NOT NULL,
  `membership_length_months` INT(2) NOT NULL,
  `membership_type` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`member_id`)
);
-- Table for tournament information
CREATE TABLE `TheGolfClub`.`tournaments`(
  `tournament_id` INT(11) NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(255) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `entry_fee` int(11) NOT NULL,
  `total_prize` int(11) NOT NULL,
  PRIMARY KEY (`tournament_id`)
);
-- Create table for tournament results
CREATE TABLE `TheGolfClub`.`tournament_results` (
  `result_id` INT(11) NOT NULL AUTO_INCREMENT,
  `tournament_id` INT(11) NOT NULL,
  `member_id` INT(11) NOT NULL,
  `result_rank` INT(11) NOT NULL,
  FOREIGN KEY (`tournament_id`) REFERENCES `TheGolfClub`.`tournaments` (`tournament_id`),
  FOREIGN KEY (`member_id`) REFERENCES `TheGolfClub`.`memberships` (`member_id`),
  PRIMARY KEY (`member_id`)
);
-- Create view for list of tournaments a member has played
CREATE OR REPLACE VIEW member_results AS
SELECT res.result_id,
  tou.location,
  tou.start_date,
  tou.result_rank
FROM tournament_results res
  JOIN tournaments tou USING (tournament_id);
-- create view for list of members in a tournament
CREATE OR REPLACE VIEW tournament_results AS
SELECT tou.tournament_id,
  tou.start_date,
  res.result_rank,
  mem.name
FROM tournament_results res
  JOIN memberships mem USING (member_id),
  JOIN tournaments tou USING (tournament_id);