package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        long decimal = input.nextLong();
        int  radix   = input.nextInt();
        String number = Long.toString(decimal, radix);
        String prefix;
        switch (radix) {
            case 2:
                prefix = "0b";
                break;
            case 8:
                prefix = "0";
                break;
            case 16:
                prefix = "0x";
                break;
            default:
                prefix = "";
                break;
        }

        System.out.println(prefix + number);
    }
}
