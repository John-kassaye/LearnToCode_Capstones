import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CLI_Application {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        homeScreen();
    }

    public static void homeScreen() {

        String choice = "";
        do {
            System.out.println("""
                           Welcome to CLI Application
                    D) Add Deposit
                    P) Make Payment(Debit)
                    L) Ledger
                    X) Exit   \s""");
            choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "d":
                    addDeposit();
                    depositDisplay();
                    break;
                case "p":
                    addPayment();
                    paymentDisplay();
                    scanner.nextLine();
                    break;
                    case "x":
                    System.out.println("""
                            Thank you for using the CLI App!!
                            """);
                    break;
            }
        } while (!choice.trim().equalsIgnoreCase("x"));
    }

    public static void depositDisplay(){
        System.out.println("""
                            H) Home
                            x) Exit""");
        String choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("x")){
            System.out.println("""
                            Thank you for using the CLI App!!
                            """);
            System.exit(0);
        }
    }

    public static void addDeposit() {

        boolean input = false;
        while (!input) {
            try {
                System.out.println("""
                        Please enter the date in the following format (yyyy-MM-dd)""");
                String date = scanner.nextLine();
                System.out.println("Please enter the time");
                String time = scanner.nextLine();
                System.out.println("Please enter the description");
                String description = scanner.nextLine();
                System.out.println("Please enter the vendor");
                String vendor = scanner.nextLine();
                System.out.println("Please enter the amount");
                float amount = scanner.nextFloat();
                scanner.nextLine();

                LocalDate date2 = LocalDate.parse(date);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
                LocalTime time2 = LocalTime.parse(time, dtf);
                writingDepositFile(date2, time2, description, vendor, amount);
                input = true;
                System.out.println("Your deposit is successfully recorded");
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date or time format. Please use yyyy-MM-dd for date and HH:mm for time.");
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Invalid amount entered. Please enter a numerical value.");
                scanner.nextLine(); // Clear the invalid input from the scanner
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
    public static void writingDepositFile(LocalDate date, LocalTime time, String description, String vendor, float amount) {

        File file = new File("transactions.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static void addPayment() {
        boolean input = false;
        while (!input){
        try {
            System.out.println("""
                    Please enter the date in the following format (yyyy-MM-dd)""");
            String date = scanner.nextLine();
            System.out.println("Please enter the time");
            String time = scanner.nextLine();
            System.out.println("Please enter the description");
            String description = scanner.nextLine();
            System.out.println("Please enter the vendor");
            String vendor = scanner.nextLine();
            System.out.println("Please enter the amount");
            float amount = scanner.nextFloat();
            scanner.nextLine();

            LocalDate date2 = LocalDate.parse(date);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
            LocalTime time2 = LocalTime.parse(time, dtf);
            writingPaymentFile(date2, time2, description, vendor, amount);
            input = true;
            System.out.println("Your payment is successfully recorded!!");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date or time format. Please use yyyy-MM-dd for date and HH:mm for time.");
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Invalid amount entered. Please enter a numerical value.");
            scanner.nextLine(); // Clear the invalid input from the scanner
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        }
    }
    public static void writingPaymentFile(LocalDate date, LocalTime time, String description, String vendor, float amount) {

        File file = new File("transactions.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(date + "|" + time + "|" + description + "|" + vendor + "|" + -amount + "\n");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static void paymentDisplay(){
        System.out.println("""
                            H) Home
                            x) Exit""");
        String choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("x")){
            System.out.println("""
                            Thank you for using the CLI App!!
                            """);
            System.exit(0);
        }
    }
}