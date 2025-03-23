CREATE TABLE schedule (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    account_id INTEGER NOT NULL,
    type INTEGER NOT NULL,
    name TEXT,
    start_timestamp INTEGER,
    end_timestamp INTEGER,
    FOREIGN KEY(account_id) REFERENCES account(id)
);