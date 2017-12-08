package cn.example.wang.expandablerecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.example.wang.expandablerecyclerview.adapter.ExpandableAdapter;
import cn.example.wang.expandablerecyclerview.method.MeOnItemClickListener;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExpandableAdapter expandableAdapter;
    private List<DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        initRecyclerView();
        initData();
        initListener();
    }

    private void initListener() {
        expandableAdapter.setListener(new MeOnItemClickListener() {
            @Override
            public void onParentItemClick(View v, int position, DataBean dataBean) {
                boolean isExpandable = dataBean.getIsExpandable();
                int childAdapterPosition = recyclerView.getChildAdapterPosition(v);
                boolean havaInsertChild = dataBean.isHavaInsertChild();
                Toast.makeText(MainActivity.this, "Position  =  " + childAdapterPosition, Toast.LENGTH_SHORT).show();
                if (isExpandable) {
                    //点击收起
                    dataBean.setExpandable(false);
                    if (havaInsertChild) {
                        dataBean.setHavaInsertChild(false);
                        List<DataBean> dataBeanList = dataBean.getDataBeanList();
                        expandableAdapter.removeDataByPosition(childAdapterPosition + 1,dataBeanList);
                    }
                } else {
                    if (!havaInsertChild) {
                        dataBean.setExpandable(true);
                        dataBean.setHavaInsertChild(true);
                        List<DataBean> dataBeanList = dataBean.getDataBeanList();
                        //点击展开
                        expandableAdapter.insertDataByPosition(childAdapterPosition + 1, dataBeanList);
                    }
                }
            }

            @Override
            public void onChildItemClick(View v, int position, DataBean dataBean) {
                Toast.makeText(MainActivity.this, "Child Position " + position, Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void initData() {
        List<DataBean> childBeanList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DataBean dataBean = new DataBean("ParentItem" + i, false, false, false);
            dataBean.setChildBean(new DataBean.ChildBean("Child Item",i));
            childBeanList.add(dataBean);
        }
        for (int i = 0; i < 10; i++) {
            DataBean dataBean = new DataBean("ParentItem" + i, false, true, false);
            dataBean.setDataBeanList(childBeanList);
            list.add(dataBean);
        }
        expandableAdapter.setRefreshData(list);

    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TreeItemAnimator defaultItemAnimator = new TreeItemAnimator();
        defaultItemAnimator.setMoveDuration(100);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(250);
        animator.setMoveDuration(120);
       // recyclerView.setItemAnimator(defaultItemAnimator);
        recyclerView.setItemAnimator(animator);
        expandableAdapter = new ExpandableAdapter(this);
        recyclerView.setAdapter(expandableAdapter);
    }
}
