package com.playplane.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lenovo on 2015/7/31.
 */
public class HttpUtils {
    public static void sendHttpRequest(final String address, final HttpCallBackListener httpCallBackListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                URL url = null;
                try {
                    url = new URL(address);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = bufferedReader.readLine()) != null)
                        response.append(line);
                    if(httpCallBackListener!=null)
                        httpCallBackListener.onFinish(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    httpCallBackListener.onError(e);
                }
            }
        }).start();
    }
}
