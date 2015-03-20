package volleydemo.aliao.com.learnvolley.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import volleydemo.aliao.com.learnvolley.R;

/**
 * Created by 丽双 on 2015/3/20.
 */
public class JsonRequestFragment extends Fragment{

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_json_request, container, false);

        return view;
    }
}
