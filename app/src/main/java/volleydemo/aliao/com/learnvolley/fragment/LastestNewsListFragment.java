package volleydemo.aliao.com.learnvolley.fragment;

import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.activity.NewsDetailActivity;
import volleydemo.aliao.com.learnvolley.adapter.LastestNewsAdapter;
import volleydemo.aliao.com.learnvolley.entity.LastestNews;
import volleydemo.aliao.com.learnvolley.entity.LastestNewsResult;
import volleydemo.aliao.com.learnvolley.utils.Constants;
import volleydemo.aliao.com.learnvolley.utils.L;
import volleydemo.aliao.com.learnvolley.utils.T;
import volleydemo.aliao.com.learnvolley.utils.VolleySingleton;

/**
 * Created by 丽双 on 2015/3/30.
 */
public class LastestNewsListFragment extends Fragment {

    //http://news-at.zhihu.com/api/4/news/latest
    private String url = "http://news-at.zhihu.com/api/4/news/latest";

    private ListView mListView;
    private List<LastestNews> mList;
    private LastestNewsAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_news, container, false);
        initViews(view);
        requestDatas();
        return view;
    }

    private void initViews(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_lastest_news_list);
        mList = new ArrayList<>();
        mAdapter = new LastestNewsAdapter(getActivity(), mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getActivity(), NewsDetailActivity.class).putExtra(Constants.NEWS_ID,mList.get(position).getId()));

            }
        });
    }

    private void requestDatas() {
        L.d(" = requestDatas = ");

        GsonRequest<LastestNewsResult> gsonRequest = new GsonRequest<LastestNewsResult>(url, LastestNewsResult.class, new Response.Listener<LastestNewsResult>() {
            @Override
            public void onResponse(LastestNewsResult response) {
                L.d("date = "+response.getDate());

                mList.addAll(response.getStories());
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                L.e("error = "+error.getMessage());
                T.showToastShort(getActivity(), "What happend ?");
            }
        });


        VolleySingleton.getInstance(getActivity()).addToRequestQueue(gsonRequest);
    }



}
