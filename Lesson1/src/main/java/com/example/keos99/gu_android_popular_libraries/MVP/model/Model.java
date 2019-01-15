package com.example.keos99.gu_android_popular_libraries.MVP.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Integer> mList;

    public Model(){
        mList = new ArrayList<>(3);

        mList.add(0);
        mList.add(0);
        mList.add(0);
    }

    public int getValueAtIndex(int index){
        return mList.get(index);
    }

    public void setValueAtIndex(int index, int value){
        mList.set(index, value);
    }
}
