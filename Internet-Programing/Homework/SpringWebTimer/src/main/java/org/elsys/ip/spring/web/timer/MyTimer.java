package org.elsys.ip.spring.web.timer;

import java.util.Timer;

public class MyTimer {
    String id;
    String name;
    Integer hours;
    Integer minutes;
    Integer seconds;
    String time;
    String done;

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public String getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MyTimer() {
    }

    public MyTimer(String name, Integer hours, Integer minutes, Integer seconds) {
        this.name = name;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public MyTimer(String name, String time) {
        this.name = name;
        this.time = time;
    }
}
