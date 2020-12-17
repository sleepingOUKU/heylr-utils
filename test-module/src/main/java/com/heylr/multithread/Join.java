package com.heylr.multithread;

import java.util.concurrent.TimeUnit;

public class Join {
    public static void main(String[] args) throws Exception{
        Thread lastThread = Thread.currentThread();

        for(int i = 0; i< 5;i++){
            Thread curThread =new Thread(new JoinThread(lastThread),"thread-" + i) ;
            curThread.start();
            lastThread = curThread;
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + " end.");
    }

    static class JoinThread implements Runnable{

        private Thread thread;

        JoinThread(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
                System.out.println(Thread.currentThread().getName() + " end.");
            }catch (Exception e){}
        }
    }
}
