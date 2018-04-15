package com.example.trafficlight;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.trafficlight.enums.Lamp;

/**
 * 交通灯控制器
 * @author liu.nh
 */
public class LampController {
    //定义当前灯    
    private Lamp currentLamp;    
        
    //构造函数，指定当前灯并让其为绿    
     public LampController(){    
         currentLamp = Lamp.S2N;    
         currentLamp.light();    
             
         //每隔10秒将当前绿灯变为红灯，并让下一个方向的灯变绿    
         //创建定时器    
         ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);    
         //通过定时器的方法，创建线程    
         timer.scheduleAtFixedRate(    
                 new Runnable() {    
                    public void run() {    
                        //让当前灯变红，并返回下一个灯    
                        System.out.println("coming...");    
                        currentLamp = currentLamp.blackOut();    
                    }    
                },    
                10,    
                10,    
                TimeUnit.SECONDS);    
     } 
}
