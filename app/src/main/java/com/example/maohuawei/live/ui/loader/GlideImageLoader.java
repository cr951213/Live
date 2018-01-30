package com.example.maohuawei.live.ui.loader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;


/**
 * Created with Android Studio
 *
 * @author maohuawei
 * @user maohuawei
 * @date 2018/1/30
 * @time 15:26
 * @qq:898658615
 * @email mhw828@sina.com
 * <p>
 * Description:
 * <p>
 * 图片加载器
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);

    }
}