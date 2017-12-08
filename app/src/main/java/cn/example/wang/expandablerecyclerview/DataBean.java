package cn.example.wang.expandablerecyclerview;

import java.util.List;

/**
 * Created by WANG on 17/12/5.
 */

public class DataBean {
    String title;
    boolean isExpandable;
    boolean havaChild;
    boolean havaInsertChild;
    ChildBean childBean;
    List<DataBean> dataBeanList;

    public DataBean(String title, boolean isExpandable, boolean havaChild, boolean havaInsertChild) {
        this.title = title;
        this.isExpandable = isExpandable;
        this.havaChild = havaChild;
        this.havaInsertChild = havaInsertChild;
    }

    public List<DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    public ChildBean getChildBean() {
        return childBean;
    }

    public void setChildBean(ChildBean childBean) {
        this.childBean = childBean;
    }

    public boolean isHavaInsertChild() {
        return havaInsertChild;
    }

    public void setHavaInsertChild(boolean havaInsertChild) {
        this.havaInsertChild = havaInsertChild;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public boolean isHavaChild() {
        return havaChild;
    }

    public void setHavaChild(boolean havaChild) {
        this.havaChild = havaChild;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsExpandable() {
        return isExpandable;
    }

    public void setIsExpandable(boolean isExpandable) {
        this.isExpandable = isExpandable;
    }
    public static class ChildBean {
        String title;
        int podition;

        public ChildBean(String title, int podition) {
            this.title = title;
            this.podition = podition;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPodition() {
            return podition;
        }

        public void setPodition(int podition) {
            this.podition = podition;
        }
    }

}
