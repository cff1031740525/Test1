package test.bwei.com.test1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by C on 2017/8/30.
 */

public class ListViewAdapter extends BaseAdapter{
    private List<NewsInfo.ResultBean.DataBean> list;
    private Context context;

    public ListViewAdapter(List<NewsInfo.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    class ViewHolder{
        ImageView img;
        TextView title;
        TextView name;
        TextView date;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.item,null);
            holder.img=view.findViewById(R.id.img);
            holder.title=view.findViewById(R.id.title);
            holder.name=view.findViewById(R.id.name);
            holder.date=view.findViewById(R.id.date);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.date.setText(list.get(i).date);
        holder.name.setText(list.get(i).author_name);
        holder.title.setText(list.get(i).title);
        ImageLoader.getInstance().displayImage(list.get(i).thumbnail_pic_s,holder.img);
        return view;
    }
}
