package vgame.ir.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

class LessonWithTimeSpentEntity {

    @Embedded
    lateinit var lessonEntity: LessonEntity

    @Relation(parentColumn = "id", entityColumn = "lesson_id", entity = TimeSpentEntity::class)
    lateinit var timeSpentEntityList: List<TimeSpentEntity>
}