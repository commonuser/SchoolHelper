package cn.edu.hytc.schoolhelper.http.service;

import cn.edu.hytc.schoolhelper.http.HttpResult;
import cn.edu.hytc.schoolhelper.http.jsonentity.CourseJsonEntity;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by admin on 2018/10/16.
 */

public interface CourseService {

    @GET("kbcx/jskbcx_cxJsKb1.html")
    Observable<HttpResult<CourseJsonEntity>> getCourses(@Query("gnmkdm") String gnmkdm, @Query("su") String su, @Query("xnm") String xnm, @Query("xqm") String xqm);

    @FormUrlEncoded
    @POST("xtgl/login_slogin.html")
    Observable<HttpResult<CourseJsonEntity>>  login(@Field("yhm") String yhm, @Field("mm") String mm);
}
