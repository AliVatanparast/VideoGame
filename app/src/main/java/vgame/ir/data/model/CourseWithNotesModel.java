package vgame.ir.data.model;

import java.util.List;

import vgame.ir.data.local.entity.CourseEntity;
import vgame.ir.data.local.entity.DefaultNoteEntity;

public class CourseWithNotesModel {
    private CourseEntity courseEntity;
    private List<DefaultNoteEntity> noteEntityList;

    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    public List<DefaultNoteEntity> getNoteEntityList() {
        return noteEntityList;
    }

    public void setNoteEntityList(List<DefaultNoteEntity> noteEntityList) {
        this.noteEntityList = noteEntityList;
    }
}
