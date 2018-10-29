package cn.edu.hytc.schoolhelper.http.presenter;

import cn.edu.hytc.schoolhelper.http.HttpMethods;
import cn.edu.hytc.schoolhelper.http.jsonentity.CourseJsonEntity;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by admin on 2018/10/16.
 */

public class CoursePresenter extends HttpMethods{
    //gnmkdm=N2150&su=8201816056&xnm=2018&xqm=3
    public static void listCourses(String gnmkdm, String su, String xnm, String xqm, Subscriber<CourseJsonEntity> subscriber){
        Observable observable = courseService.getCourses(gnmkdm, su, xnm, xqm).map(new HttpResultFunc<CourseJsonEntity>());

        toSubscribe(observable,subscriber);
    }
    public static void login(String yhm, String mm, Subscriber<CourseJsonEntity> subscriber){
        Observable observable = courseService.login(yhm, mm).map(new HttpResultFunc<CourseJsonEntity>());

        toSubscribe(observable,subscriber);
    }
}
