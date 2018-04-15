package com.example.trafficlight.test;

import com.example.trafficlight.LampController;
import com.example.trafficlight.Road;

public class TraffTest {

	public static void main(String[] args) {
        //将12个方向上的路线存入一个数组    
        String[] directions = new String[]{    
                "S2N","S2W","E2W","E2S",    
                "N2S","N2E","W2E","W2N",    
                "S2E","E2N","N2W","W2S"     
        };    
        //通过循环创建12个对象，即12条路线    
        for(int i=0;i<directions.length;i++){    
            new Road(directions[i]);    
        }    
        //产生整个交通灯系统    
        new LampController();   
	}

}
