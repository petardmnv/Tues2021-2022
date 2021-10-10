package org.example.primenumbers;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> numList = new ArrayList<String>(Arrays.asList(args));
        PrimeCalculator calculator = new PrimeCalculator(numList);
        calculator.calculate();
    }
}
//    Maven проект, който билдва изпълнима Java програма, която чете command line аргументи и изписва на конзолата кои от аргументите са прости числа.
//        Изберете подходящ groupId и artifactId
//        Да билдва .jar файл в /target през “mvn package”
//        Качените файлове са .zip файл съдържащ само pom.xml и src папка със структура от пакети (папки) и .java файлове
//        Пример: java -jar <PATH_TO_JAR> 2 88 4 5 six 7
//        Output:
//        2 is a prime
//        88 is not a prime
//        4 is not a prime
//        5 is a prime
//        six is not a number
//        7 is a prime
//        Поддържат се числа между Integer.MIN_VALUE и Integer.MAX_VALUE, ако числото е по-голямо да се изпише: “X is out of bound”, където Х е подадения аргумент
//        Ако се подаде дробно число да се изпише: “X is not an integer”, където Х е подадения аргумент