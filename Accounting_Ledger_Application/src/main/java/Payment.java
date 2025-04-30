import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Payment {
    static Scanner scanner = new Scanner(System.in);
    public static void addPayment() {
        // I could have used single try-catch ,but I choose this approach to avoid asking the user to re-enter everything in case of an error

        boolean input = false;
        boolean input2 = false;
        boolean input3 = false;
        boolean input4 = false;
        while (!input) {
            LocalDate date2 = null;
            LocalTime time2 = null;
            float amount = 0;

            while (!input2) try {
                System.out.println("""
                        Please enter the date in the following format (yyyy-M-d)""");
                String date = scanner.nextLine();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                date2 = LocalDate.parse(date, dateTimeFormatter);
                input2 = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please use yyyy-M-d for date.");
            }
            while (!input3) try {
                System.out.println("Please enter the time");
                String time = scanner.nextLine();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
                time2 = LocalTime.parse(time, dtf);
                input3 = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid time format. Please use H:mm for time.");
            }
            System.out.println("Please enter the description");
            String description = scanner.nextLine();
            System.out.println("Please enter the vendor");
            String vendor = scanner.nextLine();
            while (!input4) try {
                System.out.println("Please enter the amount");
                amount = scanner.nextFloat();
                input4 = true;
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid amount entered. Please enter a numerical value.");
                scanner.nextLine();
            }
            TransactionRecord transactionRecord = new TransactionRecord(date2, time2, description, vendor, -amount);
            writingFile(transactionRecord);
            input = true;
            System.out.println("✅ Your payment has been successfully recorded!");
            System.out.println("______________________________________________");
            System.out.println("You paid " + amount + " on " + date2 + " " + time2 + " for " + description + " at " + vendor);
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
        if (!input){
            System.out.println("No payments were found");
        }
    }

}
