package volleydemo.aliao.com.learnvolley.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;

import java.util.HashSet;
import java.util.Set;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.entity.Weather;
import volleydemo.aliao.com.learnvolley.entity.WeatherInfo;
import volleydemo.aliao.com.learnvolley.utils.Constants;
import volleydemo.aliao.com.learnvolley.utils.L;
import volleydemo.aliao.com.learnvolley.utils.VolleySingleton;

/**
 * Created by 丽双 on 2015/3/25.
 */
public class GsonRequestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gson_request, container, false);
        initViews(view);
        return view;
    }


    private void initViews(View view) {

        //请求前加载，显示加载进度框

        GsonRequest gsonRequest = new GsonRequest<Weather>(Constants.GSON_REQUEST_DEFALUT_URL, Weather.class, new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather response) {
                //取消加载进度框
                WeatherInfo weatherInfo = response.getWeatherInfo();
                L.d("weatherInfo = "+weatherInfo);
                L.d("gson request city = "+weatherInfo.getCity());
                L.d("gson request WD = "+weatherInfo.getWD());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //取消加载进度框
            }
        });

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(gsonRequest);
    }
}
