package com.mbsgood.pulltorefreshloaddata.data;

/*
* User: ChenCHaoXue
* Create date: 2016-12-07 
* Time: 14:27 
* From VCard
*/
public class ChildEntity {

    public ChildEntity(String tradeName) {
        this.tradeName = tradeName;
    }

    private String tradeName;

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }
}
