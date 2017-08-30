package test.bwei.com.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    public static final String URL="http://v.juhe.cn/toutiao/index";
    public static final String KEY="22a108244dbb8d1f49967cd74a0c144d";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        RequestParams params=new RequestParams(URL);
        params.addBodyParameter("key",KEY);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                parseJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void parseJson(String result) {
        Gson gson=new Gson();
        NewsInfo newsInfo = gson.fromJson(result, NewsInfo.class);
        NewsInfo.ResultBean result1 = newsInfo.result;
        List<NewsInfo.ResultBean.DataBean> data = result1.data;
        ListViewAdapter ada=new ListViewAdapter(data,MainActivity.this);
        lv.setAdapter(ada);
    }
}
