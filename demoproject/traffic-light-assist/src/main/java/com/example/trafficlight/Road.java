package com.example.trafficlight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.trafficlight.enums.Lamp;

public class Road {
    //定义一个集合，用于存储车辆和移除车辆    
    private List<String> vechicles = new ArrayList<String>();    
    //依据路名产生车名    
    private String name = null;    
    public Road(String name){    
        this.name = name;    
            
        //模拟车辆不断随机上路的过程    
        //使用线程池，通过产生单个线程的方法，创建一个线程池    
        ExecutorService pool = Executors.newSingleThreadScheduledExecutor();    
        //调用execute方法，可向线程池提交一个任务，让池中的线程执行任务    
        pool.execute(new Runnable(){    
            //执行的任务，随机产生车，并加入集合    
            public void run() {    
                for(int i=1;i<1000;i++){    
                    try {    
                        //每个10秒随机产生一辆车    
                        Thread.sleep((new Random().nextInt(10)+1)*1000);    
                    } catch (InterruptedException e) {    
                        e.printStackTrace();    
                    }    
                    vechicles.add(Road.this.name + "_" + i);    
                }    
            }    
        });    
            
        //每隔一秒检查对应的灯是否为绿，是则放行    
        //产生一个单线程，创建定时器    
        ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);    
        timer.scheduleAtFixedRate(    
                //执行具体的任务    
                new Runnable(){    
                    public void run(){    
                        //判断路上是否有车，有则进行相应的操作    
                        if(vechicles.size()>0){    
                            //每隔1秒让车通行，通行前要先判断灯是否亮，亮了才能通行，即从集合中移除    
                            boolean lighted = Lamp.valueOf(Road.this.name).isLighted();    
                            if(lighted)    
                                System.out.println(vechicles.remove(0) + " is traversing !");    
                        }    
                    }    
                },    
                1,    
                1,    
                TimeUnit.SECONDS    
                );    
    }  
}
