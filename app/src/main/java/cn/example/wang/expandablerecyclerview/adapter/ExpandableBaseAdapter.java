package cn.example.wang.expandablerecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.example.wang.expandablerecyclerview.R;
import cn.example.wang.expandablerecyclerview.method.ExpandableHelpMethod;

/**
 * Created by WANG on 17/12/8.
 */

public abstract class ExpandableBaseAdapter<T> extends RecyclerView.Adapter<ExpandableBaseAdapter.ExpandableViewHolder> implements ExpandableHelpMethod<T>{

   protected List<T> baseData;

    public ExpandableBaseAdapter() {
        if(baseData == null){
            baseData = new ArrayList<>();
        }
    }

    @Override
    public void setRefreshData(List<T> data) {
        baseData.clear();
        baseData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void setLoadMoveData(List<T> data) {
        baseData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void insertDataByPosition(int insertPosition, List<T> dataBean) {
        baseData.addAll(insertPosition,dataBean);
        for (int i = 0; i < dataBean.size() ; i++) {
            notifyItemInserted(insertPosition+i);
        }
        // notifyItemRangeChanged(position,dataBean.size());
    }

    @Override
    public void removeDataByPosition(int insertPosition, List<T> dataBean) {
        for (int i = 0; i <dataBean.size() ; i++) {
            T dataBean1 = baseData.get(insertPosition);
            if(dataBean1 != null) {
                baseData.remove(insertPosition);
                notifyItemRemoved(insertPosition);
            }
        }
    }


    abstract ExpandableViewHolder baseOnCreateViewHolder(ViewGroup parent, int viewType);

    abstract void baseOnBindViewHolder(ExpandableViewHolder holder, int position);


    abstract int baseGetItemCount();

    @Override
    public ExpandableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return baseOnCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(ExpandableViewHolder holder, int position) {
        baseOnBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return baseGetItemCount();
    }


    public static class ExpandableViewHolder extends RecyclerView.ViewHolder {
        public TextView parent_textView;
        public TextView child_text ;
        public ExpandableViewHolder(View itemView , int ViewType) {
            super(itemView);
            if(ViewType ==1 ){
                child_text = itemView.findViewById(R.id.child_item_title);
            }else {
                parent_textView = itemView.findViewById(R.id.parent_item_text);
            }
        }

    }
}
