package org.elsys.ip.spring.web.timer;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Timer;

@RestController
public class TimerController {
    @GetMapping("/timer/{id}")
    public  String getQuestion(){
        return null;
    }
    @PostMapping("/timer")
    public String postTimer(@RequestBody Map<String, String> resp){
        String res = "";

        return  res;
    }


}
/**
 * Вашата задача е да имплементирате сървър посредством
 * Spring Boot. Сървърът работи като таймер и изповядва следния REST интерфейс:
 *
 *     POST /timer - създаване на таймер
 *     Request body:
 *     { “name” : “name of the timer”,
 *     “hours” : 1,
 *     “minutes” : 5,
 *     “seconds” : 55 }
 *
 *     Или със Request body:
 *     { “name” : “name of the timer”,
 *     “time” : “01:05:55” }
 *
 *     Не може да се подават едновременно
 *     time и някой от параметрите (hours, minutes и seconds). Hours, minutes и seconds
 *     не са задължителни, ако поне един от тях е наличен.
 *
 *     Response body:
 *     { “id”: “<ID>”,
 *     “name” : “name of the timer”,
 *     “time” : “01:05:55” }
 *
 *     Валидация:
 *     При невалиден вход трябва да се връща HTTP status code 400
 *
 *     GET /timer/<id> - извличане на таймер
 *     Response body:
 *     { “id”: “<ID>”,
 *     “name” : “name of the timer”,
 *     “time” : “00:45:07”,
 *     “done” : “no” }
 *
 *     При подаване на query параметър long=true се извършва long polling() на таймера
 *     за 10 секунди, като ако през това време таймера приключи се връща резултат преди
 *     края на периода от 10 секунди. Ако таймера вече е приключил се връща резултат веднага.
 *
 *     Request headers
 *     Всички заявки могат да бъдат придружени от хедър TIME-FORMAT, който може да
 *     съдържа стойности : “time”, “seconds”, “hours-minutes-seconds”. Този хедър
 *     контролира формата на времето в резултата на всички заявки.
 *     При стойност “time” се връща времето във формат hh:mm:ss в атрибут “time”
 *     При стойност “seconds” се връща времето в секунди в атрибут “totalSeconds”
 *     При стойност “hours-minutes-seconds” се връща времето в часове,
 *     минути и секунди в атрибути “hour”, “minutes”, “seconds”
 *     Ако не е подаден такъв хедър се връща като при стойност “time”
 *
 *     Response headers
 *     Всеки резултат от заявка се придружава от хедър ACTIVE-TIMERS, който съдържа броя на всички активни таймери
 *
 * За да тествате вашите решения изпълнете main метода в проекта: https://github.com/Internet
 * -Programming-TUES/elsys-ip-testers с параметри springWeb и пътя до вашия проект.
 * Качвайте проектите си в zip файл, без излишни файлове и папки съдържащи .jar, .iml, .class.
 */