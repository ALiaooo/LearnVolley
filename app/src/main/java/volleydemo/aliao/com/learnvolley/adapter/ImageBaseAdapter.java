package volleydemo.aliao.com.learnvolley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import volleydemo.aliao.com.learnvolley.R;

/**
 * Created by 丽双 on 2015/3/23.
 */
public abstract class ImageBaseAdapter extends BaseAdapter {

    private Context mCtx;
    private String[] mImageUrl;

    public ImageBaseAdapter(Context context, String[] imageUrl){
        mCtx = context;
        mImageUrl = imageUrl;
    }

    @Override
    public int getCount() {
        if (null == mImageUrl)
            return 0;
        return mImageUrl.length;
    }

    @Override
    public Object getItem(int position) {
        if (null == mImageUrl)
            return null;
        return mImageUrl[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (null == convertView){

            convertView = LayoutInflater.from(mCtx).inflate(getItemLayout(), parent, false);

            viewHolder = new ViewHolder();

            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_car);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setImage(viewHolder.mImageView, mImageUrl[position]);

        return convertView;
    }

    abstract int getItemLayout();

    abstract void setImage(ImageView mImageView, String mImageUrl);

    class ViewHolder{
        private ImageView mImageView;
    }
}
