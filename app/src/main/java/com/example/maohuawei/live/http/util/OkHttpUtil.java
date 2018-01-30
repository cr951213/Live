package com.example.maohuawei.live.http.util;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.maohuawei.live.http.Interceptor.LogInterceptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created with Android Studio
 * @author maohuawei
 * @user maohuawei
 * @date 2018/1/30
 * @time 15:06
 * @qq:898658615
 * @email mhw828@sina.com
 *
 * Description:
 *
 *  OkHttp 工具类 单例设计
 */

public class OkHttpUtil {

    //使用单例设计模式
    private static OkHttpClient client = null;

    //构造私有化
    private OkHttpUtil() {

    }


    /**
     * @return
     */
    public static OkHttpClient getInstance() {

        if (client == null) {

            synchronized (OkHttpUtil.class) {

                // 缓冲路径
                File cache = new File(Environment.getExternalStorageDirectory(), "cache");

                // 缓冲大小
                int cacheSize = 1024 * 1024 * 10;

                //实例化OkHttpClient
                client = new OkHttpClient.Builder()
                        //设置连接超时
                        .connectTimeout(15, TimeUnit.SECONDS)
                        //写入超时
                        .writeTimeout(20, TimeUnit.SECONDS)
                        //设置读取超时
                        .readTimeout(20, TimeUnit.SECONDS)
                        //日志拦截器
                        .addInterceptor(new LogInterceptor())
                        //共参数拦截器
                        // .addInterceptor(new CommonParamsInterceptor())
                        //缓冲
                        .cache(new Cache(cache.getAbsoluteFile(), cacheSize))
                        //构建
                        .build();

            }
        }
        //返回OkHttpClient 对象
        return client;
    }


    /**
     * @param url
     * @param callable
     */
    public static void doGet(String url, final Callback callable) {

        getInstance().newCall(new Request.Builder().url(url).build()).enqueue(callable);

    }


    /**
     * @param url
     * @param params
     */
    public static void doPost(String url, Map<String, String> params, Callback callback) {

        FormBody.Builder builder = new FormBody.Builder();


        for (String key : params.keySet()) {

            builder.add(key, params.get(key));

        }

        getInstance().newCall(new Request.Builder().url(url).post(builder.build()).build()).enqueue(callback);

    }


    /**
     * @param url
     * @param file
     * @param fileName
     * @param params
     */
    public static void uploadFile(String url, File file, String fileName, Map<String, String> params) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance();

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        //参数
        if (params != null) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key));
            }
        }
        //文件...参数name指的是请求路径中所接受的参数...如果路径接收参数键值是fileeeee,此处应该改变
        builder.addFormDataPart("file", fileName, RequestBody.create(MediaType.parse("application/octet-stream"), file));

        //构建
        MultipartBody multipartBody = builder.build();

        //创建Request
        Request request = new Request.Builder().url(url).post(multipartBody).build();

        //得到Call
        Call call = okHttpClient.newCall(request);
        //执行请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //上传成功回调 目前不需要处理
                if (response.isSuccessful()) {

                }
            }
        });

    }

    /**
     * @param url
     * @param jsonParams
     * @param callback
     */
    public static void doPostJson(String url, String jsonParams, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);

    }


    /**
     * @param context
     * @param url
     * @param saveDir
     */
    public static void download(final Activity context, final String url, final String saveDir) {
        Request request = new Request.Builder().url(url).build();
        Call call = getInstance().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();//以字节流的形式拿回响应实体内容
                    //apk保存路径
                    final String fileDir = isExistDir(saveDir);
                    //文件
                    File file = new File(fileDir, getNameFromUrl(url));

                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }

                    fos.flush();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "下载成功:" + fileDir + "," + getNameFromUrl(url), Toast.LENGTH_SHORT).show();
                        }
                    });

                    //apk下载完成后 调用系统的安装方法
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                    context.startActivity(intent);


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        is.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }

                }
            }
        });

    }


    /**
     * @param saveDir
     * @return
     * @throws IOException
     */
    public static String isExistDir(String saveDir) throws IOException {
        // 下载位置
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
            if (!downloadFile.mkdirs()) {
                downloadFile.createNewFile();
            }
            String savePath = downloadFile.getAbsolutePath();
            Log.e("savePath", savePath);
            return savePath;
        }
        return null;
    }

    /**
     * @param url
     * @return 从下载连接中解析出文件名
     */
    private static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}