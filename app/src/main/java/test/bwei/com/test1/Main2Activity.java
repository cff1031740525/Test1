package test.bwei.com.test1;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    private EventHandler eventHandler=new EventHandler();
    private TextView phone_msg;
    private Button reg;
    private int TIME = 5;
    private final int SECOND = 1000;
    Handler handler=new Handler();
    Runnable myTime=new Runnable() {
        @Override
        public void run() {
            TIME--;
            if(TIME==0){
                handler.removeCallbacks(this);
                TIME=5;
                phone_msg.setEnabled(true);
                phone_msg.setText("再次获取");
            }else{
                phone_msg.setEnabled(false);
                phone_msg.setText(TIME+"S");
                //无限循环
                handler.postDelayed(this,SECOND);
            }
        }
    };
    private EditText phone;
    private EditText yanzheng;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        resSMS();
    }

    private void resSMS() {
        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable)data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Main2Activity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑
                    }
                }
            }
        };

        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    private void initView() {
        phone = (EditText) findViewById(R.id.et_phone);
        yanzheng = (EditText) findViewById(R.id.et_yanzheng);
        btn = (Button) findViewById(R.id.btn_more_reg);
        phone_msg = (TextView) findViewById(R.id.phone_msg);
        reg = (Button) findViewById(R.id.btn_more_reg);
        phone_msg.setOnClickListener(this);
        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_more_reg:

                break;
            case R.id.phone_msg:
                    if(TextUtils.isEmpty(phone.getText().toString())){
                        Toast.makeText(Main2Activity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    handler.postDelayed(myTime,SECOND);
                    SMSSDK.getVerificationCode("86",phone.getText().toString());
                break;
        }
    }
}
