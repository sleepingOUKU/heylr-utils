package com.heylr.multithread;

import java.util.concurrent.TimeUnit;

public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new WaitThread(), "Wait Thread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(5);
        Thread notifyThread = new Thread(new NotifyThread(),"Notify Thead");
        notifyThread.start();
        TimeUnit.SECONDS.sleep(2);
    }

    static class WaitThread implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                System.out.println("Wait Thread get Lock");
                while (flag){
                    try {
                        System.out.println("Wait Thread start to wait, flag = " + flag);

                        //释放lock，进入等待
                        lock.wait();

                        System.out.println("Wait Thread been Notified, flag = "  + flag);

                    }catch (Exception e){}
                }
            }
        }
    }

    static class NotifyThread implements Runnable{

        @Override
        public void run() {
            synchronized (lock){

                System.out.println("Notify Thread get Lock");

                //唤醒所有等待锁的线程
                lock.notifyAll();
                flag = false;
                System.out.println("Notify Thread notify Lock");
            }
        }
    }
}
