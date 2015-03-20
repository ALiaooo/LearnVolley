package volleydemo.aliao.com.learnvolley.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import volleydemo.aliao.com.learnvolley.fragment.JsonRequestFragment;
import volleydemo.aliao.com.learnvolley.fragment.StringRequestFragment;
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
        }

        setTitle("StringRequest");
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();//android.R.id.content
    }

}
