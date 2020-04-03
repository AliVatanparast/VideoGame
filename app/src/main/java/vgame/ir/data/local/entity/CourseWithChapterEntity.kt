package vgame.ir.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

class CourseWithChapterEntity {

    @Embedded
    lateinit var course: CourseEntity

    @Relation(parentColumn = "id", entityColumn = "course_id", entity = ChapterEntity::class)
    lateinit var chapters: List<ChapterWithLessonEntity>
}