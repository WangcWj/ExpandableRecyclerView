package cn.example.wang.expandablerecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.example.wang.expandablerecyclerview.DataBean;
import cn.example.wang.expandablerecyclerview.R;
import cn.example.wang.expandablerecyclerview.method.MeOnItemClickListener;

/**
 * Created by WANG on 17/12/5.
 */

public class ExpandableAdapter  extends ExpandableBaseAdapter<DataBean>{
    private Context context;
    private LayoutInflater inflater;
    private MeOnItemClickListener meOnitemClickListener;
    public ExpandableAdapter(Context context) {
        super();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void setListener(Object o) {
        MeOnItemClickListener lister = (MeOnItemClickListener) o;
        this.meOnitemClickListener = lister;
    }

    @Override
    public int getItemViewType(int position) {
        if(baseData.size() == 0) {
            return super.getItemViewType(position);
        }else {
            boolean isHavaChild = baseData.get(position).isHavaChild();
            if(!isHavaChild){
                //子列表
                return 1;
            }else {
                //父列表
                return 2;
            }
        }
    }

    @Override
    ExpandableViewHolder baseOnCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate;
        if(viewType == 1){
            inflate = inflater.inflate(R.layout.child_layout, parent, false);
        }else {
            inflate = inflater.inflate(R.layout.item_layout, parent, false);
        }
        return new ExpandableViewHolder(inflate,viewType);
    }

    @Override
    void baseOnBindViewHolder(ExpandableViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        final DataBean dataBean = baseData.get(position);
        if(itemViewType == 1){
            final DataBean.ChildBean childBean = dataBean.getChildBean();
            if(childBean == null){
                return;
            }
            String title = childBean.getTitle();
            holder.child_text.setText(title);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(meOnitemClickListener != null){
                        meOnitemClickListener.onChildItemClick(v,childBean.getPodition(),dataBean);
                    }
                }
            });
        }else {
            String title = dataBean.getTitle();
            holder.parent_textView.setText(title);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(meOnitemClickListener != null){
                        meOnitemClickListener.onParentItemClick(v,position,dataBean);
                    }
                }
            });
        }
    }

    @Override
    int baseGetItemCount() {
        return baseData.size();
    }

}
