package origin.com.http.retrofit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import origin.com.http.R;
import origin.com.http.rxjava.RxjavaMainActivity;

/**
 * Created by zc on 2018/4/8.
 */

public class RetrofitMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retrofit);
        findViewById(R.id.btn_simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RetrofitMainActivity.this, SimpleRetrofitActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_java).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RetrofitMainActivity.this, RxjavaMainActivity.class);
                startActivity(intent);
            }
        });


    }
}
