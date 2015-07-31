package com.playplane.coolweather.util;

/**
 * Created by Lenovo on 2015/7/31.
 */
public interface HttpCallBackListener {
    void onFinish(String response);
    void onError(Exception e);
}
