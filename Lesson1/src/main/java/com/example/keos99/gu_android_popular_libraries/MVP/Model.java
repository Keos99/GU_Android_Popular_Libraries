package com.example.keos99.gu_android_popular_libraries.MVP;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private List<Integer> mList;

    public Model(){
        mList = new ArrayList<>();

        mList.add(0);
        mList.add(0);
        mList.add(0);
    }

    public int getValueAtIndex(int index){
        return mList.get(index);
    }

    public void setValueAtIndex(int index){
        int currentvalue = getValueAtIndex(index);
        mList.set(index, ++currentvalue);
        setChanged();
        notifyObservers();
    }
}
