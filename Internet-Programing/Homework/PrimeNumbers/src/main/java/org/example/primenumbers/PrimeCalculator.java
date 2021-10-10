package org.example.primenumbers;

import java.math.BigInteger;
import java.util.ArrayList;

public class PrimeCalculator {
    ArrayList<String> symbols = new ArrayList<>();

    public PrimeCalculator(ArrayList<String> symbols) {
        this.symbols = symbols;
    }

    public void calculate(){
        for (String symbol: symbols){
            try{
                BigInteger number = new BigInteger(symbol);
                BigInteger b_max = new BigInteger(String.valueOf(Integer.MAX_VALUE));
                BigInteger b_min = new BigInteger(String.valueOf(Integer.MIN_VALUE));
                if (number.compareTo(b_max) > 0 || number.compareTo(b_min) < 0){
                    new NumberOutOfBound().execute(symbol);
                }else if (number.isProbablePrime(1)){
                    new NumberIsPrime().execute(symbol);
                }else {
                    new NumberIsNotPrime().execute(symbol);
                }
            }
            catch (NumberFormatException ex) {
                try{
                    double numberToDouble = Double.parseDouble(symbol);
                    new NumberIsFloat().execute(symbol);
                }
                catch (NumberFormatException ex1){
                    new NumberIsSymbol().execute(symbol);
                }
            }
        }
    }
}
