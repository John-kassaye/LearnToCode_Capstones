import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Deposit {
    static Scanner scanner = new Scanner(System.in);

    public static void addDeposit() {

        boolean input = false;
        boolean input2 = false;
        while (!input) {
            // Get the current date and time
            LocalDate date = LocalDate.now();
            LocalTime now = LocalTime.now();

            // Format the time to HH:mm and parse it
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formatedTime = now.format(dateTimeFormatter);
            LocalTime time = LocalTime.parse(formatedTime);

            // Initialize variables for amount input
            String amount = "";
            double amount1 = 0;

            // Prompt user to enter deposit details
            System.out.println();
            System.out.println("*** Add Deposit ***");
            System.out.println( "Current balance: " + String.format(" $%.2f ", Reports.balance()));
            System.out.println();

            System.out.println("Add a description");
            String description = scanner.nextLine();

            System.out.println("Please enter the vendor");
            String vendor = scanner.nextLine();

            while (!input2) {
                try {
                    System.out.println("Please enter the amount");
                    amount = scanner.nextLine();
                    amount1 = Double.parseDouble(amount);
                    if (amount1 <= 0) {
                        System.out.println("Error: Amount must be positive");
                    } else {
                        input2 = true;   // // valid input
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid amount entered. Please enter a number.");
                }
            }

            // Create a transaction record and write it to file
            TransactionRecord transactionRecord = new TransactionRecord(date, time, description, vendor, amount1);
            writingFile(transactionRecord);

            input = true;

            // Show confirmation to the user
            System.out.println();
            System.out.println("✅ Your deposit has been successfully recorded ");
            System.out.println("You deposited $" + amount + " on " + date + " " + time + " for " + description + " at " + vendor);
        }

    }

    public static void writingFile(TransactionRecord transactionRecord) {
        File file = new File("transactions.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(String.valueOf(transactionRecord));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static void onlyDeposit() {
        System.out.println();
        System.out.println("======== Deposit transactions history ========");
        System.out.println();
        System.out.println("Date | Time | Description | Vendor | Amount");
        System.out.println("___________________________________________");

        boolean input = false;

        // Reverse the list so the newest transactions come first
        Collections.reverse(lists());
        for (TransactionRecord transactionRecord : lists()) {
            if (transactionRecord.getAmount() > 0) {
                System.out.println(transactionRecord);
                input = true; // deposit was found
            }
        }

        // If no deposits were found, show a message
        if (!input) {
            System.out.println("No deposits were found");
        }
    }

    public static List<TransactionRecord> lists() {
        // Create a list to store all transaction records
        List<TransactionRecord> transaction = new ArrayList<>();
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"))) {

            while ((line = bufferedReader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) continue;

                String[] splitting = line.split("\\|");

                // Ensure the line has exactly 5 parts
                if (splitting.length != 5) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                // Trim parts
                String dateStr = splitting[0].trim();
                String timeStr = splitting[1].trim();
                String description = splitting[2].trim();
                String merchant = splitting[3].trim();
                double amount = Double.parseDouble(splitting[4].trim());

                LocalDate localDate = LocalDate.parse(dateStr);
                LocalTime localTime = LocalTime.parse(timeStr);

                TransactionRecord transactionRecord = new TransactionRecord(localDate, localTime, description, merchant, amount);
                transaction.add(transactionRecord);
            }

        } catch (IOException e) {
            System.out.println("File not found");
        }

        return transaction;
    }

}
