package origin.com.dongnaolsn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import origin.com.dongnaolsn.lsn8.Lsn8PathActivity;

public class DNMainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsn_main);

        TextView textView = findViewById(R.id.text);
        textView.setText(Html.fromHtml("<b><u color=#00ff00><font color=#ff0000>" +"修稿你吃"+ "</b></u><font/>"));

        TextView textView2 = findViewById(R.id.text2);
        textView2.setText(Html.fromHtml("<span style=border-bottom:1px solid red;>修稿你吃</span>"));
//        <span style="border-bottom:1px solid red;">123</span>
    }

    public void testLsn8(View view){
        startActivity(new Intent(this, Lsn8PathActivity.class));
    }
}
