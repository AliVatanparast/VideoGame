package vgame.ir.data.model;

public class ExamData {

    private Boolean finalExam;
    private int lesson_id;
    private int course_id;
    private int score;


    public Boolean getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(Boolean finalExam) {
        this.finalExam = finalExam;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
