package test.bwei.com.test1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.kson.slidingmenu.SlidingMenu;
import com.kson.slidingmenu.app.SlidingFragmentActivity;
import com.umeng.socialize.UMShareAPI;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    public static final String URL="http://v.juhe.cn/toutiao/index";
    public static final String KEY="22a108244dbb8d1f49967cd74a0c144d";
    private List<String> header;
    private List<Fragment> fragments;
    private MyLinerLayout myview;
    private String[] type={"top","shehui","guonei","guoji"};
    private SlidingMenu menu;
    private SharedPreferences sp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMeun();
        sp = getSharedPreferences("moshi", MODE_PRIVATE);
        if(TextUtils.isEmpty(sp.getString("status",null))){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        ImageView icon = (ImageView) findViewById(R.id.icon);
        ImageView more = (ImageView) findViewById(R.id.more);
        icon.setOnClickListener(this);
        more.setOnClickListener(this);
        findViewById(R.id.more);
        myview = (MyLinerLayout) findViewById(R.id.mview);
        header=new ArrayList<>();
        fragments=new ArrayList<>();
        header.add("头条");
        header.add("社会");
        header.add("国内");
        header.add("国际");
        for (int i=0;i<4;i++){
            MainFragment fragment=new MainFragment();
            Bundle bundle=new Bundle();
            bundle.putString("type",type[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        myview.draw(header,fragments);

    }

    private void initMeun() {

        menu = new SlidingMenu(this);
        menu.setMenu(R.layout.leftfl);
        //setBehindContentView(R.layout.leftfl);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl,new letf()).commit();
        //左划菜单
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        //设置左划出菜单的区域   边缘左划
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.setBehindOffset);

        //给右划菜单设置一个frame布局
        menu.setSecondaryMenu(R.layout.rithtfl);
        //让右fragment替换帧布局
        getSupportFragmentManager().beginTransaction().replace(R.id.right,new right()).commit();
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.icon:
                menu.showMenu();
                break;
            case R.id.more:
                menu.showSecondaryMenu();
                break;
        }
    }

}
