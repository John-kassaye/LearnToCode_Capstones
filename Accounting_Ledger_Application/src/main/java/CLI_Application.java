import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CLI_Application {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        login();
    }

    public static void homeScreen() {

        String choice = "";
        do {
            System.out.println("""

                    ======== Welcome to Bank Of America ========
                     Current Balance:""" + String.format("%.2f" , Reports.balance()));
            System.out.println("-------------------------------------");
            System.out.println("""
                     D) Add Deposit
                     P) Make Payment (Debit)
                     L) Ledger
                     X) Exit
                     
                     Select an option:""");
            choice = scanner.nextLine().trim();

            switch (choice.toLowerCase()) {
                case "d":
                    Deposit.addDeposit();
                    optionDisplay();
                    break;
                case "p":
                    Payment.addPayment();
                    optionDisplay();
                    break;
                case "l":
                    ledger();
                    break;
                case "x":
                    goodByeDisplay();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (!choice.trim().equalsIgnoreCase("x"));
    }

    public static void optionDisplay() {
        System.out.println("""
                
                H) Home
                x) Exit""");
        String choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("x")) {
            System.out.println("""
                   
                    Thank you for using BOA. Have a great day 🌟
                    """);
            System.exit(0);
        }
    }

    public static void goodByeDisplay() {
        System.out.println("""
                
                Thank you for using BOA. Have a great day 🌟
                """);
        System.exit(0);
    }

    public static void ledger() {
        String choice;
        boolean input = false;
        System.out.println("""
                
                ======== Ledger Menu ========
                -----------------------------
                
                A) All Entries
                D) Deposits
                P) Payments
                R) Reports
                H) Home
                
                Select an option:""");
        choice = scanner.nextLine();
            switch (choice.trim().toLowerCase()) {
                case "a":
                    allEntries();
                    goBackLedger();
                    break;
                case "d":
                    Deposit.onlyDeposit();
                    goBackLedger();
                    break;
                case "p":
                    Payment.onlyPayment();
                    goBackLedger();
                    break;
                case "r":
                    reports();
                    break;
                case "h":
                    homeScreen();
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    ledger();
                    break;
            }

    }
    public static void allEntries() {
        System.out.println();
        System.out.println("======== transaction history ========");
        System.out.println();
        System.out.println("Date | Time | Description | Vendor | Amount");
        System.out.println("_________________________________________");
        for (TransactionRecord transactionRecord : Deposit.lists()) {
            System.out.println(transactionRecord);
        }
    }

    public static void reports() {
        boolean input = false;
        while (!input) try {
            System.out.println("""
                    ======== Reports Menu ========
                    ------------------------------
                    
                    1) Month To Date
                    2) Previous Month
                    3) Year To Date
                    4) Previous Year
                    5) Search by Vendor
                    0) Back
                    
                    Select an option:""");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Reports.monthToDate();
                    goBackReports();
                    break;
                case 2:
                    Reports.previousMonth();
                    goBackReports();
                    break;
                case 3:
                    Reports.yearToDate();
                    goBackReports();
                    break;
                case 4:
                    Reports.previousYear();
                    goBackReports();
                    break;
                case 5:
                    Reports.searchByVendor();
                    goBackReports();
                    break;
                case 0:
                    ledger();
                    input = true;
                    break;

            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number");
            scanner.nextLine();
        }
    }

    public static void goBackLedger() {
        boolean input = false;
        while (!input) {
            System.out.println("""
                    
                    0) Back
                    X) Exit
                    """);
            String choice = scanner.nextLine();
            if (choice.trim().equalsIgnoreCase("0")) {
                ledger();
                input = true;
            } else if (choice.trim().equalsIgnoreCase("x")) {
                System.out.println("""
                        
                        Thank you for using the CLI App!!
                        """);
                System.exit(0);
                input = true;
            } else {
                System.out.println("Invalid input. please try again");
            }
        }
    }

    public static void goBackReports() {
        boolean input1 = false;
        while (!input1) {System.out.println("""
                    
                    0) Back
                    X) Exit
                    """);
            String choice2 = scanner.nextLine();
                   if (choice2.trim().equalsIgnoreCase("0")) {
                       reports();
                       input1 = true;
                   } else if (choice2.trim().equalsIgnoreCase("x")) {
                       System.out.println("""
                               
                               Thank you for using the BOA App!!
                               """);
                       System.exit(0);
                       input1 = true;
                   } else {
                       System.out.println("Invalid response");
                   }
        }
    }

    public static void startDate() {
        System.out.println("Start Date\": The beginning date for the search.(yyyy-MM-dd)");
        String date = scanner.nextLine();
        LocalDate userDate = LocalDate.parse(date);

        for (TransactionRecord transactionRecord : Deposit.lists()) {
            if (userDate.isAfter(transactionRecord.getDate())) {
                System.out.println(transactionRecord);
            }
        }
    }

    public static void endDate(){
        System.out.println("End Date: The ending date for the search.(yyyy-MM-dd)");
        String date = scanner.nextLine();
        LocalDate userDate = LocalDate.parse(date);

        for (TransactionRecord transactionRecord : Deposit.lists()) {
            if (userDate.isAfter(transactionRecord.getDate())) {
                System.out.println(transactionRecord);
            }
        }
    }

    public static void description(){
        System.out.println("Enter the description for the search");
        String description = scanner.nextLine();

        for (TransactionRecord transactionRecord : Deposit.lists()){
            if (transactionRecord.getDescription().contains(description)){
                System.out.println(transactionRecord);
            }
        }
    }

    public static void vendor(){
        System.out.println("Enter the vendor name for the search");
        String vendor = scanner.nextLine();

        for (TransactionRecord transactionRecord : Deposit.lists()){
            if (transactionRecord.getVendor().contains(vendor)){
                System.out.println(transactionRecord);
            }
        }
    }

    public static void amount(){
        System.out.println("Enter the amount for the search");
        float amount = scanner.nextFloat();

        for (TransactionRecord transactionRecord : Deposit.lists()){
            if (transactionRecord.getAmount()==amount){
                System.out.println(transactionRecord);
            }
        }
    }

    public static void login() {
        boolean input = false;
        while (!input) {
            System.out.println("""
                                        ======== Welcome to Bank Of America ========

                    """);
            System.out.println("User name:");
            System.out.println("Password:");
            String name = scanner.nextLine();
            int password = scanner.nextInt();
            scanner.nextLine();

            if (name.trim().equals("Year up") && password == 1234) {
                input = true;
            } else {
                System.out.println("Incorrect username and password");
                input = false;
                login();
            }
        }

        if (input == true){
            homeScreen();
        }
    }

    public static void signUp(){
        System.out.println("Please enter a user name");
        String userName = scanner.nextLine();
        System.out.println("Please enter a password");
        int password = scanner.nextInt();
        scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("signInFile.csv", true))) {
            writer.write(userName + "," + password);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public static List<SignIn> signUpFile(){
        List<SignIn> signUp = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("signInFile.csv"))) {

            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(",");
                String userName = parts[0];
                int password = Integer.parseInt(parts[1]);
                SignIn signIn = new SignIn(userName,password);
                signUp.add(signIn);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return signUp;
    }

}




