package com.example.maohuawei.live.http.server;


import com.example.maohuawei.live.bean.ClassificationListBean;
import com.example.maohuawei.live.bean.PlayBean;
import com.example.maohuawei.live.bean.RecommendListBean;
import com.example.maohuawei.live.bean.StartPageInfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created with Android Studio
 *
 * @author maohuawei
 * @user maohuawei
 * @date 2018/1/30
 * @time 15:09
 * @qq:898658615
 * @email mhw828@sina.com
 * <p>
 * Description:
 * <p>
 * Retrofit Server
 */

public interface HttpServerInterface {


    /**
     * 获取App启动页信息
     *
     * @return
     */
    @GET("json/page/app-data/info.json?v=3.0.1&os=1&ver=4")
    Observable<StartPageInfoBean> getStartPageInfo();


    /**
     * 获取分类列表信息
     *
     * @return
     */

    @GET("json/app/index/category/info-android.json?v=3.0.1&os=1&ver=4")
    Observable<ClassificationListBean> getClassificationList();


    /**
     * 获取推荐列表信息
     *
     * @return
     */

    @GET("json/app/index/recommend/list-android.json?v=3.0.1&os=1&ver=4")
    Observable<RecommendListBean> getRecommendList();

    /**
     * 获取直播列表信息
     *
     * @return
     */

    @GET("json/play/list.json?v=3.0.1&os=1&ver=4")
    Observable<PlayBean> getPlayList();
}
