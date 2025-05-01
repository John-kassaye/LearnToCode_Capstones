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
            LocalDate date = LocalDate.now();
            LocalTime now = LocalTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String formatedTime = now.format(dateTimeFormatter);
            LocalTime time = LocalTime.parse(formatedTime);
            String amount = "";
            double amount1 = 0;

            System.out.println();
            System.out.println("*** Add Deposit ***");
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
                        System.out.println("Amount must be positive");
                    } else {
                        input2 = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid amount entered. Please enter a numerical value.");
                }
            }
            TransactionRecord transactionRecord = new TransactionRecord(date, time, description, vendor, amount1);
            writingFile(transactionRecord);
            input = true;
            System.out.println();
            System.out.println("✅ Your deposit has been successfully recorded ");
            System.out.println("You deposited " + amount + " on " + date + " " + time + " for " + description + " at " + vendor);
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
        Collections.reverse(lists());
        for (TransactionRecord transactionRecord : lists()) {
            if (transactionRecord.getAmount() > 0) {
                System.out.println(transactionRecord);
                input = true;
            }
        }
        if (!input) {
            System.out.println("No deposits were found");
        }
    }

    public static List<TransactionRecord> lists() {
        List<TransactionRecord> transaction = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitting = line.split("\\|");
                LocalDate localDate = LocalDate.parse(splitting[0].trim());
                LocalTime localTime = LocalTime.parse(splitting[1].trim());
                float amount = Float.parseFloat(splitting[4].trim());
                TransactionRecord transactionRecord = new TransactionRecord(localDate, localTime, splitting[2], splitting[3], amount);
                transaction.add(transactionRecord);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return transaction;
    }

}
