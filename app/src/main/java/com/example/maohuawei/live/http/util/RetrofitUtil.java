package com.example.maohuawei.live.http.util;


import com.example.maohuawei.live.http.api.API;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created with Android Studio
 *
 * @author maohuawei
 * @user maohuawei
 * @date 2018/1/30
 * @time 15:05
 * @qq:898658615
 * @email mhw828@sina.com
 * <p>
 * Description:
 * <p>
 * Retrofit 单例设计工具类
 */

public class RetrofitUtil {

    private static Retrofit retrofit = null;


    public static Retrofit getInstance() {

        if (retrofit == null) {

            synchronized (RetrofitUtil.class) {

                retrofit = new Retrofit.Builder()
                        .baseUrl(API.URL_BASE)
                        .client(OkHttpUtil.getInstance())
                        // 高度自定义转化器
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        // 将call 转化成 Observable,这是Retrofit与RxJava结合使用的关键
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();

            }

        }

        return retrofit;
    }

}