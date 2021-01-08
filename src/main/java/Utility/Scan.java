package Utility;

import java.util.Scanner;

public class Scan
{
    public static Scanner scan = new Scanner(System.in);

    // Methods for output and scanning input
    public static String readLine(String output)
    {
        System.out.println(output);
        String line = scan.nextLine();
        return line.trim();
    }

    public static int readInt(String output)
    {
        System.out.println(output);
        int anInt = Integer.parseInt(scan.nextLine().trim());
        return anInt;
    }

    public static void print(String output)
    {
        System.out.println(output.trim());
    }

    public static void closeScanner()
    {
        scan.close();
    }
}



