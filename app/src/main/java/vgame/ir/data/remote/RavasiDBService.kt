package vgame.ir.data.remote

import org.json.JSONObject

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import vgame.ir.data.remote.model.CoursesResponse
import vgame.ir.data.remote.model.LessonDetailResponse
import vgame.ir.data.remote.model.AllGamesResponse

interface RavasiDBService {

    @GET("games")
    fun getGames(@Header("x-rapidapi-key") key: String, @Query("page") page: String): Call<AllGamesResponse>

    @GET("courses")
    fun searchCourse(@Query("search") search: String, @Query("api_token") api_token: String, @Query("page") page: String): Call<CoursesResponse>

    @GET("courses/{course_id}/chapters/{chapter_id}/sessions/{lesson_id}")
    fun getLessonDetail(@Path("course_id") course_id: String,
                        @Path("chapter_id") chapter_id: String,
                        @Path("lesson_id") lesson_id: String,
                        @Query("api_token") api_token: String): Call<LessonDetailResponse>

    @FormUrlEncoded
    @POST("courses/{course_id}/register")
    fun enrollUser(@Path("course_id") course_id: Int?, @Field("api_token") api_token: String): Call<JSONObject>
}
