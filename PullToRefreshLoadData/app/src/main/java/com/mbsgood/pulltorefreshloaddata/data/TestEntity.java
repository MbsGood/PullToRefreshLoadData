package com.mbsgood.pulltorefreshloaddata.data;

import java.util.ArrayList;

/*
* User: ChenCHaoXue
* Create date: 2016-12-07 
* Time: 14:25 
* From VCard
*/
public class TestEntity {
    private ArrayList<ChildEntity> list;
    private boolean hasNext;

    public ArrayList<ChildEntity> getList() {
        return list;
    }

    public void setList(ArrayList<ChildEntity> list) {
        this.list = list;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}
