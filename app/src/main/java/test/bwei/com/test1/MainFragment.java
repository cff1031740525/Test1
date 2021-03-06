package test.bwei.com.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import view.xlistview.XListView;

/**
 * Created by C on 2017/8/31.
 */

public class MainFragment extends Fragment{


    private View v;
    private XListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = View.inflate(getActivity(), R.layout.fitem,null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = v.findViewById(R.id.xlv);
        //调用联网判断方法  使用的接口回调的方法
        new NetWorkUtils().verity(getActivity(), new NetWorkUtils.NetWorks() {
            //有手机网时
            @Override
            public void hasMob() {

               // Bundle bundle=getArguments();
                //String type = bundle.getString("type");
                final RequestParams parmas=new RequestParams(HttpApi.URL);
                parmas.addBodyParameter("key",HttpApi.KEY);
                //parmas.addBodyParameter("type",type);
                x.http().post(parmas, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        parseJson(result);
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
            }

            @Override
            public void haswifi() {

               // Bundle bundle=getArguments();
                //String type = bundle.getString("type");
                final RequestParams parmas=new RequestParams(HttpApi.URL);
                parmas.addBodyParameter("key",HttpApi.KEY);
               // parmas.addBodyParameter("type",type);
                x.http().post(parmas, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        parseJson(result);
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
            }

            @Override
            public void nonet() {

            }
        });

    }
    private void parseJson(String result) {
        Gson gson=new Gson();
        NewsInfo newsInfo = gson.fromJson(result, NewsInfo.class);
        NewsInfo.ResultBean result1 = newsInfo.result;
        final List<NewsInfo.ResultBean.DataBean> data = result1.data;
        ListViewAdapter ada=new ListViewAdapter(data,getActivity());
        lv.setAdapter(ada);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),NewsActivity.class);
                intent.putExtra("url",data.get(i).url);
                startActivity(intent);
            }
        });
    }
}
