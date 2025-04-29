import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reports {
    static Scanner scanner = new Scanner(System.in);

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

    public static void monthToDate(){

        boolean input = false;
        for (TransactionRecord transactionRecord : lists()) {
            if (transactionRecord.getDate().getMonth() == LocalDate.now().getMonth() &&
                    transactionRecord.getDate().getYear() == LocalDate.now().getYear()){
                System.out.println(transactionRecord);
                input = true;
                break;
            }
        }
        if (!input){
            System.out.println("No transaction were for this month-to-date");
        }
    }

    public static void previousMonth(){

        boolean input = false;
        for (TransactionRecord transactionRecord : lists()){
            if (transactionRecord.getDate().getMonth() == LocalDate.now().getMonth().minus(1) &&
                    transactionRecord.getDate().getYear() == LocalDate.now().getYear()){
                System.out.println(transactionRecord);
                input = true;
                break;
            }
        }
        if (!input){
            System.out.println("No transactions were found for the previous month ");
        }
    }

    public static void yearToDate(){

        boolean input = false;
        for (TransactionRecord transactionRecord : lists()) {
            if (transactionRecord.getDate().getYear() == LocalDate.now().getYear()) {
                System.out.println(transactionRecord);
                input = true;
                break;
            }
        }
        if (!input){
            System.out.println("No transactions were found for this year-to-date");
        }
    }

    public static void previousYear(){

        boolean input = false;
        for (TransactionRecord transactionRecord : lists()) {
            if (transactionRecord.getDate().getYear() == LocalDate.now().minusYears(1).getYear()) {
                System.out.println(transactionRecord);
                input = true;
                break;
            }
        }
        if (!input){
            System.out.println("No transaction were found for the previous year");
        }
    }

    public static void searchByVendor(){
        System.out.println("Please enter the vendor name");
        String vendor = scanner.nextLine();

        boolean input = false;
        for (TransactionRecord transactionRecord : lists()) {
            if (transactionRecord.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transactionRecord);
                input = true;
                break;
            }
        }

        if (!input)
            System.out.println("The vendor you entered was not found.");

    }

    public static Float balance(){
        float result = 0;
        List<TransactionRecord> transactionRecords;
        transactionRecords = Deposit.lists();

        for (int i=0; i<lists().size();i++){
            result+= transactionRecords.get(i).getAmount();
        }

        return result;
    }
}
