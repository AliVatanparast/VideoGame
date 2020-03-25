package vgame.ir.data.remote;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vgame.ir.data.remote.model.CoursesResponse;
import vgame.ir.data.remote.model.LessonDetailResponse;
import vgame.ir.data.remote.model.AllGamesResponse;

public interface RavasiDBService {

    @GET("games")
    Call<AllGamesResponse> getGames(@Header("x-rapidapi-key") String key, @Query("page") String page);

    @GET("courses")
    Call<CoursesResponse> searchCourse(@Query("search") String search, @Query("api_token") String api_token, @Query("page") String page);

    @GET("courses/{course_id}/chapters/{chapter_id}/sessions/{lesson_id}")
    Call<LessonDetailResponse> getLessonDetail(@Path("course_id") String course_id,
                                               @Path("chapter_id") String chapter_id,
                                               @Path("lesson_id") String lesson_id,
                                               @Query("api_token") String api_token);

    @FormUrlEncoded
    @POST("courses/{course_id}/register")
    Call<JSONObject> enrollUser(@Path("course_id") Integer course_id, @Field("api_token") String api_token);
}
