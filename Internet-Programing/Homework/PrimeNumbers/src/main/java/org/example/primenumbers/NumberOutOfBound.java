package org.example.primenumbers;

import java.math.BigInteger;

public class NumberOutOfBound implements Checker{
    @Override
    public void execute(String number) {
        System.out.println(number + " is out of bound");
    }
}
