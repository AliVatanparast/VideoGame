package vgame.ir.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import vgame.ir.data.local.dao.RavasiDao
import vgame.ir.data.local.entity.*

@Database(entities = [
    BannerEntity::class,
    UnivercityEntity::class,
    CollegeEntity::class,
    CategoryEntity::class,
    CourseEntity::class,
    ChapterEntity::class,
    LessonEntity::class,
    TimeSpentEntity::class,
    CoursesPercentEntity::class,
    DefaultNoteEntity::class],
        version = 3, exportSchema = true)
abstract class RavasiDatabase : RoomDatabase() {

    abstract fun ravasiDao(): RavasiDao
}
