package vgame.ir.data.local.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CourseWithChapterEntity {

    @Embedded
    public CourseEntity course;

    @Relation(parentColumn = "id", entityColumn = "course_id", entity = ChapterEntity.class)
    public List<ChapterWithLessonEntity> chapters;

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public List<ChapterWithLessonEntity> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterWithLessonEntity> chapters) {
        this.chapters = chapters;
    }
}