package vgame.ir.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import vgame.ir.data.local.entity.ChapterEntity
import vgame.ir.data.model.Attachment

class CourseDetailResponse {

    @SerializedName("error")
    @Expose
    var error: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null

    inner class Data {
        @SerializedName("course")
        @Expose
        var course: Course? = null

    }

    inner class Course {
        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("title")
        @Expose
        var title: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("price")
        @Expose
        var price: String? = null
        @SerializedName("teacher_id")
        @Expose
        var teacherId: Int? = null
        @SerializedName("category_id")
        @Expose
        var categoryId: Int? = null
        @SerializedName("is_active")
        @Expose
        var isActive: Int? = null
        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null
        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null
        @SerializedName("attachments")
        @Expose
        var attachments: List<Attachment>? = null
        @SerializedName("teacher")
        @Expose
        var teacher: Teacher? = null
        @SerializedName("chapters")
        @Expose
        var chapters: List<ChapterEntity>? = null

        @SerializedName("exam")
        @Expose
        var exam: List<Any>? = null
    }

    inner class Session {

        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("course_chapter_id")
        @Expose
        var courseChapterId: Int? = null
        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null
        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null

    }

    inner class Teacher {

        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("user_id")
        @Expose
        var userId: Int? = null

        @SerializedName("full_name")
        @Expose
        var full_name: String? = null

    }

    inner class Chapter {

        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("title")
        @Expose
        var title: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("course_id")
        @Expose
        var courseId: Int? = null
        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null
        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null
        @SerializedName("sessions")
        @Expose
        var sessions: List<Session>? = null
        @SerializedName("exam")
        @Expose
        var exam: Any? = null

    }
}
