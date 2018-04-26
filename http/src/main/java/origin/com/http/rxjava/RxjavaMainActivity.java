package origin.com.http.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import origin.com.http.R;



public class RxjavaMainActivity extends AppCompatActivity {
    private static final String TAG = "RxjavaMainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_main);
        findViewById(R.id.btn_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RxjavaMainActivity.this,Rxjava01Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RxjavaMainActivity.this,Rxjava02Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RxjavaMainActivity.this,Rxjava03Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RxjavaMainActivity.this,Rxjava04Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_05_07).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RxjavaMainActivity.this,Rxjava05_07Activity.class);
                startActivity(intent);
            }
        });
    }


}
