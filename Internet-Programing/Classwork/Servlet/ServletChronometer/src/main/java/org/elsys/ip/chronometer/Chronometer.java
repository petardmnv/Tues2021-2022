package org.elsys.ip.chronometer;

import org.apache.commons.lang3.time.StopWatch;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Chronometer {
    private Map<String, ArrayList<String>> timeTable = new HashMap<>();
    private Map<String, StopWatch> watches = new HashMap<>();
    private Map<String, StopWatch> laps = new HashMap<>();
    public String start(){
        String id = generateId();
        StopWatch s = new StopWatch();
        StopWatch l = new StopWatch();
        watches.put(id, s);
        watches.get(id).start();
        laps.put(id, l);
        ArrayList<String> results = new ArrayList<>();
        timeTable.put(id, results);
        return id;
    }
    public String startLap(String id){
        if(!keyExistence(id)){
            return null;
        }
        long currentTime = 0;
        if (laps.get(id).isStarted()){
           currentTime = laps.get(id).getTime();
            laps.get(id).reset();
        }else{
            currentTime = watches.get(id).getTime();
        }
        addToTimeTable(id, currentTime);
        laps.get(id).start();
        return getMilliToHHMMSS(currentTime);
    }
    public String stop(String id){
        if(!keyExistence(id)){
            return null;
        }
        long currentTime = 0;
        if (laps.get(id).isStarted()){
            currentTime = laps.get(id).getTime();
            laps.get(id).stop();
        }else{
            currentTime = watches.get(id).getTime();
        }
        addToTimeTable(id, currentTime);
        watches.get(id).stop();
        watches.remove(id);
        laps.remove(id);
        return printTimeTable(id);
    }

    private void addToTimeTable(String id, long currentTime) {
        if (timeTable.containsKey(id)){
            timeTable.get(id).add(getMilliToHHMMSS(currentTime) + " / " +getMilliToHHMMSS(watches.get(id).getTime()));
        }else {
            ArrayList<String> results = new ArrayList<>();
            results.add(getMilliToHHMMSS(currentTime) + " / " +getMilliToHHMMSS(watches.get(id).getTime()));
            timeTable.put(id, results);
        }
    }

    public String stopWatch(String id){
        if(!keyExistence(id)){
            return null;
        }
        return getMilliToHHMMSS(watches.get(id).getTime());
    }
    public boolean keyExistence(String id){
        if(watches.containsKey(id)){
            return true;
        }
        return false;
    }
    public String generateId(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    public String getMilliToHHMMSS(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = millis / (1000 * 60 * 60);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    public String printTimeTable(String id) {
        ArrayList<String> template = new ArrayList<>();
        int counter = 1;
        for (String e : timeTable.get(id)) {
            template.add(String.format("%02d %s", counter, e));
            counter ++;
        }
        return  String.join("\n", template);
    }
}
