import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

        LocalDate date2 = LocalDate.parse(date);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
        LocalTime time2 = LocalTime.parse(time, dtf);
        writingFile(date2, time2, description, vendor, amount);

    }

    public static void writingFile(LocalDate date, LocalTime time, String description, String vendor, float amount) {

        File file = new File("transactions.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}