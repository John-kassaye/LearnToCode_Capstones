import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Payment {
    static Scanner scanner = new Scanner(System.in);
    public static void addPayment() {
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
                System.out.println("Error: Invalid date or time format. Please use yyyy-MM-dd for date.");
            }
            while (!input3) try {
                System.out.println("Please enter the time");
                String time = scanner.nextLine();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
                time2 = LocalTime.parse(time, dtf);
                input3 = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date or time format. Please use HH:mm for time.");
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
            System.out.println("Your payment is successfully recorded!!");
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
        for (TransactionRecord transactionRecord : lists()) {
            if (transactionRecord.getAmount() < 0) {
                System.out.println(transactionRecord);
            }
        }
    }

    public static List<TransactionRecord> lists() {
        List<TransactionRecord> transaction = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitting = line.split("\\|");
                LocalDate localDate = LocalDate.parse(splitting[0]);
                LocalTime localTime = LocalTime.parse(splitting[1]);
                float amount = Float.parseFloat(splitting[4]);
                TransactionRecord transactionRecord = new TransactionRecord(localDate, localTime, splitting[2], splitting[3], amount);
                transaction.add(transactionRecord);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return transaction;
    }

}
