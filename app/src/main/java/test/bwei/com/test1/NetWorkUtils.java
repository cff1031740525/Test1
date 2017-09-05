package test.bwei.com.test1;

import android.content.Context;
import android.net.ConnectivityManager;

import android.net.Network;
import android.net.NetworkInfo;

/**
 * Created by C on 2017/9/5.
 */

public class NetWorkUtils {
    private Context context;
    public void verity(Context context, NetWorks network){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info!=null){
            if(info.getType()==ConnectivityManager.TYPE_MOBILE){
                network.hasMob();
            }else if(info.getType()==ConnectivityManager.TYPE_WIFI){
                network.haswifi();
            }else{
                network.nonet();
            }
        }else{
            network.nonet();
        }

    }
    //接口回调
    public interface NetWorks{
        void hasMob();
        void haswifi();
        void nonet();
    }
}
