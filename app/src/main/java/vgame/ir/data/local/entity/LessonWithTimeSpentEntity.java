package vgame.ir.data.local.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class LessonWithTimeSpentEntity {

    @Embedded
    public LessonEntity lessonEntity;

    @Relation(parentColumn = "id", entityColumn = "lesson_id", entity = TimeSpentEntity.class)
    public List<TimeSpentEntity> timeSpentEntityList;

    public LessonEntity getLessonEntity() {
        return lessonEntity;
    }

    public void setLessonEntity(LessonEntity lessonEntity) {
        this.lessonEntity = lessonEntity;
    }

    public List<TimeSpentEntity> getTimeSpentEntityList() {
        return timeSpentEntityList;
    }

    public void setTimeSpentEntityList(List<TimeSpentEntity> chapters) {
        this.timeSpentEntityList = chapters;
    }
}