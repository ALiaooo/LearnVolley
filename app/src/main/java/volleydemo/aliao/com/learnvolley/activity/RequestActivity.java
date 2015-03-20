package volleydemo.aliao.com.learnvolley.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.fragment.StringRequestFragment;

/**
 * Created by 丽双 on 2015/3/20.
 */
public class RequestActivity extends FragmentActivity {

    private StringRequestFragment mStringRequestFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = new StringRequestFragment();

        setTitle("StringRequest");
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).addToBackStack(null).commit();//android.R.id.content
    }

}
