package volleydemo.aliao.com.learnvolley.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import volleydemo.aliao.com.learnvolley.R;


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

        switch (v.getId()){
            case R.id.btn_main_string_request:
                startActivity(new Intent(MainActivity.this, RequestActivity.class));
                break;
            case R.id.btn_main_json_request:
                break;
        }
    }

}
