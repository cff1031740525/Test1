package test.bwei.com.test1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C on 2017/8/30.
 */

public class MyLinerLayout extends LinearLayout {
    private Context context;
    private List<ChannelBean> header;
    private List<Fragment> fragments;
    private View view;
    private List<TextView> tvlist;
    private LinearLayout line;
    private ViewPager vp;
    private TextView addtv;

    public MyLinerLayout(Context context) {
        super(context);
        init(context, null);
    }

    public MyLinerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(final Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyLinerLayout);
        typedArray.getDimensionPixelSize(R.styleable.MyLinerLayout_Margin, 10);
        view = LayoutInflater.from(context).inflate(R.layout.myview, this, true);
        line = view.findViewById(R.id.line);
        vp = view.findViewById(R.id.vp);
        addtv = view.findViewById(R.id.add);


    }

    public void draw(List<ChannelBean> header, List<Fragment> fragemts) {
        this.header = header;
        this.fragments = fragemts;
        drawBanner();
        drawViewpager();

    }

    //根据header集合的数据添加头部标签
    private void drawBanner() {
        tvlist=new ArrayList<>();
        line.removeAllViews();
        for (int i = 0; i < header.size(); i++) {
            if(header.get(i).isSelect()){
                TextView tv = new TextView(context);
                if(i==0){
                    tv.setTextColor(Color.RED);
                }else{
                    tv.setTextColor(Color.BLACK);
                }
                tv.setText(header.get(i).getName());

                LinearLayout.LayoutParams parms=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                parms.leftMargin=30;
                line.addView(tv,parms);
                tvlist.add(tv);
                final int finalI = i;
                tv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vp.setCurrentItem(finalI);
                    }
                });
            }

        }
        addtv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ChannelActivity.startChannelActivity((AppCompatActivity) context,header);
            }
        });
    }

    private void drawViewpager() {
        ada a = new ada(((FragmentActivity) context).getSupportFragmentManager());
        vp.setAdapter(a);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i=0;i<header.size();i++){
                    if(header.get(i).isSelect()){
                        if(position==i){
                            tvlist.get(i).setTextColor(Color.RED);
                        }else{
                            tvlist.get(i).setTextColor(Color.BLACK);
                        }
                    }

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ada extends FragmentPagerAdapter {

        public ada(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
