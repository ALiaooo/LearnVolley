package volleydemo.aliao.com.learnvolley.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 丽双 on 2015/3/20.
 */
public class VolleySingleton {

/*    private static RequestQueue mQueue;

    public static synchronized RequestQueue getQueue(Context context){
        if (null == mQueue){
            mQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mQueue;
    }*/


    private RequestQueue mRequestQueue;
    private Context mCtx;
    private static VolleySingleton mInstance;

    private VolleySingleton(Context context){
        mCtx = context;
    }

    public static synchronized VolleySingleton getInstance(Context context){
        if (null == mInstance)
            mInstance = new VolleySingleton(context);
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
       if (null == mRequestQueue)
           mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
       return mRequestQueue;
    }


}
