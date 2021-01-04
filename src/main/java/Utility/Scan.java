package Utility;

import java.util.Scanner;

public class Scan {

    public static Scanner scan = new Scanner(System.in);

    // Methods for reading input without printing
    public static String scan() {
        return scan.next().trim().toLowerCase();
    }

    // Methods for output and scanning input
    public static String readLine(String output) {
        System.out.println(output);
        String line = scan.nextLine();
        return line.trim().toLowerCase();
    }

    public static int readInt(String output) {
        System.out.println(output);
        int anInt = Integer.parseInt(scan.nextLine().trim().toLowerCase()); // to avoid error with new line
        return anInt;
    }

    //system.out.println method
    public static void print(String output){
        System.out.println(output.trim().toLowerCase());
    }

    public static void closeScanner() {
        scan.close();
    }

}



