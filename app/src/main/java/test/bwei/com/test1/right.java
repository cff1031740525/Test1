package test.bwei.com.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * Created by C on 2017/8/30.
 */

public class right extends Fragment{

    private View v;
    private RelativeLayout download;
    private Switch ts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = View.inflate(getActivity(), R.layout.rightview,null);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DownloadActivity.class);
                startActivity(intent);
            }
        });
        ts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //打开推送
                    Toast.makeText(getActivity(),"打开",Toast.LENGTH_SHORT).show();
                    JPushInterface.resumePush(getActivity());
                }else{
                    //关闭推送
                    JPushInterface.stopPush(getActivity());
                    Toast.makeText(getActivity(),"关闭",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        download = v.findViewById(R.id.download);
        ts = v.findViewById(R.id.ts);
    }
}
