package com.bautista.kurt;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SMS sms = new SMS();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            if (s.trim().equalsIgnoreCase("quit")) break;
            try {
                System.out.println(sms.send(s));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println();
            } catch (RuntimeException e) {
                System.out.println("Invalid command/parameters.");
                System.out.printf("Error message: %s\n", e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }
    }
}
