package API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MessageApi {
    @POST(".")
    @Headers("token:w0ToHSv-yP-0o1RPdO")
    @FormUrlEncoded
    Call<SessionResponse> getSession(@Field("a") String command);

    @POST(".")
    @Headers("token:w0ToHSv-yP-0o1RPdO")
    @FormUrlEncoded
    Call<EntryResponse> getEntries(
            @Field("session") String session,
            @Field("a") String command);

    @POST(".")
    @Headers("token:w0ToHSv-yP-0o1RPdO")
    @FormUrlEncoded
    Call<AddedNoteResponse> addEntry(
            @Field("session") String session,
            @Field("a") String command,
            @Field("body") String text);

}
