package converter;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int    srcRadix = input.nextInt();
        String srcNum   = input.next().toLowerCase();
        int    tgtRadix = input.nextInt();

        System.out.println(fromDecimal(toDecimal(srcNum, srcRadix), tgtRadix));
    }

    private static long toDecimal(String number, int radix) {
        String digits = "0123456789abcdefghijklmnopqrstuvwxyz";
        long decimal = 0;

        for (int i = number.length() - 1, power = 0; i >= 0; i--) {
            int digit = digits.indexOf(number.charAt(i));
            decimal += digit * Math.pow(radix, power);
            power++;
        }

        return decimal;
    }

    private static String fromDecimal(long number, int radix) {
        StringBuilder result = new StringBuilder();

        if (radix == 1) {
            char[] ones = new char[(int) number];
            Arrays.fill(ones, '1');
            result.append(ones);
        } else {
            String digits = "0123456789abcdefghijklmnopqrstuvwxyz";

            do {
                long index = number % radix;
                result.insert(0, digits.charAt((int) index));
                number /= radix;
            } while (number != 0);
        }
        return String.valueOf(result);
    }
}
