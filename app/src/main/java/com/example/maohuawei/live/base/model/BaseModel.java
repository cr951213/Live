package com.example.maohuawei.live.base.model;

import com.example.maohuawei.live.base.presenter.BasePresenter;


/**
 * Created with Android Studio
 *
 * @author maohuawei
 * @user maohuawei
 * @date 2018/1/30
 * @time 14:39
 * @qq:898658615
 * @email mhw828@sina.com
 * <p>
 * Description:
 * Model 层基类
 */
public abstract class BaseModel<P extends BasePresenter> implements IModel {


    protected P presenter;

    public BaseModel(P presenter) {
        this.presenter = presenter;
    }

}
