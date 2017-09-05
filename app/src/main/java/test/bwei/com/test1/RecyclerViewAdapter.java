package test.bwei.com.test1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by C on 2017/9/5.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<CateBean> list;
    private Context context;
    private onItemClickListener onItemClickListener;
    public RecyclerViewAdapter(List<CateBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //类似于settag
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //初始化每一个条目
        View v= LayoutInflater.from(context).inflate(R.layout.reitem,null);
        ViewHolder holder=new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener((Integer)view.getTag(),view);
            }
        });
        return holder;
    }
    //处理逻辑  绘制ui数据
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_name.setText(list.get(position).name);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        CheckBox cb;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.name);
            cb=itemView.findViewById(R.id.cb);
        }
    }
    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
       this.onItemClickListener=onItemClickListener;
    }
    public interface onItemClickListener{
        void onItemClickListener(int pos,View v);
    }
}
