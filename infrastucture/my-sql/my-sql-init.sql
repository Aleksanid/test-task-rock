CREATE DATABASE TestTask;

USE TestTask;

DROP TABLE IF EXISTS Wallets;

DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
    UserID INT PRIMARY KEY,
    UserName VARCHAR(255) NOT NULL
);

CREATE TABLE Wallets (
    WalletID INT PRIMARY KEY,
    UserID INT,
    Currency VARCHAR(3) NOT NULL,
    Amount DECIMAL(32) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);