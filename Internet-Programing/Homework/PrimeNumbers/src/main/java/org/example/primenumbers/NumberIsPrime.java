package org.example.primenumbers;

import java.math.BigInteger;

public class NumberIsPrime implements Checker{
    @Override
    public void execute(String number) {
        System.out.println(number + " is a prime");
    }
}
