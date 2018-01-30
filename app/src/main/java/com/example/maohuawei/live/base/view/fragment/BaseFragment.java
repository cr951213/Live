package com.example.maohuawei.live.base.view.fragment;

import android.graphics.Color;
import android.os.Handler;

import com.blankj.utilcode.util.ToastUtils;
import com.geek.thread.GeekThreadManager;
import com.maohuawei.aquarterofanhour.app.App;
import com.maohuawei.aquarterofanhour.base.presenter.BasePresenter;

/**
 * Created by maohuawei on 2018/1/26.
 */

public abstract class BaseFragment<V extends IView, P extends BasePresenter> extends IFragment<V, P> {

    /**
     * 线程池
     */
    protected GeekThreadManager threadManager = App.getThreadManager();


    /**
     * Handler
     */
    protected Handler handler = App.getHandler();


    @Override
    protected P initPresenter() {
        return null;
    }

    /**
     * 吐司方法
     *
     * @param text
     */

    public void showToast(String text) {
        ToastUtils.setBgColor(Color.parseColor("#666666"));
        ToastUtils.showLong(text);
    }

    public static void destoryFragment(BaseFragment fragment) {
        if (fragment != null) {
            fragment = null;
        }
    }
}
