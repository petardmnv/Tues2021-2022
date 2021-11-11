package org.elsys.ip.chronometer;
import org.apache.commons.lang3.time.StopWatch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
//import org.oracle.javatools.util;
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Chronometer chronometer  = new Chronometer();
        String id = chronometer.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(chronometer.stopWatch(id));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(chronometer.startLap(id));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(chronometer.startLap(id));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(chronometer.stop(id));

        String id1 = chronometer.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(chronometer.stopWatch(id1));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(chronometer.stop(id1));


    }
    private static String getMillsecToHHMMSS(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = millis / (1000 * 60 * 60);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}


/*
* Вашата задача е да имплементирате хронометър под формата на Java Servlet с вграден TomCat.

Започва се ново броене с заявка:

    POST stopwatch/start
    201 Created
    Резултатът трябва да е ID-то на създаденото броене

Извличане на изминалото време на стартиран хронометър

    GET stopwatch/<ID>
    200 OK
    Където <ID> e ID-то на броенето, както е върнато от POST заявката
    Резултатът да е изминалото време от създаването на броенето в следния формат hh:mm:ss, пример 00:00:20, което четем като 20 секунди

Маркиране на обиколка:

    PUT stopwatch/<ID>/lap
    200 OK
    Резултатът е сходен с този на GET заявката, но времето, което ще се връща е изминалото време от предната маркирана обиколка (или от началото, ако се маркира първа обиколка)

Спиране на броене:

    DELETE stopwatch/<ID>
    200 OK
    Хронометърът се спира и се връща информация за цялото броене - на колко време е спрян и времето на всяка обиколка, както следва:
    01 00:00:35 / 00:00:35
    02 00:00:08 / 00:00:43
    03 00:00:15 / 00:00:58

Валидиране на потребителски вход:

    При подаване на несъществуващо ID се връща HTTP 404 Not Found
    При подаване на заявка от вида /stopwatch/*, различна от описаните по-горе, да се връща HTTP 400 Bad Request



Пример:
Client: POST stopwatch/start
Server: abc101
Client: GET stopwatch/abc101
Server: 00:00:20
Client: PUT stopwatch/abc101/lap
Server: 00:00:35
Client: PUT stopwatch/abc101/lap
Server: 00:00:08
Client: DELETE stopwatch/abc101
Server: 01 00:00:35 / 00:00:35
             02 00:00:08 / 00:00:43
             03 00:00:15 / 00:00:58


Пример:
Client: POST stopwatch/start
Server: zyx987
Client: DELETE stopwatch/zyx987
Server: 01 00:00:12 / 00:00:12
*
*
*
* */