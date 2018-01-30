package com.example.maohuawei.live.http.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created with Android Studio
 *
 * @author maohuawei
 * @user maohuawei
 * @date 2018/1/30
 * @time 15:02
 * @qq:898658615
 * @email mhw828@sina.com
 * <p>
 * Description:
 * <p>
 * 适配器设计模式
 */

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

        onSuccess(t);

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }


    /**
     * 成功的方法
     *
     * @param t
     */
    public abstract void onSuccess(T t);


    /**
     * 失败
     */
    public abstract void onFailed();
}