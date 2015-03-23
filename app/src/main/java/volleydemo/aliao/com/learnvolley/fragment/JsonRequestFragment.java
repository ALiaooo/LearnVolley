package volleydemo.aliao.com.learnvolley.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.utils.Constants;
import volleydemo.aliao.com.learnvolley.utils.L;
import volleydemo.aliao.com.learnvolley.utils.VolleySingleton;

/**
 * Created by 丽双 on 2015/3/20.
 */
public class JsonRequestFragment extends Fragment{

    private ListView mListView;
    private SimpleAdapter mAdapter;
    private List<Map<String, String>> mDataList;
    private RequestQueue mRequestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_json_request, container, false);
        initViews(view);
        jsonRequest();
        return view;
    }

    private void initViews(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_json_request_list);

        mDataList = new ArrayList<>();

        mAdapter = new SimpleAdapter(getActivity(),mDataList, R.layout.listitem_json_request,
                new String[]{"name", "levelname", "price"},
                new int[]{R.id.tv_request_json_name, R.id.tv_request_json_level, R.id.tv_request_json_price});

        mListView.setAdapter(mAdapter);
    }

    private void jsonRequest() {

        mRequestQueue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        //请求的结果可以查看/assets/jsonresult.txt
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constants.JSON_REQUEST_DEFALUT_URL, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                L.d("json string = " + response.toString());

                try {
                    if (!response.has("result"))
                        return;
                    JSONObject result = response.getJSONObject("result");
                    if (!result.has("fctlist"))
                        return;
                    JSONArray fctlist = result.getJSONArray("fctlist");

                    JSONObject fctInfo = fctlist.getJSONObject(0);

                    if (!fctInfo.has("serieslist"))
                        return;
                    JSONArray serieslist = fctInfo.getJSONArray("serieslist");

                    for (int i = 0; i < serieslist.length(); i++){
                        Map<String, String> map = new HashMap<>();
                        map.put("id", serieslist.getJSONObject(i).getString("id"));
                        map.put("name", serieslist.getJSONObject(i).getString("name"));
                        map.put("imgurl", serieslist.getJSONObject(i).getString("imgurl"));
                        map.put("levelid", serieslist.getJSONObject(i).getString("levelid"));
                        map.put("levelname", serieslist.getJSONObject(i).getString("levelname"));
                        map.put("price", serieslist.getJSONObject(i).getString("price"));
                        mDataList.add(map);
                    }

                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        /*JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constants.JSON_REQUEST_DEFALUT_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });*/

        jsonObjectRequest.setTag(this);

        mRequestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mRequestQueue){
            mRequestQueue.cancelAll(this);
        }
    }
}
