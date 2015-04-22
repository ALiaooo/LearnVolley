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

        /*HttpURLConnection urlConnection = null;
        //构建HttpUrlConnection
        urlConnection = createUrlConnection(request.getUrl());
        //设置headers
        setRequestHeaders(urlConnection, request);*/

        /** 使用HttpURLConnection打开连接 **/
        HttpURLConnection urlConn = null;
        try {
            URL url = new URL("");
            urlConn = (HttpURLConnection) url.openConnection();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        /** 设置基本参数 **/
        //设置输入和输出流
        urlConn.setDoOutput(true);
        urlConn.setDoInput(true);
        //设置连接超时时间和读取超时时间
        urlConn.setConnectTimeout(5000);
        urlConn.setReadTimeout(5000);
        //设置不能使用缓存
        urlConn.setUseCaches(false);
        /** 设置请求头 **/
        for(String key:request.getHeaders().keySet()){
            urlConn.addRequestProperty(key, request.getHeaders().get(key));
        }
        /** 设置请求参数 **/




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
