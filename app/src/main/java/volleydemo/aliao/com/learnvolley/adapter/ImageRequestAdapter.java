package volleydemo.aliao.com.learnvolley.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.utils.T;
import volleydemo.aliao.com.learnvolley.utils.VolleySingleton;

/**
 * Created by 丽双 on 2015/3/23.
 */
public class ImageRequestAdapter extends ImageBaseAdapter{

    private Context mCtx;

    public ImageRequestAdapter(Context context, String[] imageUrl){
        super(context, imageUrl);
        mCtx = context;
    }

    @Override
    int getItemLayout() {
        return R.layout.griditem_image_request;
    }

    @Override
    void setImage(final ImageView mImageView, String mImageUrl) {

        //图片请求
        ImageRequest imageRequest = new ImageRequest(mImageUrl,new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                mImageView.setImageBitmap(response);

            }
        }, 0, 0, null, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mImageView.setImageResource(R.mipmap.pagefailed_bg);
                T.showToastShort(mCtx, "请求失败,请检查网络是否正常");
            }
        });

        imageRequest.setTag(mImageUrl);
        VolleySingleton.getInstance(mCtx).getRequestQueue().add(imageRequest);

    }
}
