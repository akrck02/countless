CREATE TABLE financial_goal (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    account_id INTEGER NOT NULL,
    name TEXT,
    target_value INTEGER NOT NULL DEFAULT 0,
    current_value INTEGER NOT NULL DEFAULT 0,
    month_spend_limit INTEGER,
    month_savings INTEGER NOT NULL DEFAULT 0,
    target_timestamp INTEGER,
    estimated_timestamp INTEGER,
    FOREIGN KEY(account_id) REFERENCES account(id)
);