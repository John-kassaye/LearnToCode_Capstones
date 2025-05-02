import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Payment {
    static Scanner scanner = new Scanner(System.in);

    public static void addPayment() {

        boolean input = false;
        boolean input2 = false;

        while (!input) {
            // Get current date and time
            LocalDate date = LocalDate.now();
            LocalTime now = LocalTime.now();

            // Format the time as HH:mm:ss
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formatedTime = now.format(dateTimeFormatter);
            LocalTime time = LocalTime.parse(formatedTime);

            String amount = "";
            double amount1 = 0;

            // Prompt user to enter payment details
            System.out.println();
            System.out.println("*** Make Payment ***");
            System.out.println( "Current balance: " + String.format(" $%.2f ", Reports.balance()));
            System.out.println();

            System.out.println("Add a description:");
            String description = scanner.nextLine();

            System.out.println("Please enter the vendor:");
            String vendor = scanner.nextLine();

            while (!input2) {
                try {
                    System.out.println("Please enter the amount:");
                    amount = scanner.nextLine();
                    amount1 = Double.parseDouble(amount);
                    if (amount1 <= 0) {
                        System.out.println("Error: Amount must be positive");
                    } else {
                        input2 = true; // valid input
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid amount entered. Please enter a numerical value.");
                }

            }

            // Save the payment as a negative amount
            TransactionRecord transactionRecord = new TransactionRecord(date, time, description, vendor, -amount1);
            Deposit.writingFile(transactionRecord);

            input = true;

            // Show confirmation message to user
            System.out.println();
            System.out.println("✅ Your payment has been successfully recorded!");
            System.out.println("You paid $" + amount + " on " + date + " " + time + " for " + description + " at " + vendor);
        }
    }

    public static void onlyPayment() {
        System.out.println("======== Payment transactions history ========");
        System.out.println();
        System.out.println("Date | Time | Description | Vendor | Amount");
        System.out.println("___________________________________________");

        boolean input = false;

        // Reverse the list so the newest transactions come first
        Collections.reverse(Deposit.lists());

        // Loop through the transactions and display only payments (negative amounts)
        for (TransactionRecord transactionRecord : Deposit.lists()) {
            if (transactionRecord.getAmount() < 0) {
                System.out.println(transactionRecord);
                input = true;
            }
        }

        // If no payments were found, show a message
        if (!input) {
            System.out.println("No payments were found");
        }
    }

}
