package test.bwei.com.test1;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;



public class DownloadActivity extends AppCompatActivity {


    private RecyclerView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
    }

    private void initView() {
        lv = (RecyclerView) findViewById(R.id.lv);
    }
}
