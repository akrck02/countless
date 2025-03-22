CREATE TABLE financial_transaction (
   id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
   account_id INTEGER NOT NULL,
   name TEXT,
   value INTEGER,
   timestamp INTEGER,
   schedule_id INTEGER,
   FOREIGN KEY(account_id) REFERENCES account(id),
   FOREIGN KEY(schedule_id) REFERENCES schedule(id)
);