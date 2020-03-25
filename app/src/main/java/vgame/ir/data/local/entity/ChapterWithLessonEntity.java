package vgame.ir.data.local.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ChapterWithLessonEntity {

    @Embedded
    public ChapterEntity chapter;

    @Relation(parentColumn = "id", entityColumn = "course_chapter_id", entity = LessonEntity.class)
    public List<LessonWithTimeSpentEntity> lessons;

    public ChapterEntity getChapter() {
        return chapter;
    }

    public void setChapter(ChapterEntity chapter) {
        this.chapter = chapter;
    }

    public List<LessonWithTimeSpentEntity> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonWithTimeSpentEntity> lessons) {
        this.lessons = lessons;
    }
}