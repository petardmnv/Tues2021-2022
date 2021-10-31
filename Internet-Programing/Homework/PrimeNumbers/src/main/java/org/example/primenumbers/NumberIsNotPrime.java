package org.example.primenumbers;

import java.math.BigInteger;

public class NumberIsNotPrime implements Checker{
    @Override
    public void execute(String number) {
        System.out.println(number + " is not a prime");
    }
}
