package com.graduation.api;

import com.graduation.bean.AnswerUser;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.Question;
import com.graduation.bean.QuestionUser;
import com.graduation.bean.Users;
import com.graduation.bean.UsersInfo;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * des:ApiService
 * Created by xsf
 * on 2016.06.15:47
 */
public interface ApiService {

    @POST(ApiConstants.USER_LOGIN)
    @Headers({"Content-type:application/json;charset=UTF-8"})
    Observable<BaseResponse<UsersInfo>> login(@Body RequestBody body);

    @POST(ApiConstants.ASK_QUESTION)
    @Headers({"Content-type:application/json;charset=UTF-8"})
    Observable<Void> askQuestion(@Body RequestBody body);

    @GET(ApiConstants.GET_LAST_QUESTION)
    Observable<BaseResponse<List<QuestionUser>>> getLastQuestion();

    @GET(ApiConstants.GET_ANSWER/*+"?userId={userId}&questionId={questionId}"*/)
    Observable<BaseResponse<List<AnswerUser>>> getAnswer(@Query("userId") int userId, @Query("questionId")int questionId);
}
