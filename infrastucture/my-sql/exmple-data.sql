INSERT INTO Users (UserID, UserName) VALUES
(1, 'Alice'),
(2, 'Bob'),
(3, 'Charlie');

INSERT INTO Wallets (WalletID, UserID, Currency, Amount) VALUES
(101, 1, 'USD', 10000),
(102, 1, 'EUR', 8000),
(103, 2, 'USD', 5000),
(104, 3, 'GBP', 12000);
