package test.bwei.com.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        WebView wv= (WebView) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if(url!=null){
            wv.loadUrl(url);
        }
    }
}
