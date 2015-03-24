package volleydemo.aliao.com.learnvolley.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.utils.Constants;
import volleydemo.aliao.com.learnvolley.utils.L;
import volleydemo.aliao.com.learnvolley.utils.T;
import volleydemo.aliao.com.learnvolley.utils.VolleySingleton;

/**
 * Created by 丽双 on 2015/3/24.
 */
public class PostRequestFragment extends Fragment {

    private EditText mETInputUrl, mETInputParams;
    private TextView mTVShowResult;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_request, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        mETInputUrl = (EditText) view.findViewById(R.id.et_post_request_input_url);
        mETInputUrl.setText(Constants.POST_REQUEST_URL);
        mETInputParams = (EditText) view.findViewById(R.id.et_post_request_input_params);
        mETInputParams.setText("mobileCode=13636527078;\nuserID=;");

        mTVShowResult = (TextView) view.findViewById(R.id.tv_result);

        view.findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(mETInputUrl.getText().toString().trim()))
                    return;


                StringRequest stringRequest = new StringRequest(Request.Method.POST, mETInputUrl.getText().toString().trim(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTVShowResult.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        T.showToastShort(getActivity(), "请求失败");
                    }
                }){
                    //重写getParams设置post请求参数
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> paramMap = new HashMap<>();
                        paramMap.put("mobileCode", "13636527078");//"mobileCode=13636527078;\nuserID=
                        paramMap.put("userID", "");//"mobileCode=13636527078;\nuserID=

                        /*String paramsStr = mETInputParams.getText().toString().trim();
                        String[] paramsArray = paramsStr.split(";");
                        for (String param : paramsArray) {
                            if (TextUtils.isEmpty(param)) {
                                continue;
                            }
                            String[] keyValueArray = param.split("=");
                            if (keyValueArray.length < 1) {
                                continue;
                            }
                            if (TextUtils.isEmpty(keyValueArray[0])) {
                                continue;
                            }
                            paramMap.put(keyValueArray[0].trim(), keyValueArray.length > 1 ? keyValueArray[1].trim() : "");
                        }
*/
                        printPL(paramMap);

                        return paramMap;
                    }
                };

                stringRequest.setTag(this);

                VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

            }
        });

    }


    public static void printPL(Map hm) {
        Set s = hm.keySet();
        Iterator i = s.iterator();
        while(i.hasNext()) {
            Object o = i.next();
            L.d(o +"--"+hm.get(o));
        }
    }

    @Override
    public void onDestroy() {
        VolleySingleton.getInstance(getActivity()).getRequestQueue().cancelAll(this);
        super.onDestroy();
    }
}
