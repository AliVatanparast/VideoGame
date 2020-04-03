package vgame.ir.data.remote.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import vgame.ir.data.local.entity.ChapterEntity
import vgame.ir.data.model.Attachment

class LessonDetailResponse {
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

        @SerializedName("session")
        @Expose
        var session: Session? = null

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
        @SerializedName("attachments")
        @Expose
        var attachments: List<Attachment>? = null
        @SerializedName("voice_comment")
        @Expose
        var voiceComment: VoiceComment? = null
        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("chapter")
        @Expose
        var chapter: ChapterEntity? = null
        var course_image: String? = null

        var hight_light: String? = null

    }

    inner class VoiceComment {

        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("body")
        @Expose
        var body: String? = null
        @SerializedName("attachment_id")
        @Expose
        var attachmentId: Int? = null
        @SerializedName("course_chapter_session_id")
        @Expose
        var courseChapterSessionId: Int? = null
        @SerializedName("at_time")
        @Expose
        var atTime: String? = null
        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null
        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null

    }
}
