package Utility;

import java.util.Scanner;

public class Scan {

    public static Scanner scan = new Scanner(System.in);

    // Methods for reading input without printing
    public static String scan() {
        return scan.next().trim();
    }

    // Methods for output and scanning input
    public static String readLine(String output) {
        System.out.println(output);
        String line = scan.nextLine();
        return line.trim();
    }

    public static int readInt(String output) {
        System.out.println(output);
        int anInt = Integer.parseInt(scan.nextLine().trim()); // to avoid error with new line
        return anInt;
    }

    //system.out.println method
    public static void print(String output){
        System.out.println(output.trim());
    }

    public static void closeScanner() {
        scan.close();
    }

}



