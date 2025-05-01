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
            LocalDate date = LocalDate.now();
            LocalTime now = LocalTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String formatedTime = now.format(dateTimeFormatter);
            LocalTime time = LocalTime.parse(formatedTime);
            String amount = "";
            double amount1 = 0;

            System.out.println();
            System.out.println("*** Make Payment ***");
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
                        System.out.println("Amount must be positive");
                    } else {
                        input2 = true;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid amount entered. Please enter a numerical value.");
                }
            }

            TransactionRecord transactionRecord = new TransactionRecord(date, time, description, vendor, -amount1);
            Deposit.writingFile(transactionRecord);
            input = true;
            System.out.println();
            System.out.println("✅ Your payment has been successfully recorded!");
            System.out.println("You paid " + amount + " on " + date + " " + time + " for " + description + " at " + vendor);
        }
    }

    public static void onlyPayment() {
        System.out.println("======== Payment transactions history ========");
        System.out.println();
        System.out.println("Date | Time | Description | Vendor | Amount");
        System.out.println("___________________________________________");
        boolean input = false;
        Collections.reverse(Deposit.lists());
        for (TransactionRecord transactionRecord : Deposit.lists()) {
            if (transactionRecord.getAmount() < 0) {
                System.out.println(transactionRecord);
                input = true;
            }
        }
        if (!input) {
            System.out.println("No payments were found");
        }
    }

}
