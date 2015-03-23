package volleydemo.aliao.com.learnvolley.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.utils.VolleySingleton;

/**
 * Created by 丽双 on 2015/3/23.
 */
public class ImageLoaderAdapter extends ImageBaseAdapter{

    private Context mCtx;
    private ImageLoader mImageLoader;

    public ImageLoaderAdapter(Context context, String[] imageUrl) {
        super(context, imageUrl);
        mCtx = context;
    }

    @Override
    int getItemLayout() {
        return R.layout.griditem_image_request;
    }

    @Override
    void setImage(ImageView mImageView, String mImageUrl) {

        //使用ImageLoader

        mImageLoader = VolleySingleton.getInstance(mCtx).getImageLoader();
        mImageLoader.get(mImageUrl, ImageLoader.getImageListener(
                mImageView,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher));

    }
}
