import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Reports {
    static Scanner scanner = new Scanner(System.in);

    public static void monthToDate() {

        boolean input = false;
        System.out.println("""
                
                ====== This month transactions ======
                -------------------------------------
                """);
        List<TransactionRecord> list = Deposit.lists();
        Collections.reverse(list);
        for (TransactionRecord transactionRecord : list) {
            if (transactionRecord.getDate().getMonth() == LocalDate.now().getMonth() &&
                    transactionRecord.getDate().getYear() == LocalDate.now().getYear()) {
                System.out.println(transactionRecord);
                input = true;
            }
        }
        if (!input) {
            System.out.println("No transaction were for this month-to-date");
        }
    }

    public static void previousMonth() {

        boolean input = false;
        System.out.println("""
                
                ====== Previous Month's Transactions ======
                -------------------------------------------
                """);
        List<TransactionRecord> list = Deposit.lists();
        Collections.reverse(list);
        for (TransactionRecord transactionRecord : list) {
            if (transactionRecord.getDate().getMonth() == LocalDate.now().getMonth().minus(1) &&
                    transactionRecord.getDate().getYear() == LocalDate.now().getYear()) {
                System.out.println(transactionRecord);
                input = true;
            }
        }
        if (!input) {
            System.out.println("No transactions were found for the previous month ");
        }
    }

    public static void yearToDate() {

        boolean input = false;
        System.out.println("""
                
                ====== Year-to-Date Transaction Summary ======
                -------------------------------------------
                """);
        List<TransactionRecord> list = Deposit.lists();
        Collections.reverse(list);
        for (TransactionRecord transactionRecord : list) {
            if (transactionRecord.getDate().getYear() == LocalDate.now().getYear()) {
                System.out.println(transactionRecord);
                input = true;
            }
        }
        if (!input) {
            System.out.println("No transactions were found for this year-to-date");
        }
    }

    public static void previousYear() {

        boolean input = false;
        System.out.println("""
                
                ====== Previous Year Transaction Summary ======
                -------------------------------------------
                """);
        List<TransactionRecord> list = Deposit.lists();
        Collections.reverse(list);
        for (TransactionRecord transactionRecord : list) {
            if (transactionRecord.getDate().getYear() == LocalDate.now().minusYears(1).getYear()) {
                System.out.println(transactionRecord);
                input = true;
            }
        }
        if (!input) {
            System.out.println("No transaction were found for the previous year");
        }
    }

    public static void searchByVendor() {

        System.out.println("""
                
                ====== Search By Vendor Name ======
                -------------------------------------------
                """);
        System.out.println("Please enter the vendor name");
        String vendor = scanner.nextLine();

        boolean input = false;
        List<TransactionRecord> list = Deposit.lists();
        Collections.reverse(list);
        for (TransactionRecord transactionRecord : list) {
            if (transactionRecord.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transactionRecord);
                input = true;
            }
        }

        if (!input)
            System.out.println("The vendor you entered was not found.");

    }

    public static void customSearch() {
        boolean input = false;

        System.out.println("""
                
                ====== Custom Search ======
                ---------------------------
                """);
        System.out.println("Enter the start date below:");
        String startDate = scanner.nextLine();
        LocalDate localDate = LocalDate.parse(startDate);
        System.out.println("Enter the end date below:");
        String endDate = scanner.nextLine();
        LocalDate localDate1 = LocalDate.parse(endDate);
        System.out.println("Enter the description:");
        String description = scanner.nextLine();
        System.out.println("Enter the vendor name:");
        String vendor = scanner.nextLine();
        System.out.println("Enter the amount:");
        String amount = scanner.nextLine();

        List<TransactionRecord> list = Deposit.lists();
        for (TransactionRecord transactionRecord : list) {
            input = true;

            if (!startDate.isEmpty() && transactionRecord.getDate().isBefore(localDate)) {
                input = false;
            }
            if (!startDate.isEmpty() && transactionRecord.getDate().isAfter(localDate1)) {
                input = false;
            }
            if (!description.isEmpty() && !transactionRecord.getDescription().contains(description)) {
                input = false;
            }
            if (!vendor.isEmpty() && !transactionRecord.getVendor().contains(vendor)) {
                input = false;
            }
            if (!amount.isEmpty() && transactionRecord.getAmount() != transactionRecord.getAmount()){
                input = false;
            }

            if (input) {
                System.out.println(transactionRecord);
            }
        }
    }

    public static double balance() {

        double result = 0;
        List<TransactionRecord> transactionRecords;
        transactionRecords = Deposit.lists();

        for (int i = 0; i < Deposit.lists().size(); i++) {
            result += transactionRecords.get(i).getAmount();
        }

        return result;
    }

    public static void balanceSummary() {

        System.out.println("""
                
                ====== Balance Summary ======
                ------------------------------
                """);

        double totalDeposit = 0;
        double totalPayment = 0;

        for (TransactionRecord transactionRecord : Deposit.lists()) {
            if (transactionRecord.getAmount() > 0) {
                totalDeposit += transactionRecord.getAmount();
            } else {
                totalPayment += transactionRecord.getAmount();
            }
        }
        System.out.println("Total deposit:" + totalDeposit);
        System.out.println("Total payment:" + totalPayment);
        System.out.printf("Current balance: %.2f", balance());

    }
}
