
package com.example.backed.service.util;


public class DelaySimulator {

    public static void simulate(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            // ignore
        }
    }

}
