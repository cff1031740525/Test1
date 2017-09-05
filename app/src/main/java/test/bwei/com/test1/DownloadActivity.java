package test.bwei.com.test1;



import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class DownloadActivity extends AppCompatActivity {

private List<CateBean> list=new ArrayList<>();
    private RecyclerView lv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
        initData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CateBean cateBean : list) {
                    if(cateBean.status){
                        loadData(cateBean.type);
                    }
                }
            }
        });
        RecyclerViewAdapter ada=new RecyclerViewAdapter(list,this);
        lv.setLayoutManager(new LinearLayoutManager(this));
        lv.setAdapter(ada);
        ada.setOnItemClickListener(new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClickListener(int pos, View v) {
                CheckBox cb=v.findViewById(R.id.cb);
                CateBean cateBean = list.get(pos);
                if(cb.isChecked()){
                    cb.setChecked(false);
                    cateBean.status=false;
                }else{
                    cb.setChecked(true);
                    cateBean.status=true;
                }
                //修改数据  并更新
                list.set(pos,cateBean);
            }
        });
    }
    public void loadData(final String type){
        RequestParams parms=new RequestParams(HttpApi.URL);
        parms.addBodyParameter("key",HttpApi.KEY);
        parms.addBodyParameter("type",type);
        x.http().get(parms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("2222222222222"+result);
                dateB d=new dateB(DownloadActivity.this);
                SQLiteDatabase helper = d.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("type",type);
                values.put("result",result);
                helper.insert("info",null,values);

                helper.close();
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
    };

    private void initData() {
        CateBean b=new CateBean();
        b.name="社会";
        b.type="shehui";
        list.add(b);
        b=new CateBean();
        b.name="头条";
        b.type="top";
        list.add(b);
        b=new CateBean();
        b.name="国内";
        b.type="guonei";
        list.add(b);
    }

    private void initView() {
        lv = (RecyclerView) findViewById(R.id.lv);
        btn = (Button) findViewById(R.id.btn);

    }
}
