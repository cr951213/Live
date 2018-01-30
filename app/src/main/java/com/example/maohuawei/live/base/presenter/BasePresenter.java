package com.example.maohuawei.live.base.presenter;

import com.example.maohuawei.live.base.model.BaseModel;
import com.example.maohuawei.live.base.view.IView;

/**
 * Created with Android Studio
 *
 * @author maohuawei
 * @user maohuawei
 * @date 2018/1/30
 * @time 14:56
 * @qq:898658615
 * @email mhw828@sina.com
 * <p>
 * Description:
 * Presenter 基类
 */
public abstract class BasePresenter<V extends IView, M extends BaseModel, T> implements IPresenter<T> {


    public V view;

    protected M model;


    public BasePresenter() {


        model = initModel();
    }

    protected abstract M initModel();


    /**
     * 绑定
     *
     * @param view
     */
    public void attach(V view) {

        this.view = view;

    }

    /**
     * 分离
     */
    public void detach() {

        this.view = null;
    }


}

