package volleydemo.aliao.com.learnvolley.net.core;

import java.util.concurrent.BlockingQueue;

import volleydemo.aliao.com.learnvolley.net.base.Request;
import volleydemo.aliao.com.learnvolley.net.base.Response;
import volleydemo.aliao.com.learnvolley.net.httpstacks.HttpStack;

/**
 * Created by 丽双 on 2015/4/21.
 */
public class NetworkExecutor extends Thread {

    private BlockingQueue<Request<?>> mRequestQueue;
    private HttpStack mHttpStack;
    private boolean mQuit;

    public NetworkExecutor(BlockingQueue<Request<?>> queue, HttpStack httpStack){
        mRequestQueue = queue;
        mHttpStack = httpStack;
    }

    @Override
    public void run() {
        while (true){
            Request<?> request;
            try {
                request = mRequestQueue.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
                if (mQuit){
                    return;
                }
                continue;
            }

            if(request.isCanceled()){
                //取消执行请求
                continue;
            }

            Response response = null;

            //从网络上获取数据
            response = mHttpStack.performRequest(request);

            //将获取的结果分发到UI线程处理
            request.deliveryResponse(response);

        }
    }

    public void quit(){
        mQuit = true;
        interrupt();
    }
}
