package com.example.trafficlight.enums;

public enum Lamp {
    S2N("N2S","S2W",false),
    S2W("N2E","E2W",false),
    E2W("W2E","E2S",false),
    E2S("W2N","S2N",false),    
    N2S(null,null,false),
    N2E(null,null,false),
    W2E(null,null,false),
    W2N(null,null,false),    
    S2E(null,null,true),
    E2N(null,null,true),
    N2W(null,null,true),
    W2S(null,null,true);    
        
    //定义当前灯对应方向的灯，同亮    
    private String oppsiteLampName;    
    //定义当前灯的下一个灯，当前灯亮，下个灯不亮    
    private String nextLampName;    
    //标记当前灯是否为绿灯（亮）    
    private boolean lighted;    
    //灯的判断是否亮的方法    
    public boolean isLighted(){    
        return lighted;    
    }    
    //构造方法，初始化对象    
    private Lamp(String oppsiteLampName,String nextLampName,boolean lighted){    
        this.oppsiteLampName = oppsiteLampName;    
        this.nextLampName = nextLampName;    
        this.lighted = lighted;    
    }    
    //当前灯变绿时，对应的灯也绿了    
    public void light(){    
        this.lighted = true;    
        //当前灯变绿了，让对应的灯也变绿    
        if(oppsiteLampName!=null)    
            Lamp.valueOf(oppsiteLampName).light();    
        //调试用    
        System.out.println(name() + " lamp is green,下面总共应该有6个方向能看到汽车穿过！");    
    }    
        
    //当前灯变红了，对应灯也要变红，并启动下个灯变绿    
    public Lamp blackOut(){    
        this.lighted = false;    
        if(oppsiteLampName!=null)    
            Lamp.valueOf(oppsiteLampName).blackOut();    
        Lamp nextLamp = null;    
        if(nextLampName!=null){    
            nextLamp = Lamp.valueOf(nextLampName);    
            //调试用    
            System.out.println("绿灯从" + name() +"切换为" + nextLampName);    
            nextLamp.light();    
        }    
        return nextLamp;        
    } 
}
