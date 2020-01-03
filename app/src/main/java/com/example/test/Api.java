package com.example.test;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "http://quiz-restapi.herokuapp.com/?format=json";

    @GET("/")
    Call<List<Subject>> getSubjects();

    @GET("module")
    Call<List<Module>> getModules(@Query("search") int id);

    @GET("topic")
    Call<List<Topic>> getTopics(@Query("search") int id);

    @GET("question")
    Call<List<Question>> getQuestions(@Query("search") int id);

    @GET("choice")
    Call<List<Choice>> getChoices(@Query("search") int id);

    @GET("edu")
    Call<List<Notes>> getNotes(@Query("search") int id);

    @POST("SubjectCreate")
    Call<Subject> postSubject(@Body Subject subject);

    @POST("module/ModuleCreate")
    Call<Module> postModule(@Body Module module);

    @POST("topic/TopicCreate")
    Call<Topic> postTopic(@Body Topic topic);

    @POST("question/QuestionCreate")
    Call<Question> postQuestion(@Body Question question);

    @POST("choice/ChoiceCreate")
    Call<Choice> postChoice(@Body Choice choice);

    @Multipart
    @POST("edu/NoteCreate")
    Call<Notes> uploadNotes(@Part MultipartBody.Part file,
                                    @Part("module") Integer module);


}