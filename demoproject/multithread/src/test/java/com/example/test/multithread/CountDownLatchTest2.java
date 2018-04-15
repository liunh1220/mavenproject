package com.example.test.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  从名字可以看出，CountDownLatch是一个倒数计数的锁，当倒数到0时触发事件，也就是开锁，其他人就可以进入了。在一些应用场合中，需要等待某个条件达到要求后才能做后面的事情；同时当线程都完成后也会触发事件，以便进行后面的操作。 
	CountDownLatch最重要的方法是countDown()和await()，前者主要是倒数一次，后者是等待倒数到0，如果没有到达0，就只有阻塞等待了。
	一个CountDouwnLatch实例是不能重复使用的，也就是说它是一次性的，锁一经被打开就不能再关闭使用了，如果想重复使用，请考虑使用CyclicBarrier。
	下面的例子简单的说明了CountDownLatch的使用方法，模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
 * @author liu.nh
 *
 */
public class CountDownLatchTest2 {

	private static final int PLAYER_AMOUNT = 10;
	
    public CountDownLatchTest2() {
    }
    
    public static void main(String[] args) {
        //对于每位运动员，CountDownLatch减1后即结束比赛
        CountDownLatch begin = new CountDownLatch(1);
        //对于整个比赛，所有运动员结束后才算结束
        CountDownLatch end = new CountDownLatch(PLAYER_AMOUNT);
        Player[] plays = new Player[PLAYER_AMOUNT];
        for(int i=0;i<PLAYER_AMOUNT;i++){
            plays[i] = new CountDownLatchTest2().new Player(i+1,begin,end);
        }
        //设置特定的线程池，大小为5
        ExecutorService exe = Executors.newFixedThreadPool(PLAYER_AMOUNT);
        for(Player p:plays){
        	exe.execute(p);            //分配线程
        }
        System.out.println("Race begins!");
        begin.countDown();
        try{
            end.await();            //等待end状态变为0，即为比赛结束
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            System.out.println("Race ends!");
        }
        exe.shutdown();
    }
    
    
    class Player implements Runnable {
    	 
        private int id;
        private CountDownLatch begin;
        private CountDownLatch end;
        
        public Player(int i, CountDownLatch begin, CountDownLatch end) {
            super();
            this.id = i;
            this.begin = begin;
            this.end = end;
        }
    
        @Override
        public void run() {
            try{
                begin.await();//等待begin的状态为0
                Thread.sleep((long)(Math.random()*6000));//随机分配时间，即运动员完成时间
                System.out.println("Play"+id+" arrived.");
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                end.countDown();//使end状态减1，最终减至0
            }
        }
    }

}
