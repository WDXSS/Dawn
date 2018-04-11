package origin.com.http.retrofit.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import origin.com.http.R;
import origin.com.http.retrofit.service.SimpleService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by zc on 2018/4/8.
 */

public class SimpleRetrofitActivity extends AppCompatActivity{
    private TextView mTextView1,mTextView2;
    private static final String TAG = "SimpleRetrofitActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_simple);
        mTextView1 = findViewById(R.id.tv_name);
        mTextView2 = findViewById(R.id.tv_psw);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqLogin();
            }
        });
    }

    private void reqLogin(){
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://dev-api.gaiay.net/").build();
//        SimpleService service = retrofit.create(SimpleService.class);
//        Call<JSONObject> call = service.reqLogin("zm",mTextView1.getText().toString(),mTextView2.getText().toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://zm.gaiay.net.cn/w/").build();
        SimpleService service = retrofit.create(SimpleService.class);
        Call<JSONObject> call = service.reqLogin1(mTextView1.getText().toString(),mTextView2.getText().toString());
        call.enqueue(new Callback<JSONObject>() {
           @Override
           public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
               Log.i(TAG, "onResponse: response = "+ response.toString());
               Log.i(TAG, "onResponse: response.body() = "+ response.body().toString());

           }

           @Override
           public void onFailure(Call<JSONObject> call, Throwable t) {
               Log.i(TAG, "onFailure: call = "+ call);
           }
       });
    }


}
























