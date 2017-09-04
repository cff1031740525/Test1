package test.bwei.com.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        Bundle bundle=getArguments();
        String type = bundle.getString("type");
        final RequestParams parmas=new RequestParams(HttpApi.URL);
        parmas.addBodyParameter("key",HttpApi.KEY);
        parmas.addBodyParameter("type",type);
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
    private void parseJson(String result) {
        Gson gson=new Gson();
        NewsInfo newsInfo = gson.fromJson(result, NewsInfo.class);
        NewsInfo.ResultBean result1 = newsInfo.result;
        List<NewsInfo.ResultBean.DataBean> data = result1.data;
        ListViewAdapter ada=new ListViewAdapter(data,getActivity());
        lv.setAdapter(ada);
    }
}
