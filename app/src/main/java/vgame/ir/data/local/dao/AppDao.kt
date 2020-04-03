package vgame.ir.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import vgame.ir.data.local.entity.BannerEntity
import vgame.ir.data.local.entity.CategoryEntity
import vgame.ir.data.local.entity.ChapterEntity
import vgame.ir.data.local.entity.CollegeEntity
import vgame.ir.data.local.entity.CourseEntity
import vgame.ir.data.local.entity.CourseWithChapterEntity
import vgame.ir.data.local.entity.CoursesPercentEntity
import vgame.ir.data.local.entity.DefaultNoteEntity
import vgame.ir.data.local.entity.LessonEntity
import vgame.ir.data.local.entity.TimeSpentEntity
import vgame.ir.data.local.entity.UnivercityEntity

@Dao
interface AppDao {

    @get:Query("SELECT * FROM courses_percent")
    val coursPercentList: LiveData<List<CoursesPercentEntity>>

    @get:Query("SELECT course.*, " +
            "course.id AS course__id, " +
            "chapter.id AS chapter__id " +
            "FROM course " +
            "JOIN chapter ON course_id = course.id " +
            "JOIN lesson ON course_chapter_id = chapter.id " +
            "JOIN time_spent ON lesson_id = lesson.id " +
            "WHERE time>0")
    val startedLessons: LiveData<List<CourseWithChapterEntity>>

    @get:Query("SELECT * FROM default_note")
    val allUserNotes: LiveData<List<DefaultNoteEntity>>

    // banners
    @Query("SELECT * FROM banner")
    fun loadBanners(): LiveData<List<BannerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBanner(bannerEntities: List<BannerEntity>)

    @Query("SELECT * FROM banner WHERE id=:id")
    fun getBanner(id: Int): LiveData<BannerEntity>

    //univercities
    @Query("SELECT * FROM univerciry")
    fun loadUnivercities(): LiveData<List<UnivercityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUnivercities(univercityEntities: List<UnivercityEntity>)

    @Query("SELECT * FROM univerciry WHERE id=:id")
    fun getUnivercity(id: Int): LiveData<UnivercityEntity>

    @Query("DELETE FROM univerciry")
    fun clearAllUnivercities()

    @Query("DELETE FROM category")
    fun clearAllCategory()

    @Query("DELETE FROM college")
    fun clearAllCollege()

    @Query("DELETE FROM course")
    fun clearAllCourses()

    //college
    @Query("SELECT * FROM college WHERE uni_id=:id")
    fun loadCollegesByUniId(id: Int): LiveData<List<CollegeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveColleges(collegeEntities: List<CollegeEntity>)

    @Query("SELECT * FROM college WHERE id=:id")
    fun getCollege(id: Int): LiveData<CollegeEntity>

    //Category
    @Query("SELECT * FROM category WHERE college_id=:id")
    fun loadCategoryByCollegeId(id: Int): LiveData<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCategory(categoryEntities: List<CategoryEntity>)

    @Query("SELECT * FROM category WHERE id=:id")
    fun getCategory(id: Int): LiveData<CategoryEntity>

    //Course
    @Query("SELECT * FROM course WHERE uni_id=:id")
    fun loadCourseByCategoryId(id: Int): LiveData<List<CourseEntity>>

    @Query("SELECT * FROM course WHERE id IN (:ids)")
    fun loadCourseByMultiId(ids: List<String>): LiveData<List<CourseEntity>>

    @Query("SELECT * FROM course WHERE isBookmark = '1'")
    fun loadBookmarkedCourses(): LiveData<List<CourseEntity>>

    /*ignored conflicte for bookmark last state*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCourse(courseEntities: List<CourseEntity>)

    @Query("SELECT * FROM course WHERE id=:id")
    fun getCourse(id: Int): LiveData<CourseEntity>

    @Query("UPDATE course SET isBookmark =:b WHERE id=:id")
    fun changeCourseBookmark(id: Int, b: Boolean)

    // @Query("UPDATE courses_percent SET total_lessons =:total_lesson WHERE id=:id")
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun updateCourseTotalLesson(coursesPercentEntity: CoursesPercentEntity)

    @Query("UPDATE courses_percent SET passed_lessons =:passed WHERE course_id=:id")
    fun updateCoursePassedLesson(id: Int, passed: String)

    @Query("SELECT * FROM courses_percent WHERE course_id=:id")
    fun getCoursePercend(id: Int): LiveData<CoursesPercentEntity>

    //Lesson
    @Query("SELECT * FROM lesson WHERE course_chapter_id=:id")
    fun loadLessonBychapterId(id: Int): LiveData<List<LessonEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveLesson(lessonEntities: List<LessonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLesson(lessonEntitie: LessonEntity)

    @Query("SELECT * FROM lesson WHERE id=:id")
    fun getLesson(id: Int): LiveData<LessonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLessonTimeSpent(timeSpentEntity: TimeSpentEntity)

    @Query("UPDATE time_spent SET time =:timeSpentEntity WHERE id=:id")
    fun updateLessonTimeSpent(timeSpentEntity: Long, id: Int)

    @Query("SELECT * FROM time_spent WHERE lesson_id=:id")
    fun getTimeSpentOfLesson(id: Int): LiveData<TimeSpentEntity>

    //Chapters
    @Query("SELECT * FROM chapter WHERE course_id=:id")
    fun loadChaptersByCourseId(id: Int): LiveData<List<ChapterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCapters(courseEntities: List<ChapterEntity>)

    @Query("SELECT * FROM chapter WHERE id=:id")
    fun getChapter(id: Int): LiveData<ChapterEntity>

    // Note
    @Query("SELECT * FROM default_note WHERE lesson_id=:id")
    fun loadNotesByLessonId(id: Int): LiveData<List<DefaultNoteEntity>>

    @Query("DELETE FROM default_note WHERE id=:id")
    fun deleteNote(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(noteEntities: DefaultNoteEntity)

    @Query("SELECT * FROM default_note WHERE id=:id")
    fun getNote(id: Int): LiveData<DefaultNoteEntity>

    // Default Note
    @Query("SELECT * FROM default_note WHERE lesson_id=:lesson_id")
    fun getDefaultNote(lesson_id: Int): LiveData<DefaultNoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDefaultNote(noteEntity: DefaultNoteEntity)

}
