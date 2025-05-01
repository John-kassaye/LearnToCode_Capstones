import java.io.*;
import java.util.*;
import java.util.Collections;

public class CLI_Application {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        login();
    }

    public static void login() {
        boolean input = false;

        while (!input){System.out.println("""
                
                
                              ======== Welcome to Bank Of America ========

                1) sign in
                2) new customer? sign up
                """);
            String user = scanner.nextLine();

            switch (user) {
                case "1":
                    signInCheck();
                    input = true;
                    break;
                case "2":
                    signUp();
                    System.out.println("""
                        
                        0) Back
                        X) Exit
                        """);
                    String choice = scanner.nextLine();
                    if (choice.trim().equalsIgnoreCase("0")) {
                        login();
                    } else {
                        System.exit(0);
                    }
                    input = true;
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    public static void signUp() {

        System.out.println("Select an option:");
        System.out.println("Enter your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter your Email:");
        String Email = scanner.nextLine();
        System.out.println("Enter a username:");
        String userName = scanner.nextLine();
        System.out.println("Enter a password (at least 4 characters)");
        String password = scanner.nextLine();
        String file = userName + ".csv";
        if (password.length() > 3) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(userName + "," + password);
                writer.newLine();
                System.out.println();
                System.out.println("✅ Sign-up successful! Welcome, " + firstName + " " + lastName + "!");
            } catch (IOException e) {
                System.out.println("Error saving user info.");
            }
        } else {
            System.out.println("Error: Password must be at least 4 character.");
        }

    }

    public static void signInCheck() {

        boolean input = false;
        while (!input) {
            System.out.println("Please enter a user name");
            String userName = scanner.nextLine();
            System.out.println("Please enter a password");
            String password = scanner.nextLine();

            String file = userName + ".csv";
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line = bufferedReader.readLine();
                String[] parts = line.split(",");
                if (parts[0].trim().equals(userName) && parts[1].trim().equals(password)) {
                    homeScreen();
                    input = true;
                }

                if (!input) {
                    System.out.println("Incorrect user name or password");
                }
            } catch (IOException e) {
                System.out.println("Incorrect User name or password");

            }
        }
    }

    public static void homeScreen() {

        String choice = "";
        do {
            System.out.println("""

                    ========== Home ==========
                     Current Balance:""" + String.format(" $%.2f " , Reports.balance()));
            System.out.println("--------------------------");
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
                    System.out.println("Invalid input.");
            }
        } while (!choice.trim().equalsIgnoreCase("x"));
    }

    public static void ledger() {
        String choice;
        System.out.println("""
                
                ======== Ledger Menu ========
                -----------------------------
                
                A) All Entries
                D) Deposits
                P) Payments
                R) Reports
                H) Home
                X) Exit
                
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
                case "x":
                    goodByeDisplay();
                default:
                    System.out.println("Invalid input, please try again.");
                    ledger(); // I could have used a while loop here. just like I did in goBackDisplays
                    break;
            }

    }

    public static void reports() {
        try {
            System.out.println("""
                   
                    ======== Reports Menu ========
                    ------------------------------
                    
                    1) Month To Date
                    2) Previous Month
                    3) Year To Date
                    4) Previous Year
                    5) Search by Vendor
                    6) Custom Search
                    7) Balance Summary
                    0) Back
                    9) Exit
                    
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
                case 6:
                    Reports.customSearch();
                    goBackReports();
                    break;
                case 7:
                    Reports.balanceSummary();
                    goBackReports();
                case 0:
                    ledger();
                    break;
                case 9:
                    goodByeDisplay();
                default:
                    System.out.println("Invalid Input.");
                    reports();

            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number:");
            reports();  // I could have used a while loop here. just like I did in goBackDisplays.
            scanner.nextLine();
        }
    }

    public static void allEntries() {
        System.out.println();
        System.out.println("======== transaction history ========");
        System.out.println();
        System.out.println("Date | Time | Description | Vendor | Amount");
        System.out.println("-------------------------------------------");

        Collections.reverse(Deposit.lists());
        for (TransactionRecord transactionRecord : Deposit.lists()) {
            System.out.println(transactionRecord);
        }
    }

    public static void optionDisplay() {
        boolean input = false;
        while (!input) {System.out.println("""
                
                H) Home
                x) Exit""");
            String choice = scanner.nextLine();

            if (choice.trim().equalsIgnoreCase("h")){
                homeScreen();
                input = true;
            }
            else if (choice.trim().equalsIgnoreCase("x")) {
                System.out.println("""
                    
                    Thank you for using BOA. Have a great day. 🌟
                    """);
                System.exit(0);
            }
            else {
                System.out.println("Invalid input.");
            }
        }
    }

    public static void goBackLedger() {
        boolean input = false;
        while (!input) {
            System.out.println("""
                    
                    h) Home
                    0) Back
                    X) Exit
                    """);
            String choice = scanner.nextLine();
            if (choice.trim().equalsIgnoreCase("0")) {
                ledger();
                input = true;
            } else if (choice.trim().equalsIgnoreCase("H")){
                homeScreen();
                input = true;
            }
            else if (choice.trim().equalsIgnoreCase("x")) {
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
                    
                    H) Home
                    0) Back
                    X) Exit
                    """);
            String choice2 = scanner.nextLine();
                   if (choice2.trim().equalsIgnoreCase("0")) {
                       reports();
                       input1 = true;
                   } else if (choice2.trim().equalsIgnoreCase("H")) {
                       homeScreen();
                       input1 = true;
                   }
                       if (choice2.trim().equalsIgnoreCase("x")) {
                       System.out.println("""
                               
                               Thank you for using the BOA App. 🌟
                               """);
                       System.exit(0);
                       input1 = true;
                   } else {
                       System.out.println("Invalid response.");
                   }
        }
    }

    public static void goodByeDisplay() {
        System.out.println("""
                
                Thank you for using BOA. Have a great day. 🌟
                """);
        System.exit(0);
    }

}




