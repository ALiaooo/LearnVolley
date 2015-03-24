package volleydemo.aliao.com.learnvolley.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
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
    private ImageLoader mImageLoader;

    private VolleySingleton(Context context){
        mCtx = context;
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

            private final LruCache<String, Bitmap> cache = new LruCache<>(5*1024*1024);//5M

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });

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

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader(){
        return mImageLoader;
    }


}
