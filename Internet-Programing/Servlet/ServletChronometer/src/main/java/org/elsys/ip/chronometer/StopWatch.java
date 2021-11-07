package org.elsys.ip.chronometer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StopWatch {
    private Map<String, Map<String, String>> timestamps = new HashMap<>();
    private Map<String, Integer> startTimes = new HashMap<>();
    private Map<String, Integer> timeLaps = new HashMap<>();

    private String getBeautifulTime(int seconds) {
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));
    }

    public int getCurrentTime(){
        LocalDateTime date = LocalDateTime.now();
        return date.toLocalTime().toSecondOfDay();
    }
    public void start(String id){
        int seconds = getCurrentTime();
        startTimes.put(id, seconds);
        timeLaps.put(id, seconds);
    }
    public void stopwatch(String id){
        System.out.println(getBeautifulTime(getCurrentTime() - startTimes.get(id)));
    }
    public void lap(String id){
        int newLapTime = getCurrentTime();
        int lapTimeDiff =newLapTime - timeLaps.get(id);
        timeLaps.replace(id, newLapTime);
        Map<String, String> map = new HashMap<>();
        map.put(getBeautifulTime(lapTimeDiff), getBeautifulTime(newLapTime));
        timestamps.put(id, map);
    }

    public void stop(String id){
        Map<String, String> map = new HashMap<>();
        if(getTimeLaps(id) == getStartTimes(id)){
            int diff = getCurrentTime() - startTimes.get(id);
            map.put(getBeautifulTime(diff), getBeautifulTime(diff));
        }else {
            int newLapTime = getCurrentTime();
            int lapTimeDiff =newLapTime - timeLaps.get(id);
            map.put(getBeautifulTime(lapTimeDiff), getBeautifulTime(newLapTime));
        }
        timestamps.put(id, map);
        System.out.println(printTimestamps(id));
        timestamps.remove(id);
        startTimes.remove(id);
        timeLaps.remove(id);
    }

    public String printTimestamps(String id) {
        ArrayList<String> result = new ArrayList<>();
        int counter = 1;
        for (Map.Entry<String,String> e : timestamps.get(id).entrySet()) {
            //System.out.println("KEY: " + e.getKey() + " VALUE:" + e.getValue());
            result.add(String.format("%02d %s / %s", counter, e.getKey(), e.getValue()));
            counter ++;
        }
        return  String.join("\n", result);
    }

    public int getStartTimes(String id) {
        return startTimes.get(id);
    }

    public int getTimeLaps(String id) {
        return timeLaps.get(id);
    }
}
