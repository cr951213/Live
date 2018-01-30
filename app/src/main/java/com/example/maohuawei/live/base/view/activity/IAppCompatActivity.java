package com.example.maohuawei.live.base.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.maohuawei.live.base.presenter.BasePresenter;
import com.example.maohuawei.live.base.view.IView;

/**
 * Created with Android Studio
 * @author maohuawei
 * @user maohuawei
 * @date 2018/1/30
 * @time 14:44
 * @qq:898658615
 * @email mhw828@sina.com
 *
 * Description:
 *
 *  IActivity
 */

public abstract class IAppCompatActivity<V extends IView, P extends BasePresenter> extends AppCompatActivity {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

    }


    /**
     * 逻辑操作
     */
    @Override
    protected void onResume() {
        super.onResume();

        initView();
        presenter = initPresenter();
        if (presenter != null) {
            presenter.attach((V) this);
        }

    }

    /**
     * 销毁操作
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }

    /**
     * 实例化Presenter
     *
     * @return
     */
    protected abstract P initPresenter();


    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayout();


    /**
     * 初始化控件
     */
    protected abstract void initView();


}
