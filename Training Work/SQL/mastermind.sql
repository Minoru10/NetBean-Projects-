DROP DATABASE IF EXISTS mastermindDB;
CREATE DATABASE mastermindDB;

USE mastermindDB;

CREATE TABLE gameTbl(
Id INT PRIMARY KEY AUTO_INCREMENT,
inProgress boolean,
answer varchar (50) );

CREATE TABLE roundTbl(
Id INT PRIMARY KEY AUTO_INCREMENT,
Result varchar(15),
TimePeriod timestamp DEFAULT CURRENT_TIMESTAMP,
Guess varchar(15),
gameID INT NOT NULL,
CONSTRAINT FK_game_round
	foreign key (gameID)
    references gameTbl(Id) );