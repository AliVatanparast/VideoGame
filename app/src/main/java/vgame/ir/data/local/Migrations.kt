package vgame.ir.data.local

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

object Migrations {
    /*public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE default_note (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "text TEXT ," +
                    "lesson_id INTEGER NOT NULL DEFAULT ''," +
                    "course_id INTEGER NOT NULL DEFAULT ''" + ")");
        }
    };*/

    val MIGRATION_1_3: Migration = object : Migration(1, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {/*
            database.execSQL("ALTER TABLE lesson "
                    + " ADD COLUMN time_spent INTEGER DEFAULT 0 NOT NULL");*/
            database.execSQL("CREATE TABLE IF NOT EXISTS `time_spent` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `lesson_id` INTEGER NOT NULL, `time` INTEGER NOT NULL)")
        }
    }
}
