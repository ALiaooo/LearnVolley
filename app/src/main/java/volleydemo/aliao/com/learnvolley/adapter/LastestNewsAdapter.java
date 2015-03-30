package volleydemo.aliao.com.learnvolley.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.entity.LastestNews;
import volleydemo.aliao.com.learnvolley.utils.VolleySingleton;

/**
 * Created by 丽双 on 2015/3/30.
 */
public class LastestNewsAdapter extends BaseListAdapter<LastestNews> {

    private List<LastestNews> mLastestNewsList;
    private ImageLoader mImageLoader;

    public LastestNewsAdapter(Context context, List<LastestNews> mList) {
        super(context, mList);
        mLastestNewsList = mList;
        mImageLoader = VolleySingleton.getInstance(mCtx).getImageLoader();
    }

    @Override
    View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (null == convertView){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.listitem_lastest_news, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.tv_item_lastest_news_title);
            holder.image = (NetworkImageView) convertView.findViewById(R.id.niv_item_lastest_news_image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (null != mDataList){
            holder.title.setText(mLastestNewsList.get(position).getTitle());
            holder.image.setDefaultImageResId(R.mipmap.pagefailed_bg);
            holder.image.setErrorImageResId(R.mipmap.pagefailed_bg);
            holder.image.setImageUrl(mLastestNewsList.get(position).getImages()[0], mImageLoader);
        }

        return convertView;
    }

    class ViewHolder{

        private TextView title;
        private NetworkImageView image;
    }
}
