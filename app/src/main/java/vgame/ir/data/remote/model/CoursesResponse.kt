package vgame.ir.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoursesResponse {
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

        @SerializedName("courses")
        @Expose
        var courseResponse: CourseResponse? = null

        @SerializedName("chapters")
        @Expose
        var chapterResponse: ChapterResponse? = null

        @SerializedName("sessions")
        @Expose
        var lessonResponse: LessonResponse? = null
    }

    inner class CourseResponse {

        @SerializedName("data")
        @Expose
        var coursesList: List<Course>? = null
    }

    inner class ChapterResponse {

        @SerializedName("data")
        @Expose
        private val chapterList: List<CourseDetailResponse.Chapter>? = null
    }

    inner class LessonResponse {

        @SerializedName("data")
        @Expose
        var sessionList: List<LessonDetailResponse.Session>? = null
    }

    inner class Courses {

        @SerializedName("current_page")
        @Expose
        var currentPage: Int? = null
        @SerializedName("data")
        @Expose
        var data: List<Course>? = null
        @SerializedName("first_page_url")
        @Expose
        var firstPageUrl: String? = null
        @SerializedName("from")
        @Expose
        var from: Int? = null
        @SerializedName("last_page")
        @Expose
        var lastPage: Int? = null
        @SerializedName("last_page_url")
        @Expose
        var lastPageUrl: String? = null
        @SerializedName("next_page_url")
        @Expose
        var nextPageUrl: String? = null
        @SerializedName("path")
        @Expose
        var path: String? = null
        @SerializedName("per_page")
        @Expose
        var perPage: Int? = null
        @SerializedName("prev_page_url")
        @Expose
        var prevPageUrl: Any? = null
        @SerializedName("to")
        @Expose
        var to: Int? = null
        @SerializedName("total")
        @Expose
        var total: Int? = null

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
        var fullName: String? = null
        @SerializedName("avatar_url")
        @Expose
        var avatarUrl: String? = null

        var hight_light: String? = null

    }

    inner class Category {

        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("major_id")
        @Expose
        var majorId: Int? = null
        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null
        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null
        @SerializedName("is_active")
        @Expose
        var isActive: Int? = null

    }
}
