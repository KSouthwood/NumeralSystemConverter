package converter;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String DIGITS = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int    srcRadix = input.nextInt();
        String[] srcNum = input.next().split("\\.");
        int    tgtRadix = input.nextInt();

        String result = fromDecimal(toDecimal(srcNum[0], srcRadix), tgtRadix);

        if (srcNum.length == 2) {
            result += "." + baseXFractional(base10Fractional(srcNum[1], srcRadix), tgtRadix);
        }

        System.out.println(result);
    }

    private static long toDecimal(String number, int radix) {
        long decimal = 0;

        for (int i = number.length() - 1, power = 0; i >= 0; i--) {
            int digit = DIGITS.indexOf(number.charAt(i));
            decimal += digit * Math.pow(radix, power);
            power++;
        }

        return decimal;
    }

    private static double base10Fractional(String number, int radix) {
        double result = 0;

        for (int i = 0, power = 1; i < number.length(); ++i, ++power) {
            int digit = DIGITS.indexOf(number.charAt(i));
            result += digit / Math.pow(radix, power);
        }

        return result;
    }

    private static String fromDecimal(long number, int radix) {
        StringBuilder result = new StringBuilder();

        if (radix == 1) {
            char[] ones = new char[(int) number];
            Arrays.fill(ones, '1');
            result.append(ones);
        } else {
            do {
                long index = number % radix;
                result.insert(0, DIGITS.charAt((int) index));
                number /= radix;
            } while (number != 0);
        }
        return String.valueOf(result);
    }

    private static String baseXFractional(double number, int radix) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            number *= radix;
            int index = ((Double) number).intValue();
            result.append(DIGITS.charAt(index));
            number -= index;
        }

        return String.valueOf(result);
    }
}
