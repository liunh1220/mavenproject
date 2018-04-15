package com.example.test.multithread.list;

import java.util.ArrayList;

public class OneList {

    private ArrayList<String> list = new ArrayList<String>();

    public int getSize() {

        return list.size();
    }

    public void add(String data) {
        list.add(data);
    }
}
