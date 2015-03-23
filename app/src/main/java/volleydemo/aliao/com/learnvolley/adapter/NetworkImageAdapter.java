package volleydemo.aliao.com.learnvolley.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.utils.VolleySingleton;

/**
 * Created by 丽双 on 2015/3/23.
 */
public class NetworkImageAdapter extends ImageBaseAdapter {

    private Context mCtx;
    private ImageLoader mImageLoader;

    public NetworkImageAdapter(Context context, String[] imageUrl) {
        super(context, imageUrl);
        mCtx = context;
    }

    @Override
    int getItemLayout() {
        return R.layout.griditem_image_request;
    }

    @Override
    void setImage(ImageView mImageView, String mImageUrl) {

        mImageLoader = VolleySingleton.getInstance(mCtx).getImageLoader();

        NetworkImageView view = (NetworkImageView) mImageView;
        view.setDefaultImageResId(R.mipmap.pagefailed_bg);
        view.setErrorImageResId(R.mipmap.pagefailed_bg);
        view.setImageUrl(mImageUrl, mImageLoader);
    }
}
