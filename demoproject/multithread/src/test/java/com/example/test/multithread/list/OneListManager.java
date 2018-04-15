package com.example.test.multithread.list;

public class OneListManager {

    public OneList addOneList(OneList list,String data){
        try {
            synchronized (list) {
                if (list.getSize() < 1) { // list只保存一个数据
                    Thread.sleep(100);
                    list.add(data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return list;
    }
}
