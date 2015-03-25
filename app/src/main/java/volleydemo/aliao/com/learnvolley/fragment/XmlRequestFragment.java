package volleydemo.aliao.com.learnvolley.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.android.volley.toolbox.XmlRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.entity.Weather;
import volleydemo.aliao.com.learnvolley.entity.WeatherInfo;
import volleydemo.aliao.com.learnvolley.utils.Constants;
import volleydemo.aliao.com.learnvolley.utils.L;
import volleydemo.aliao.com.learnvolley.utils.VolleySingleton;

/**
 * Created by 丽双 on 2015/3/25.
 */
public class XmlRequestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xml_request, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        XmlRequest xmlRequest = new XmlRequest(Constants.XML_REQUEST_DEFALUT_URL, new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser response) {
                try {
                    int eventType = response.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT){
                        switch (eventType){
                            case XmlPullParser.START_TAG:
                                String nodeName = response.getName();
                                if ("city".equals(nodeName)){
                                    String pName = response.getAttributeValue(0);
                                    L.d("pName = "+pName);
                                }
                                break;
                        }
                        eventType = response.next();
                    }

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(xmlRequest);
    }
}
