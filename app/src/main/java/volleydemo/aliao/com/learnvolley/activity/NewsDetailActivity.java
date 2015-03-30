package volleydemo.aliao.com.learnvolley.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;

import java.util.HashMap;
import java.util.Map;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.entity.News;
import volleydemo.aliao.com.learnvolley.utils.Constants;
import volleydemo.aliao.com.learnvolley.utils.VolleySingleton;

/**
 * Created by 丽双 on 2015/3/30.
 */
public class NewsDetailActivity extends Activity{


    private String url = "http://news-at.zhihu.com/api/4/news/";

    private TextView mTvTitle;

    private String mNewsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        mNewsId = getIntent().getStringExtra(Constants.NEWS_ID);

        initViews();
        requestDatas();
    }

    private void requestDatas() {


        /**
         * 加载进度框：
         * 1.swapeLayout形式显示
         * 2.对话框的形式显示
         * 3.整个背景中间显示加载进度圈
         *
         * 其中第3条可以直接结合网络异常、超时、错误、点击重新连接的显示
         */


        GsonRequest<News> gsonRequest = new GsonRequest<News>(url+mNewsId, News.class, new Response.Listener<News>() {
            @Override
            public void onResponse(News response) {
                mTvTitle.setText(response.getTitle());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(gsonRequest);
    }

    private void initViews() {

        mTvTitle = (TextView) findViewById(R.id.tv_news_detail_title);

    }

    public static String getRequestUrl(String url,String newsId){
        HashMap<String, String> params = new HashMap<>();
        params.put("id",newsId);
        return buildUrl(url, params);
    }

    public static String buildUrl(String url, HashMap<String,String> hashMap){

        StringBuilder builder = new StringBuilder();

        if (hashMap.size() > 0){
            builder.append("?");
            for (Map.Entry entry:hashMap.entrySet()){
                if (TextUtils.isEmpty((CharSequence) entry.getValue())){
                    continue;
                }
                builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }

            builder.deleteCharAt(builder.length()-1);
        }

        return builder.toString() ;
    }

}
