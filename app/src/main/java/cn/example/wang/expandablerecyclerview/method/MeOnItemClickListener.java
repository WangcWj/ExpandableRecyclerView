package cn.example.wang.expandablerecyclerview.method;

import android.view.View;

import cn.example.wang.expandablerecyclerview.DataBean;

/**
 * Created by WANG on 17/12/5.
 */

public interface MeOnItemClickListener {
   /**
    *
    * @param v
    * @param position
    * @param dataBean
    */
   void onParentItemClick(View v, int position, DataBean dataBean);

   /**
    *
    * @param v
    * @param position
    * @param dataBean
    */
   void onChildItemClick(View v, int position, DataBean dataBean);
}
