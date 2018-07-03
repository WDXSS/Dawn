package origin.com.dongnaolsn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import origin.com.dongnaolsn.lsn8.Lsn8PathActivity;

public class DNMainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsn_main);
    }

    public void testLsn8(View view){
        startActivity(new Intent(this, Lsn8PathActivity.class));
    }
}
