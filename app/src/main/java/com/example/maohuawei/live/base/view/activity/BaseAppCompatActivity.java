package com.example.maohuawei.live.base.view.activity;


import android.os.Handler;

import com.example.maohuawei.live.app.App;
import com.example.maohuawei.live.base.presenter.BasePresenter;
import com.example.maohuawei.live.base.view.IView;
import com.geek.thread.GeekThreadManager;

public abstract class BaseAppCompatActivity<V extends IView, P extends BasePresenter> extends IAppCompatActivity<V, P> {

    protected static GeekThreadManager threadManager = App.getThreadManager();

    protected static Handler handler = App.getHandler();

    @Override
    protected void initView() {


    }


    @Override
    protected P initPresenter() {
        return null;
    }
}

