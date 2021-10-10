package org.example.primenumbers;

public class NumberIsFloat implements Checker{
    @Override
    public void execute(String number) {
        System.out.println(number + " is not an integer");
    }
}
