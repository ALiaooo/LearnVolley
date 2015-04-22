package volleydemo.aliao.com.learnvolley.net.httpstacks;

import android.net.http.AndroidHttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.Map;

import volleydemo.aliao.com.learnvolley.net.base.Request;
import volleydemo.aliao.com.learnvolley.net.base.Response;

/**
 * Created by 丽双 on 2015/4/21.
 * api 9以下使用HttpClient执行网络请求
 */
public class HttpClientStack implements HttpStack {

    private static final String userAgent = "default";

    /**
     *创建HttpClient实例
     */
    HttpClient mHttpClient = AndroidHttpClient.newInstance(userAgent);

    @Override
    public Response performRequest(Request<?> request) throws IOException {

        /**
         * 创建某种连接方法的实例
         */
        HttpUriRequest httpUriRequest = createHttpRequest(request);
        /**
         * 设置一些基本参数比如超时
         */
        setConnectionParams(httpUriRequest);
        /**
         * 添加请求头
         */
        setHeaders(httpUriRequest, request.getHeaders());

        /** 设置支持Https **/
        configHttps(request);
        /**
         * 使用HttpClient实例执行请求
         */
        HttpResponse httpResponse = mHttpClient.execute(httpUriRequest);

        /**
         * 读取response的值
         */
        Response response = new Response(httpResponse.getStatusLine());
        response.setEntity(httpResponse.getEntity());

        return response;
    }

    /**
     * https配置
     * @param request
     */
    private void configHttps(Request<?> request) {
        //则使用用户配置的SSLSocketFactory进行配置
      /*  SSLSocketFactory sslSocketFactory = mConfig.getSocketFactory();
        if (request.isHttps() && sslSocketFactory != null) {
            Scheme sch = new Scheme("https", sslSocketFactory, 443);
            mHttpClient.getConnectionManager().getSchemeRegistry().register(sch);
        }*/
    }

    /**
     * 设置一些基本参数
     * @param httpUriRequest
     */
    private void setConnectionParams(HttpUriRequest httpUriRequest) {
        HttpParams httpParams = httpUriRequest.getParams();
        /** 连接超时-定义通过网络与服务器建立连接的超时时间 **/
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        /** socket读数据的超时时间，即从服务器获取响应数据的超时时间 **/
        HttpConnectionParams.setSoTimeout(httpParams, 5000);//------------------------------------->这里的超时时间在Volley里由用户自己设置
        /** 设置http协议参数 **/

        /** 设置我们的HttpClient支持HTTP和HTTPS两种模式 **/
       /* SchemeRegistry schReg =new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));*/

    }

    /**
     * 添加请求头
     * @param httpUriRequest
     * @param headers
     */
    private void setHeaders(HttpUriRequest httpUriRequest, Map<String, String> headers) {
        for (String key:headers.keySet()){
            httpUriRequest.setHeader(key, headers.get(key));
        }
    }

    /**
     * 创建网络请求的连接方式
     * @param request
     * @return
     */
    private HttpUriRequest createHttpRequest(Request<?> request) {
        HttpUriRequest httpUriRequest = null;
        switch (request.getHttpMethod()){
            case GET:
                httpUriRequest = new HttpGet(request.getUrl());
                break;
            case POST:
                httpUriRequest = new HttpPost(request.getUrl());
                //添加数据
                httpUriRequest.addHeader("Content-Type", request.getBodyContentType());//---------->这里是一定要写这句话吗
                /** 设置请求参数 **/
                setRequestParams((HttpPost) httpUriRequest, request);
                break;
            case PUT:
                break;
            default:
                ;
        }
        return httpUriRequest;
    }

    /**
     * 设置请求参数到HttpEntity
     * @param httpUriRequest
     * @param request
     */
    private void setRequestParams(HttpPost httpUriRequest, Request<?> request) {

        byte[] body = request.getBody();
        if (null != null){
            HttpEntity httpEntity = new ByteArrayEntity(body);//----------------------------------->HttpEntity太陌生了，还有UrlEncodedFormEntity这个
            httpUriRequest.setEntity(httpEntity);
        }
    }


}
