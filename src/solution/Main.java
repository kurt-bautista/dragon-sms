package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SMS sms = SMS.getInstance();
        if(args.length == 1) {
            Scanner sc = new Scanner(System.in);
            System.out.println("FILE or CONSOLE?");
            if(sc.nextLine().equalsIgnoreCase("file")) {
                try {
                    sms.setReader(new ReadFile(new File("src/solution/" + args[0])));
                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                    e.printStackTrace();
                }
            }
        }

        while (true) {
            try {
                if(!sms.getReader().hasNext()) sms.setReader(new ReadConsole());
                String output = sms.read();
                if(output.equals("quit")) break;
                System.out.println(output);
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
