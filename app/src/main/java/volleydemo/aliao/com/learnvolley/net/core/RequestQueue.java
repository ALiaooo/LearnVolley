package volleydemo.aliao.com.learnvolley.net.core;

import android.net.http.AndroidHttpClient;
import android.os.Build;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import volleydemo.aliao.com.learnvolley.net.base.Request;
import volleydemo.aliao.com.learnvolley.net.httpstacks.HttpClientStack;
import volleydemo.aliao.com.learnvolley.net.httpstacks.HttpStack;
import volleydemo.aliao.com.learnvolley.net.httpstacks.HttpUrlConnStack;

/**
 * Created by 丽双 on 2015/4/21.
 *
 * 要有个请求队列
 * 将请求添加到队列
 * 运行执行网络请求的线程
 *
 * 问题private BlockingQueue<Request<?>>这里能不能用泛型T，怎么区别T和?
 * 队列的相关知识(contains的用法) BlockingQueue到底是个什么鬼
 *
 */
public class RequestQueue {

    /**
     * 请求队列
     */
    private BlockingQueue<Request<?>> mRequestQueue = new PriorityBlockingQueue<>();

    /** Number of network request dispatcher threads to start. */
    private static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 4;

    private NetworkExecutor[] mDispatchers;

    private HttpStack mHttpStack;

    public RequestQueue(HttpStack httpStack){
        this(DEFAULT_NETWORK_THREAD_POOL_SIZE, httpStack);
    }

    public RequestQueue(int threadPoolSize, HttpStack httpStack){
        mDispatchers = new NetworkExecutor[threadPoolSize];
        if (null == mHttpStack){
            if (Build.VERSION.SDK_INT >= 9) {
                mHttpStack = new HttpUrlConnStack();

            } else {
                // Prior to Gingerbread, HttpUrlConnection was unreliable.
                // See: http://android-developers.blogspot.com/2011/09/androids-http-clients.html
                mHttpStack = new HttpClientStack();
            }
        }
    }

    /**
     * 启动NetworkExecutor，启动线程去执行网络请求
     */
    private void startNetworkExecutors(){

        for (int i=0; i<mDispatchers.length; i++){
            mDispatchers[i] = new NetworkExecutor(mRequestQueue, mHttpStack );
            mDispatchers[i].start();
        }
    }

    public void start(){
        stop();
        startNetworkExecutors();
    }

    /**
     * 停止正在进行的网络请求
     */
    private void stop() {
        if(null != mDispatchers && mDispatchers.length > 0){
            for (int i = 0; i<mDispatchers.length; i++){
                mDispatchers[i].quit();
            }
        }
    }


    /**
     * 添加请求，但是不能重复添加请求
     * @param request
     */
    public void addRequest(Request<?> request){
        //判断是否重复添加了某请求
        if(!mRequestQueue.contains(request)){
            //设置请求的优先级

            //添加进队列
            mRequestQueue.add(request);
        }
    }


}
