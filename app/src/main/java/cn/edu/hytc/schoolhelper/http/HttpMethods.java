package cn.edu.hytc.schoolhelper.http;


import java.util.concurrent.TimeUnit;

import cn.edu.hytc.schoolhelper.http.service.CourseService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhusheng on 2017/8/9.
 */

public class HttpMethods {
    protected static final String BASE_URL="http://202.195.113.107/jwglxt/";
    private static final long DEFAULT_TIMEOUT=5L;
    private static HttpMethods mInstance;
    private static Retrofit retrofit;

   protected static CourseService courseService;

    public HttpMethods(){
        if(mInstance == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //.addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
            courseService = retrofit.create(CourseService.class);
        }
    }

    public static HttpMethods getInstance(){
        if(mInstance == null){
            synchronized (HttpMethods.class){
                if(mInstance == null){
                    mInstance = new HttpMethods();
                }
            }
        }
        return mInstance;
    }
    public static class HttpResultFunc<T> implements Func1<HttpResult<T>, T>{

        @Override
        public T call(HttpResult<T> httpResult) {

            return httpResult.getData();
        }
    }
    public static<T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
}
