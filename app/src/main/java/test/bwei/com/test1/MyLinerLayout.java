package test.bwei.com.test1;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by C on 2017/8/30.
 */

public class MyLinerLayout extends LinearLayout {
    private Context context;
    private List<String> header;
    private List<Fragment> fragments;
    private View view;
    private LinearLayout line;
    private ViewPager vp;

    public MyLinerLayout(Context context) {
        super(context);
        init(context,null);
    }

    public MyLinerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MyLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public void init(Context context,AttributeSet attrs) {
        this.context=context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyLinerLayout);
        typedArray.getDimensionPixelSize(R.styleable.MyLinerLayout_Margin,10);
        view = LayoutInflater.from(context).inflate(R.layout.myview,this,true);
        line = view.findViewById(R.id.line);
        vp = view.findViewById(R.id.vp);
    }
    public void draw(List<String> header,List<Fragment> fragemts){
        this.header=header;
        this.fragments=fragemts;
        drawBanner();
        drawViewpager();
    }
    //根据header集合的数据添加头部标签
    private void drawBanner() {
        for (int i=0;i<header.size();i++) {
            TextView tv=new TextView(context);
            tv.setText(header.get(i));
            LinearLayout.LayoutParams parms=new LayoutParams(40,20);
            parms.leftMargin=10;
            line.addView(tv,parms);
        }
    }
    private void drawViewpager() {
        ada a=new ada(((FragmentActivity)context).getSupportFragmentManager());
        vp.setAdapter(a);
    }
    class ada extends FragmentPagerAdapter{

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
