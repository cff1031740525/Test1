package test.bwei.com.test1;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;


public class DownloadActivity extends AppCompatActivity {

private List<CateBean> list=new ArrayList<>();
    private RecyclerView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
        initData();
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
                list.set(pos,cateBean);
            }
        });
    }

    private void initData() {
        CateBean b=new CateBean();
        b.name="社会";
        b.type="shehui";
        list.add(b);
        b=new CateBean();
        b.name="头条";
        b.type="top";
        list.add(b);
    }

    private void initView() {
        lv = (RecyclerView) findViewById(R.id.lv);
    }
}
