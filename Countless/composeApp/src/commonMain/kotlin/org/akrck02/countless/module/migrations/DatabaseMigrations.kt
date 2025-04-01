package org.akrck02.countless.module.migrations

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL


var DB_1_TO_2 = listOf("ALTER TABLE account ADD COLUMN name TEXT")

var DB_2_to_3 = listOf(
    """
    CREATE TABLE financial_goal_track_record(
        id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        financial_goal_id INTEGER,
        target_value REAL NOT NULL DEFAULT 0,
        current_value REAL NOT NULL DEFAULT 0,
        target_timestamp INTEGER,
        estimated_timestamp INTEGER,
        insert_timestamp INTEGER,
        FOREIGN KEY(financial_goal_id) REFERENCES financial_goal(id)
    );
    """,
    "CREATE INDEX index_financial_goal_track_record_financial_goal_id ON financial_goal_track_record(financial_goal_id);",
    "CREATE INDEX index_financial_goal_account_id ON financial_goal(account_id);",
    "CREATE INDEX index_financial_transaction_account_id ON financial_transaction(account_id);",
    "CREATE INDEX index_financial_transaction_schedule_id ON financial_transaction(schedule_id);",
    "CREATE INDEX index_schedule_account_id ON schedule(account_id);"
)

var DB_3_to_4 = listOf(

    "DROP TABLE financial_goal_track_record;",
    "DROP TABLE account;",
    """
       
        DROP TABLE financial_goal;
        
        
    """.trimIndent(),


    "ALTER TABLE financial_goal ALTER id INTEGER NOT NULL",

    "ALTER TABLE financial_goal ALTER COLUMN id INTEGER NOT NULL;",
    "ALTER TABLE financial_goal ALTER COLUMN account_id INTEGER NOT NULL;",
    "ALTER TABLE financial_goal ALTER COLUMN name TEXT NOT NULL;",
    "ALTER TABLE financial_goal ALTER COLUMN target_value REAL NOT NULL;",
    "ALTER TABLE financial_goal ALTER COLUMN current_value REAL NOT NULL;",
    "ALTER TABLE financial_goal ALTER COLUMN month_spend_limit REAL NOT NULL;",
    "ALTER TABLE financial_goal ALTER COLUMN month_savings REAL NOT NULL;",
    "ALTER TABLE financial_goal ALTER COLUMN target_timestamp INTEGER NOT NULL;",

    "ALTER TABLE financial_goal_track_record ALTER COLUMN id INTEGER NOT NULL;",
    "ALTER TABLE financial_goal_track_record ALTER COLUMN financial_goal_id INTEGER NOT NULL;",
    "ALTER TABLE financial_goal_track_record ALTER COLUMN target_value REAL NOT NULL;",
    "ALTER TABLE financial_goal_track_record ALTER COLUMN current_value REAL NOT NULL;",
    "ALTER TABLE financial_goal_track_record ALTER COLUMN insert_timestamp INTEGER NOT NULL;",
    "ALTER TABLE financial_goal_track_record ALTER COLUMN target_timestamp INTEGER NOT NULL;",

    "ALTER TABLE financial_transaction ALTER COLUMN id INTEGER NOT NULL;",
    "ALTER TABLE financial_transaction ALTER COLUMN account_id INTEGER NOT NULL;",
    "ALTER TABLE financial_transaction ALTER COLUMN name TEXT NOT NULL;",
    "ALTER TABLE financial_transaction ALTER COLUMN value REAL NOT NULL;",
    "ALTER TABLE financial_transaction ALTER COLUMN timestamp INTEGER NOT NULL;",

    "ALTER TABLE schedule ALTER COLUMN id INTEGER NOT NULL;",
    "ALTER TABLE schedule ALTER COLUMN account_id INTEGER NOT NULL;",
    "ALTER TABLE schedule ALTER COLUMN name INTEGER NOT NULL;",
    "ALTER TABLE schedule ALTER COLUMN start_timestamp INTEGER NOT NULL;",
)

fun dbMigration(v1: Int, v2: Int, sql: List<String>) = object : Migration(v1, v2) {
    override fun migrate(connection: SQLiteConnection) {
        sql.forEach {
            connection.execSQL(it)
        }
    }
}
