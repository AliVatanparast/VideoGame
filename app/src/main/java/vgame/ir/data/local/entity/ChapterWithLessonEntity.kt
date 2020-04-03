package vgame.ir.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

class ChapterWithLessonEntity {

    @Embedded
    lateinit var chapter: ChapterEntity

    @Relation(parentColumn = "id", entityColumn = "course_chapter_id", entity = LessonEntity::class)
    lateinit var lessons: List<LessonWithTimeSpentEntity>
}