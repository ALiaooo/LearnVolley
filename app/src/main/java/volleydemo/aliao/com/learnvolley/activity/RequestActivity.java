package volleydemo.aliao.com.learnvolley.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import volleydemo.aliao.com.learnvolley.fragment.GsonRequestFragment;
import volleydemo.aliao.com.learnvolley.fragment.ImageLoaderRequestFragment;
import volleydemo.aliao.com.learnvolley.fragment.ImageRequestFragment;
import volleydemo.aliao.com.learnvolley.fragment.JsonRequestFragment;
import volleydemo.aliao.com.learnvolley.fragment.LastestNewsListFragment;
import volleydemo.aliao.com.learnvolley.fragment.NetworkImageRequsetFragment;
import volleydemo.aliao.com.learnvolley.fragment.PostRequestFragment;
import volleydemo.aliao.com.learnvolley.fragment.StringRequestFragment;
import volleydemo.aliao.com.learnvolley.fragment.XmlRequestFragment;
import volleydemo.aliao.com.learnvolley.utils.Constants;

/**
 * Created by 丽双 on 2015/3/20.
 */
public class RequestActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fragment fragment = null;

        switch (getIntent().getIntExtra(Constants.FRAGMENT_INDEX, 0)){

            case Constants.STRING_REQUEST_INTEX:
                if (null == fragment)
                    fragment = new StringRequestFragment();
                break;

            case Constants.JSON_REQUEST_INTEX:
                if (null == fragment)
                    fragment = new JsonRequestFragment();
                break;

            case Constants.IMAGE_REQUEST_INTEX:
                if (null == fragment)
                    fragment = new ImageRequestFragment();
                break;

            case Constants.IMAGE_LOADER_REQUEST_INTEX:
                if (null == fragment)
                    fragment = new ImageLoaderRequestFragment();
                break;

            case Constants.NETWORK_IMAGE_LOADER_REQUEST_INTEX:
                if (null == fragment)
                    fragment = new NetworkImageRequsetFragment();
                break;

            case Constants.POST_REQUEST_INTEX:
                if (null == fragment)
                    fragment = new PostRequestFragment();
                break;
            case Constants.GSON_REQUEST_INTEX:
                if (null == fragment)
                    fragment = new GsonRequestFragment();
                break;
            case Constants.XML_REQUEST_INTEX:
                if (null == fragment)
                    fragment = new XmlRequestFragment();
                break;
            case Constants.DEMO_REQUEST_INTEX:
                if (null == fragment)
                    fragment = new LastestNewsListFragment();
                break;
        }

        setTitle("StringRequest");
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
    }

}
