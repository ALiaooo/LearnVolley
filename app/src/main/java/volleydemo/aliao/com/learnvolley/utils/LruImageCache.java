package volleydemo.aliao.com.learnvolley.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by 丽双 on 2015/3/24.
 */
public class LruImageCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mLruCache;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public LruImageCache(){
        int maxSize = 10*1024*1024;
        mLruCache = new LruCache<String, Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public Bitmap getBitmap(String url) {

        return mLruCache.get(url);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mLruCache.put(url, bitmap);
    }
}
