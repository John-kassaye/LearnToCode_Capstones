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

        while (!input) {
            System.out.println("""
                    
                    
                               ======== Welcome to Bank Of America ========
                    
                    1) sign in
                    2) new customer? sign up
                    X) Exit
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
                case "3":
                    goodByeDisplay();
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    public static void signUp() {

        boolean input = false;
        boolean input1 = false;

        // Initialize variables for email parts and user input
        String[] parts = new String[0];
        String email = "";

        System.out.println("Select an option:");
        System.out.println("Enter your first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name:");
        String lastName = scanner.nextLine();

        while (!input) {
            System.out.println("Enter your Email:");
            email = scanner.nextLine();

            // Split the email into parts based on '@' and '.' characters
            parts = email.trim().split("[@.]");

            if (parts.length > 2) {
                input = true; // Email is valid, exit the loop
            } else {
                System.out.println("Invalid email address");
            }
        }

        while (!input1) {
            System.out.println("Enter a password (at least 4 characters)");
            String password = scanner.nextLine();

            // Create file name based on the email username (before '@')
            String file = parts[0] + ".csv";

            // Check if password length is at least 4 characters
            if (password.length() > 3) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                    writer.write(email + "," + password);
                    writer.newLine();

                    // Show success message
                    System.out.println();
                    System.out.println("✅ Sign-up successful! Welcome, " + firstName + " " + lastName + "!");
                    input1 = true;
                } catch (IOException e) {
                    System.out.println("Error saving user info.");
                }
            } else {
                System.out.println("Error: Password must be at least 4 character.");
            }
        }
    }

    public static void signInCheck() {

        boolean input = false;

        while (!input) {
            System.out.println("Enter your Email");
            String email = scanner.nextLine();

            System.out.println("Enter your password");
            String password = scanner.nextLine();

            // Split the email into parts
            String[] parts = email.trim().split("[@.]");
            String file = parts[0] + ".csv"; // Create the file name based on the email username

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line = bufferedReader.readLine();
                String[] part = line.split(",");// Split the line into email and password parts

                // Check if the email and password match the data in the file
                if (part[0].trim().equalsIgnoreCase(email) && part[1].trim().equals(password)) {
                    homeScreen();
                    input = true;
                }

                // If login is not successful
                if (!input) {
                    System.out.println("Incorrect user name or password");
                }
            } catch (IOException e) {
                // Catch any file-related errors (like file not found) and show error message
                System.out.println("Incorrect User name or password");

            }
        }
    }

    public static void homeScreen() {

        String choice = "";
        do {
            System.out.println("""
                    
                    ========== Home ==========
                     Current Balance:""" + String.format(" $%.2f ", Reports.balance()));
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
                    invalidInput();

            }
        } while (!choice.trim().equalsIgnoreCase("x"));
    }

    public static void ledger() {
        String choice;

        System.out.println("""
                
                *** opening Ledger Menu ***
                
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
                invalidInput();
                ledger(); // I could have used a while loop here. just like I did in goBackDisplays
                break;
        }
    }

    public static void reports() {
        try {
            System.out.println("""
                    
                    *** opening Report Menu ***
                    
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
                    invalidInput();
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

        List<TransactionRecord> list = Deposit.lists();
        Collections.reverse(list);
        for (TransactionRecord transactionRecord : list) {
            System.out.println(transactionRecord);
        }
    }

    public static void optionDisplay() {
        boolean input = false;

        while (!input) {
            System.out.println("""
                    
                    H) Home
                    x) Exit""");
            String choice = scanner.nextLine();

            if (choice.trim().equalsIgnoreCase("h")) {
                homeScreen();
                input = true;
            } else if (choice.trim().equalsIgnoreCase("x")) {
                goodByeDisplay();
                input = true;
            } else {
                invalidInput();
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
            } else if (choice.trim().equalsIgnoreCase("H")) {
                homeScreen();
                input = true;
            } else if (choice.trim().equalsIgnoreCase("x")) {
               goodByeDisplay();
                input = true;
            } else {
                invalidInput();
            }
        }
    }

    public static void goBackReports() {
        boolean input1 = false;

        while (!input1) {
            System.out.println("""
                    
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
                goodByeDisplay();
                input1 = true;
            } else {
                invalidInput();
            }
        }
    }

    public static void goodByeDisplay() {
        System.out.println("""
                
                Thank you for using Smart Ledger App. Have a great day. 🌟
                """);
        System.exit(0);
    }

    public static void invalidInput() {
        // created to avoid writing same invalid input message in multiple methods
        System.out.println("Invalid input. Please try again");
    }

}




