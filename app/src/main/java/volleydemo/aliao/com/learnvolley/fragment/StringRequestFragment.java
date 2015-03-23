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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.utils.Constants;
import volleydemo.aliao.com.learnvolley.utils.L;
import volleydemo.aliao.com.learnvolley.utils.T;
import volleydemo.aliao.com.learnvolley.utils.VolleySingleton;

/**
 * Created by 丽双 on 2015/3/20.
 */
public class StringRequestFragment extends Fragment{

    private TextView mTVResult;
    private EditText mETInputUrl;
    private Button mBtnSend;
    private RequestQueue mRequestQueue;
    private static final String VOLLEY_TAG = "volleyTag";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_string_request, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mETInputUrl = (EditText) view.findViewById(R.id.et_string_request_input_url);
        mETInputUrl.setText(Constants.STRING_REQUEST_DEFALUT_URL);

        mTVResult = (TextView) view.findViewById(R.id.tv_string_request_content);

        mBtnSend = (Button) view.findViewById(R.id.btn_string_request_send);
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(mETInputUrl.getText().toString().trim())){
                    T.showToastShort(getActivity(),"请输入url");
                    return;
                }

                mRequestQueue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
                StringRequest stringRequest = new StringRequest(mETInputUrl.getText().toString().trim(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        L.d("string request response = "+response);
                        mTVResult.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        L.d("string request error = "+error.toString());
                        T.showToastShort(getActivity(), "请求失败");
                    }
                });

                stringRequest.setTag(this);
                mRequestQueue.add(stringRequest);

            }
        });
    }

    @Override
    public void onDestroyView() {
        if (null != mRequestQueue)
            mRequestQueue.cancelAll(this);
        super.onDestroyView();
    }
}
