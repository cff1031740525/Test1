package test.bwei.com.test1;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private List<String> header;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        header=new ArrayList<>();
        fragments=new ArrayList<>();
        for (int i=0;i<15;i++){
            header.add("新闻");
            fragments.add(new letf());
        }
        MyLinerLayout l = (MyLinerLayout) findViewById(R.id.buju);
        l.draw(header,fragments);
    }
}
