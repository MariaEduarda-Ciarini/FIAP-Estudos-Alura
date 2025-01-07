package reading;

import java.util.Scanner;

public class DataReading {

    public static void main(String[] args) {

        int age = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = sc.nextLine();

        System.out.println("Enter age: ");
        age = sc.nextInt();

        if (age < 0) {
            System.out.println("Invalid Age");
        } else if (age >= 18 && age < 50) {
            System.out.println("Older Age");
        } else if (age >= 50 && age < 70) {
            System.out.println("Arrived halfway through life");
        } else if (age >= 100) {
            System.out.println("Centenary");
        } else {
            System.out.println("Young Age");
        }

        sc.close();
    }
}
