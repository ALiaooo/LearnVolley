package volleydemo.aliao.com.learnvolley.net.httpstacks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;

import volleydemo.aliao.com.learnvolley.net.base.Request;
import volleydemo.aliao.com.learnvolley.net.base.Response;

/**
 * Created by 丽双 on 2015/4/21.
 */
public class HttpUrlConnStack implements HttpStack {

    public String userAgent = "gzip";
    public int connTimeOut = 10000;
    public int soTimeOut = 10000;

    @Override
    public Response performRequest(Request<?> request) {

        HttpURLConnection urlConnection = null;
        //构建HttpUrlConnection
        urlConnection = createUrlConnection(request.getUrl());
        //设置headers
        setRequestHeaders(urlConnection, request);

        return null;
    }


    /**
     * 构建HttpURLConnection
     * @param url
     * @return
     */
    private HttpURLConnection createUrlConnection(String url) {
        try {
            URL newUrl = new URL(url);
            try {
                URLConnection urlConnection = newUrl.openConnection();
                urlConnection.setConnectTimeout(connTimeOut);
                urlConnection.setReadTimeout(soTimeOut);
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);
                return (HttpURLConnection) urlConnection;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 设置headers
     * @param urlConnection
     * @param request
     */
    private void setRequestHeaders(HttpURLConnection urlConnection, Request<?> request) {
//        Set<String> headerKeys = ;
    }

}
