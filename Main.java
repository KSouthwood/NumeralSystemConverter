package converter;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String DIGITS = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        String result = "error";
        String input;
        Scanner scanner = new Scanner(System.in);

        input = scanner.next();
        // validate first input as a valid number between 1 - 36
        if (input.matches("[0-9]+")) {
            int srcRadix = Integer.parseInt(input);
            if (srcRadix >= 1 && srcRadix <= 36) {
                // valid source radix found, get number to convert and split at decimal point
                String[] srcNum = scanner.next().split("\\.");
                // convert integer part of number validating it fits the supplied radix
                long base10Integer = toBase10Integer(srcNum[0], srcRadix);
                if (base10Integer != -1) {
                    // number fits the source radix and converted fine, get the target radix and validate it
                    input = scanner.next();
                    if (input.matches("[0-9]+")) {
                        int tgtRadix = Integer.parseInt(input);
                        if (tgtRadix >= 1 && tgtRadix <= 36) {
                            // target radix is validated, convert the number
                            result = toBaseXInteger(base10Integer, tgtRadix);
                            if (srcNum.length == 2) {
                                result += "." + toBaseXFractional(toBase10Fractional(srcNum[1], srcRadix), tgtRadix);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    /**
     * Converts from a number base to decimal (base 10) validating the number fits the radix
     * @param number - string containing a number
     * @param radix - radix of the number (between 1 - 36 inclusive)
     * @return base 10 equivalent of number, -1 otherwise
     */
    private static long toBase10Integer(String number, int radix) {
        long decimal = 0;

        if (radix == 1) {
            return number.length();
        }

        for (int i = number.length() - 1, power = 0; i >= 0; i--) {
            int digit = DIGITS.indexOf(number.charAt(i));
            if (digit >= radix) {
                return -1;
            }
            decimal += digit * Math.pow(radix, power);
            power++;
        }

        return decimal;
    }

    /**
     * Converts from a number base to decimal (base 10)
     * @param number - string containing a number
     * @param radix - source base of the number
     * @return base 10 equivalent of number
     */
    private static double toBase10Fractional(String number, int radix) {
        double result = 0;

        for (int i = 0, power = 1; i < number.length(); ++i, ++power) {
            int digit = DIGITS.indexOf(number.charAt(i));
            result += digit / Math.pow(radix, power);
        }

        return result;
    }

    /**
     * Convert a base 10 number to a specified base X number
     * @param number a base 10 number
     * @param radix radix of base to convert to (1 - 36 inclusive)
     * @return valid String of new number
     */
    private static String toBaseXInteger(long number, int radix) {
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

    /**
     * Convert a fractional base 10 number to a specified base X number to 5 digits
     * @param number fractional part of a base 10 number
     * @param radix base to convert to
     * @return valid String of new number
     */
    private static String toBaseXFractional(double number, int radix) {
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
