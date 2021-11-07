package org.elsys.ip.chronometer;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        StopWatch s = new StopWatch();
        String id = "asdf";
        s.start(id);
        System.out.println(s.getTimeLaps(id));
        System.out.println(s.getStartTimes(id));
        if(s.getStartTimes(id) == s.getTimeLaps(id)){
            System.out.println("OJ");
        }
        TimeUnit.SECONDS.sleep(4);
        s.lap(id);
        TimeUnit.SECONDS.sleep(3);
        s.stopwatch(id);
        s.lap(id);
        TimeUnit.SECONDS.sleep(3);
        s.stop(id);
    }

}
