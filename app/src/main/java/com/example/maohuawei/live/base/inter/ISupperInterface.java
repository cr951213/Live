package com.example.maohuawei.live.base.inter;

/**
 * Created with Android Studio
 *
 * @author maohuawei
 * @user maohuawei
 * @date 2018/1/30
 * @time 14:28
 * @qq:898658615
 * @email mhw828@sina.com
 * <p>
 * Description:
 * <p>
 * 数据请求状态接口
 */
public interface ISupperInterface<T> {


    /**
     * 请求成功
     *
     * @param t
     */
    void onSuccess(T t);

    /**
     * 数据请求失败
     *
     * @param e
     */
    void onFailure(Throwable e);


}
