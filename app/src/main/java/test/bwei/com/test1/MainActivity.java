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
import android.widget.Toast;

import com.andy.library.ChannelBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kson.slidingmenu.SlidingMenu;
import com.kson.slidingmenu.app.SlidingFragmentActivity;
import com.umeng.socialize.UMShareAPI;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    public static final String URL="http://v.juhe.cn/toutiao/index";
    public static final String KEY="22a108244dbb8d1f49967cd74a0c144d";
    private List<ChannelBean> header;
    private List<Fragment> fragments;
    private List<ChannelBean> list2;
    private List<Fragment> fragments2;
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
        initData();

        for (int i=0;i<header.size();i++){
            if(header.get(i).isSelect()){
                MainFragment fragment=new MainFragment();
                //Bundle bundle=new Bundle();
                //bundle.putString("type",type[i]);
                //fragment.setArguments(bundle);
                fragments.add(fragment);
            }

        }
        myview.draw(header,fragments);
        new NetWorkUtils().verity(this, new NetWorkUtils.NetWorks() {
            @Override
            public void hasMob() {
                Toast.makeText(MainActivity.this,"此时为手机网络",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void haswifi() {
                Toast.makeText(MainActivity.this,"此时为WIFI网络",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void nonet() {

            }
        });
    }

    private void initData() {
        header.add(new ChannelBean("头条",true));
        header.add(new ChannelBean("社会",true));
        header.add(new ChannelBean("国际",true));
        header.add(new ChannelBean("国内",true));
        header.add(new ChannelBean("娱乐",true));
        header.add(new ChannelBean("体育",true));
        header.add(new ChannelBean("军事",true));
        header.add(new ChannelBean("科技",true));
        header.add(new ChannelBean("财经",true));
        header.add(new ChannelBean("时尚",true));

        for (int i=0;i<header.size();i++){
            if(i<4){
                header.get(i).setSelect(true);
            }else{
                header.get(i).setSelect(false);
            }
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        header.clear();
        list2=new ArrayList<>();
       if(resultCode==101){
           String json = data.getStringExtra("json");
          list2=new Gson().fromJson(json,new TypeToken<List<ChannelBean>>(){}.getType());
       }
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        if(list2!=null){
            header.clear();
            fragments.clear();
            for (int i=0;i<list2.size();i++){
                if(list2.get(i).isSelect()){
                    MainFragment fragment=new MainFragment();
                    //Bundle bundle=new Bundle();
                    //bundle.putString("type",type[i]);o
                    //fragment.setArguments(bundle);
                    fragments.add(fragment);
                }

            }
            myview.draw(list2,fragments);
        }else{
            myview.draw(header,fragments);
        }


    }
}
