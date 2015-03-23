package volleydemo.aliao.com.learnvolley.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.adapter.ImageRequestAdapter;
import volleydemo.aliao.com.learnvolley.adapter.NetworkImageAdapter;
import volleydemo.aliao.com.learnvolley.utils.Constants;

/**
 * Created by 丽双 on 2015/3/23.
 */
public class NetworkImageRequsetFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_request, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gv_image_request_car);
        gridView.setAdapter(new NetworkImageAdapter(getActivity(), Constants.IMAGE_URLS));
        return view;
    }

}
