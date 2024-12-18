package main.java.tasks.task3;
/*
. Find the sum of all digits in the result of factorial some number
(i.e. 100 factorial => Correct answer: 648)
 */

import java.math.BigInteger;
import java.util.Scanner;

public class SumOfAllDigitsInFactorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        BigInteger factorialOfNumber = findFactorialOfNumber(number);
        int result = getSumOfDigits(factorialOfNumber);
        System.out.println("Correct answer : " + result);
    }

    private static int getSumOfDigits(BigInteger factorialOfNumber) {
        int sum = 0;
        for (char item : factorialOfNumber.toString().toCharArray()) {
            sum += Integer.parseInt(String.valueOf(item));
        }
        return sum;
    }

    private static BigInteger findFactorialOfNumber(int number) {
        BigInteger factorail = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            factorail = factorail.multiply(BigInteger.valueOf(i));
        }
        return factorail;
    }
}
