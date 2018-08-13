package com.dawndemo;

import org.junit.Test;

/**
 * Created by Chao on 2018/8/13
 */
public class TestVolatile {
    private volatile int inc = 0;
    public void increase() {
        inc++;
    }
    @Test
    private void test(){
        final TestVolatile test = new TestVolatile();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}
