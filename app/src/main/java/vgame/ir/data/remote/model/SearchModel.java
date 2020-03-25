package vgame.ir.data.remote.model;

import vgame.ir.data.model.Teacher;

public class SearchModel {

    private Teacher teacher;
    private Course course;
    private TYPE type;

    public SearchModel(TYPE type) {
        this.type = type;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public enum TYPE{
        Course, Teacher
    }
}
