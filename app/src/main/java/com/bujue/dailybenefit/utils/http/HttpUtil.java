package com.bujue.dailybenefit.utils.http;

import android.text.TextUtils;
import android.util.Log;

import com.bujue.dailybenefit.callback.Callback;
import com.bujue.dailybenefit.constant.ErrorCode;
import com.bujue.dailybenefit.utils.JsonUtil;
import com.bujue.dailybenefit.utils.Logger;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Http工具类
 *
 * @author bujue
 * @date 16/10/17
 */
public class HttpUtil {

    // 日志TAG
    private static final String TAG = "HttpUtil";

    // OKHTTP
    private OkHttpClient mClient = new OkHttpClient();

    // JSON格式
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    // 对象
    private static HttpUtil mInstance;

    //单例模式
    public static HttpUtil getInstance() {
        if (mInstance == null) {
            synchronized (HttpUtil.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化操作
     */
    public void init() {
        // 增加超时时间
        mClient.setConnectTimeout(20000, TimeUnit.MILLISECONDS);
        mClient.setReadTimeout(20000, TimeUnit.MILLISECONDS);
        mClient.setWriteTimeout(20000, TimeUnit.MILLISECONDS);
    }

    /**
     * 请求网页信息
     * @param url
     * @param callback
     */
    public void get(String url, final Callback<String> callback){

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        mClient.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
                if(callback != null){
                    callback.onException(ErrorCode.RESULT_EXCEPTION,e.toString());
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    if(callback != null){
                        callback.onException(response.code(),response.message());
                    }
                }

                String html = response.body().string();
                Log.d("HttpUtil","html = "+html);
                if(callback != null){
                    callback.onSuccess(html);
                }
            }
        });
    }

    /**
     * HTTP请求
     *
     * @param url
     * @param clazz
     * @param callback
     * @param <T>
     */
    public <T extends HttpBaseEntity> void get(String url, Object params, final Class<T> clazz, final Callback<T> callback) {

        if(TextUtils.isEmpty(url) || clazz == null){
            Logger.e(TAG,"HttpUtil#get params is null");
            if(callback != null){
                callback.onException(ErrorCode.PARAMS_ERROR,"请求参数为空");
            }
            return;
        }

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        mClient.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
                if(callback != null){
                    callback.onException(ErrorCode.RESULT_EXCEPTION,e.toString());
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    if(callback != null){
                        callback.onException(response.code(),response.message());
                    }
                }

                String resultStr = response.body().string();
                Logger.d(TAG, "HttpUtil#get response:%s", resultStr);
                T result = null;
                try {
                    result = JsonUtil.fromJson(resultStr,clazz);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (result != null) {
                    if(callback != null){
                        callback.onSuccess(result);
                    }
                } else {
                    if(callback != null){
                        callback.onException(ErrorCode.RESULT_NULL,"返回结果为空");
                    }
                }
            }
        });
    }

    /**
     * 销毁
     */
    public void destroy(){

    }

}
