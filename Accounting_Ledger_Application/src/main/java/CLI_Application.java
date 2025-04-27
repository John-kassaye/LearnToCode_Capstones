import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                    optionDisplay();
                    break;
                case "p":
                    addPayment();
                    optionDisplay();
                    break;
                case "l":
                    ledger();
                    optionDisplay();
                    break;
                case "x":
                    goodByeDisplay();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (!choice.trim().equalsIgnoreCase("x"));
    }

    public static void optionDisplay(){
        System.out.println("""
                            H) Home        x) Exit""");
        String choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("x")){
            System.out.println("""
                            Thank you for using the CLI App!!
                            """);
            System.exit(0);
        }
    }

    public static void goodByeDisplay(){
        System.out.println("""
                            Thank you for using the CLI App!!
                            """);
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

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy-M-d");
                LocalDate date2 = LocalDate.parse(date, dateTimeFormatter);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
                LocalTime time2 = LocalTime.parse(time, dtf);
//                writingDepositFile(date2, time2, description, vendor, amount);
                TransactionRecord transactionRecord = new TransactionRecord(date2,time2,description,vendor,amount);
                writingDepositFile(transactionRecord);
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
//    public static void writingDepositFile(LocalDate date, LocalTime time, String description, String vendor, float amount) {

    public static void writingDepositFile(TransactionRecord transactionRecord) {
        File file = new File("transactions.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(String.valueOf(transactionRecord));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static void addPayment() {
        boolean input = false;
        while (!input){
        try {
            System.out.println("""
                    Please enter the date in the following format (yy-M-dd)""");
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

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy-M-d");
            LocalDate date2 = LocalDate.parse(date, dateTimeFormatter);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
            LocalTime time2 = LocalTime.parse(time, dtf);
            TransactionRecord transactionRecord = new TransactionRecord(date2,time2,description,vendor,amount);
//            writingPaymentFile(date2, time2, description, vendor, amount);
            writingPaymentFile(transactionRecord);
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
//    public static void writingPaymentFile(LocalDate date, LocalTime time, String description, String vendor, float amount) {

    public static void writingPaymentFile(TransactionRecord transactionRecord) {
        File file = new File("transactions.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(String.valueOf(transactionRecord));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static void ledger(){
        String choice;
        System.out.println("""
                A) All Entries
                D) Deposits
                P) Payments
                R) Reports  \s""");
        choice = scanner.nextLine();
        switch (choice.toLowerCase()){
            case "a":
                allEntries();
                break;
            case "d":
                onlyDeposit();
                break;
            case "p":
                onlyPayment();
                break;
            default:
                System.out.println("Invalid");
        }
    }

    public static void allEntries(){
        String line;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"))) {
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static List<String> lists(){
        List<String> transaction = new ArrayList<>();
        String line;
        try(BufferedReader bufferedReader =new BufferedReader(new FileReader("transactions.csv"))){
            while ((line = bufferedReader.readLine()) != null){
                transaction.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return transaction;
    }

    public static void onlyDeposit(){
        List<String> list = lists();
        for (int i = 0; i < list.size(); i++){
            String string = list.get(i);
            String[] deposit = string.split("\\|");
            LocalDate date = LocalDate.parse(deposit[0]);
            LocalTime time = LocalTime.parse(deposit[1]);
            String description = deposit[2];
            String vendor = deposit[3];
            float amount = Float.parseFloat(deposit[4]);

            if (amount > 0){
                System.out.println(Arrays.toString(deposit));
            }
        }
    }

    public static void onlyPayment(){
        List<String> list = lists();
        for (int i = 0; i < list.size(); i++){
            String string = list.get(i);
            String[] payment = string.split("\\|");
            LocalDate date = LocalDate.parse(payment[0]);
            LocalTime time = LocalTime.parse(payment[1]);
            String description = payment[2];
            String vendor = payment[3];
            float amount = Float.parseFloat(payment[4]);

            if (amount < 0){
//                System.out.println(Arrays.toString(payment));
                System.out.println();
            }
        }
    }

    public static void reports(){
        System.out.println("""
                1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                H) Home
                """);
    }
}