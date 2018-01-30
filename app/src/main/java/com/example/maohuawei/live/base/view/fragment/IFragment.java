package com.example.maohuawei.live.base.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maohuawei.live.base.presenter.BasePresenter;
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
 */

public abstract class IFragment<V extends IView, P extends BasePresenter> extends Fragment {


    protected View view;

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = getLayout(inflater, container);

        initView();

        return view;
    }

    protected abstract void initView();

    protected abstract View getLayout(LayoutInflater inflater, ViewGroup container);


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        presenter = initPresenter();

        if (presenter != null) {
            presenter.attach((V) this);
        }
    }

    protected abstract P initPresenter();

    protected <T extends View> T findView(int id) {
        return (T) view.findViewById(id);
    }


    @Override
    public void onDetach() {
        super.onDetach();


        if (presenter != null) {
            presenter.detach();
        }
    }
}
