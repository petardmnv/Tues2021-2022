package org.example.primenumbers;

public class NumberIsSymbol implements Checker{
    @Override
    public void execute(String number) {
        System.out.println(number + " is not a number");
    }
}
