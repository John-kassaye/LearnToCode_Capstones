import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Payment {
    static Scanner scanner = new Scanner(System.in);
    public static void addPayment() {

        boolean input = false;
        boolean input2 = false;
        while (!input) {
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            float amount = 0;

            System.out.println("Please enter the description");
            String description = scanner.nextLine();
            System.out.println("Please enter the vendor");
            String vendor = scanner.nextLine();
            while (!input2) try {
                System.out.println("Please enter the amount");
                amount = scanner.nextFloat();
                input2 = true;
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid amount entered. Please enter a numerical value.");
                scanner.nextLine();
            }
            TransactionRecord transactionRecord = new TransactionRecord(date, time, description, vendor, -amount);
            writingFile(transactionRecord);
            input = true;
            System.out.println("✅ Your payment has been successfully recorded!");
            System.out.println("______________________________________________");
            System.out.println("You paid " + amount + " on " + date + " " + time + " for " + description + " at " + vendor);
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
