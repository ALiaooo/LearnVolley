package volleydemo.aliao.com.learnvolley.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import volleydemo.aliao.com.learnvolley.R;
import volleydemo.aliao.com.learnvolley.utils.Constants;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        findViewById(R.id.btn_main_string_request).setOnClickListener(this);
        findViewById(R.id.btn_main_json_request).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(MainActivity.this, RequestActivity.class);
        switch (v.getId()){
            case R.id.btn_main_string_request:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.STRING_REQUEST_INTEX);
                break;
            case R.id.btn_main_json_request:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.JSON_REQUEST_INTEX);
                break;
        }
        startActivity(intent);
    }

}
