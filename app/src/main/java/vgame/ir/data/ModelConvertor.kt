package vgame.ir.data

import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LifecycleRegistryOwner

import java.util.ArrayList

import javax.inject.Inject

import vgame.ir.data.local.dao.AppDao
import vgame.ir.data.local.entity.BannerEntity
import vgame.ir.data.local.entity.CategoryEntity
import vgame.ir.data.local.entity.CollegeEntity
import vgame.ir.data.local.entity.CourseEntity
import vgame.ir.data.local.entity.DefaultNoteEntity
import vgame.ir.data.local.entity.LessonEntity
import vgame.ir.data.local.entity.UnivercityEntity
import vgame.ir.data.model.CourseWithNotesModel
import vgame.ir.data.remote.model.BannerResponse
import vgame.ir.data.remote.model.CategoryResponse
import vgame.ir.data.remote.model.CollegeResponse
import vgame.ir.data.remote.model.Course
import vgame.ir.data.remote.model.HomeResponse
import vgame.ir.data.remote.model.LessonDetailResponse
import vgame.ir.data.remote.model.UnivercityResponse

class ModelConvertor @Inject
constructor(private val ravasiDao: AppDao) : LifecycleRegistryOwner {
    internal var lifecycleRegistry = LifecycleRegistry(this)

    fun getCoursesWithNotes(noteEntities: MutableList<DefaultNoteEntity>): List<CourseWithNotesModel> {
        val res = ArrayList<CourseWithNotesModel>()

        while (noteEntities.size > 0) {
            val lesson_id = noteEntities[0].course_id

            val temp = ArrayList<DefaultNoteEntity>()
            var j = 0
            while (j < noteEntities.size) {
                if (lesson_id == noteEntities[j].course_id) {
                    temp.add(noteEntities[j])
                    noteEntities.removeAt(j)
                    j--
                }
                j++
            }

            val course = CourseWithNotesModel()
            course.noteEntityList = temp

            res.add(course)
        }

        return res
    }

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    companion object {
    }
}
