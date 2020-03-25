package vgame.ir.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import vgame.ir.data.local.entity.BannerEntity;
import vgame.ir.data.local.entity.CategoryEntity;
import vgame.ir.data.local.entity.ChapterEntity;
import vgame.ir.data.local.entity.CollegeEntity;
import vgame.ir.data.local.entity.CourseEntity;
import vgame.ir.data.local.entity.CourseWithChapterEntity;
import vgame.ir.data.local.entity.CoursesPercentEntity;
import vgame.ir.data.local.entity.DefaultNoteEntity;
import vgame.ir.data.local.entity.LessonEntity;
import vgame.ir.data.local.entity.TimeSpentEntity;
import vgame.ir.data.local.entity.UnivercityEntity;

@Dao
public interface RavasiDao {

    // banners
    @Query("SELECT * FROM banner")
    LiveData<List<BannerEntity>> loadBanners();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveBanner(List<BannerEntity> bannerEntities);

    @Query("SELECT * FROM banner WHERE id=:id")
    LiveData<BannerEntity> getBanner(int id);

    //univercities
    @Query("SELECT * FROM univerciry")
    LiveData<List<UnivercityEntity>> loadUnivercities();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUnivercities(List<UnivercityEntity> univercityEntities);

    @Query("SELECT * FROM univerciry WHERE id=:id")
    LiveData<UnivercityEntity> getUnivercity(int id);

    @Query("DELETE FROM univerciry")
    public void clearAllUnivercities();

    @Query("DELETE FROM category")
    public void clearAllCategory();

    @Query("DELETE FROM college")
    public void clearAllCollege();

    @Query("DELETE FROM course")
    public void clearAllCourses();

    //college
    @Query("SELECT * FROM college WHERE uni_id=:id")
    LiveData<List<CollegeEntity>> loadCollegesByUniId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveColleges(List<CollegeEntity> collegeEntities);

    @Query("SELECT * FROM college WHERE id=:id")
    LiveData<CollegeEntity> getCollege(int id);

    //Category
    @Query("SELECT * FROM category WHERE college_id=:id")
    LiveData<List<CategoryEntity>> loadCategoryByCollegeId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCategory(List<CategoryEntity> categoryEntities);

    @Query("SELECT * FROM category WHERE id=:id")
    LiveData<CategoryEntity> getCategory(int id);

    //Course
    @Query("SELECT * FROM course WHERE category_id=:id")
    LiveData<List<CourseEntity>> loadCourseByCategoryId(int id);

    @Query("SELECT * FROM course WHERE id IN (:ids)")
    LiveData<List<CourseEntity>> loadCourseByMultiId(List<String> ids);

    @Query("SELECT * FROM course WHERE bookmark = '1'")
    LiveData<List<CourseEntity>> loadBookmarkedCourses();

    /*ignored conflicte for bookmark last state*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCourse(List<CourseEntity> courseEntities);

    @Query("SELECT * FROM course WHERE id=:id")
    LiveData<CourseEntity> getCourse(int id);

    @Query("UPDATE course SET bookmark =:b WHERE id=:id")
    void changeCourseBookmark(int id, boolean b);

    // @Query("UPDATE courses_percent SET total_lessons =:total_lesson WHERE id=:id")
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void updateCourseTotalLesson(CoursesPercentEntity coursesPercentEntity);

    @Query("UPDATE courses_percent SET passed_lessons =:passed WHERE course_id=:id")
    void updateCoursePassedLesson(int id, String passed);

    @Query("SELECT * FROM courses_percent WHERE course_id=:id")
    LiveData<CoursesPercentEntity> getCoursePercend(int id);

    @Query("SELECT * FROM courses_percent")
    LiveData<List<CoursesPercentEntity>> getCoursPercentList();

    //Lesson
    @Query("SELECT * FROM lesson WHERE course_chapter_id=:id")
    LiveData<List<LessonEntity>> loadLessonBychapterId(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveLesson(List<LessonEntity> lessonEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveLesson(LessonEntity lessonEntitie);

    @Query("SELECT * FROM lesson WHERE id=:id")
    LiveData<LessonEntity> getLesson(int id);

    @Query("SELECT course.*, " +
            "course.id AS course__id, " +
            "chapter.id AS chapter__id " +
            "FROM course " +
            "JOIN chapter ON course_id = course.id " +
            "JOIN lesson ON course_chapter_id = chapter.id " +
            "JOIN time_spent ON lesson_id = lesson.id " +
            "WHERE time>0")
    LiveData<List<CourseWithChapterEntity>> getStartedLessons();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLessonTimeSpent(TimeSpentEntity timeSpentEntity);

    @Query("UPDATE time_spent SET time =:timeSpentEntity WHERE id=:id")
    void updateLessonTimeSpent(long timeSpentEntity, int id);

    @Query("SELECT * FROM time_spent WHERE lesson_id=:id")
    LiveData<TimeSpentEntity> getTimeSpentOfLesson(int id);

    //Chapters
    @Query("SELECT * FROM chapter WHERE course_id=:id")
    LiveData<List<ChapterEntity>> loadChaptersByCourseId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCapters(List<ChapterEntity> courseEntities);

    @Query("SELECT * FROM chapter WHERE id=:id")
    LiveData<ChapterEntity> getChapter(int id);

    // Note
    @Query("SELECT * FROM default_note WHERE lesson_id=:id")
    LiveData<List<DefaultNoteEntity>> loadNotesByLessonId(int id);

    @Query("DELETE FROM default_note WHERE id=:id")
    void deleteNote(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveNote(DefaultNoteEntity noteEntities);

    @Query("SELECT * FROM default_note WHERE id=:id")
    LiveData<DefaultNoteEntity> getNote(int id);

    // Default Note
    @Query("SELECT * FROM default_note WHERE lesson_id=:lesson_id")
    LiveData<DefaultNoteEntity> getDefaultNote(int lesson_id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveDefaultNote(DefaultNoteEntity noteEntity);

    @Query("SELECT * FROM default_note")
    LiveData<List<DefaultNoteEntity>> getAllUserNotes();

}
