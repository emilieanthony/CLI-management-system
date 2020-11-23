package Utility;

import java.util.Scanner;

public class Scan {

    public static Scanner scan = new Scanner(System.in);

    // Methods for reading input without printing
    public static String scan() {
        return scan.next();
    }

    // Methods for output and scanning input
    public static String readLine(String output) {
        System.out.print(output);
        String line = scan.nextLine();
        return line;
    }

    public static int readInt(String output) {
        System.out.println(output);
        int anInt = scan.nextInt();
        return anInt;
    }

    public static double readDouble(String output) {
        System.out.println(output);
        double aDouble = scan.nextDouble();
        return aDouble;
    }

    //system.out.println method
    public static void print(String output){
        System.out.println(output);
    }

    public static void closeScanner() {
        scan.close();
    }

}

