package com.example.test.multithread.list;

public class MyTread1 extends Thread{

    private OneList list;

    public MyTread1(OneList list){
        super();
        this.list = list;
    }
    @Override
    public void run() {
        OneListManager listManager = new  OneListManager();
        listManager.addOneList(list,"AAA");
    }
}
