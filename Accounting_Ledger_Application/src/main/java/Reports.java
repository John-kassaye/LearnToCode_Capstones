import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Reports {
    static Scanner scanner = new Scanner(System.in);

    public static void monthToDate() {

        System.out.println("""
                
                ====== This month transactions ======
                -------------------------------------
                """);

        boolean input = false;

        // Load and reverse transaction list to show newest first
        List<TransactionRecord> list = Deposit.lists();
        Collections.reverse(list);

        for (TransactionRecord transactionRecord : list) {
            if (transactionRecord.getDate().getMonth() == LocalDate.now().getMonth() &&
                    transactionRecord.getDate().getYear() == LocalDate.now().getYear()) {
                System.out.println(transactionRecord);
                input = true;
            }
        }

        // If no transactions found for the current month, print message
        if (!input) {
            System.out.println("No transactions were found for this month-to-date");
        }
    }

    public static void previousMonth() {

        System.out.println("""
                
                ====== Previous Month's Transactions ======
                -------------------------------------------
                """);

        boolean input = false;

        // Load and reverse transaction list to show newest first
        List<TransactionRecord> list = Deposit.lists();
        Collections.reverse(list);

        for (TransactionRecord transactionRecord : list) {
            if (transactionRecord.getDate().getMonth() == LocalDate.now().getMonth().minus(1) &&
                    transactionRecord.getDate().getYear() == LocalDate.now().getYear()) {
                System.out.println(transactionRecord);
                input = true;
            }
        }

        // If no transactions found for the previous month, print message
        if (!input) {
            System.out.println("No transactions were found for the previous month ");
        }
    }

    public static void yearToDate() {

        System.out.println("""
                
                ====== Year-to-Date Transaction Summary ======
                -------------------------------------------
                """);

        boolean input = false;

        // Load and reverse transaction list to show newest first
        List<TransactionRecord> list = Deposit.lists();
        Collections.reverse(list);

        for (TransactionRecord transactionRecord : list) {
            if (transactionRecord.getDate().getYear() == LocalDate.now().getYear()) {
                System.out.println(transactionRecord);
                input = true;
            }
        }

        // If no transactions found for the current year, print message
        if (!input) {
            System.out.println("No transactions were found for this year-to-date");
        }
    }

    public static void previousYear() {

        System.out.println("""
                
                ====== Previous Year Transaction Summary ======
                -------------------------------------------
                """);

        boolean input = false;

        // Load and reverse transaction list to show newest first
        List<TransactionRecord> list = Deposit.lists();
        Collections.reverse(list);

        for (TransactionRecord transactionRecord : list) {
            if (transactionRecord.getDate().getYear() == LocalDate.now().minusYears(1).getYear()) {
                System.out.println(transactionRecord);
                input = true;
            }
        }

        // If no transactions found for the current year, print message
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

        // Load and reverse transaction list to show newest first
        List<TransactionRecord> list = Deposit.lists();
        Collections.reverse(list);

        for (TransactionRecord transactionRecord : list) {
            if (transactionRecord.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transactionRecord);
                input = true;
            }
        }

        // If no transactions found for the current year, print message
        if (!input)
            System.out.println("The vendor you entered was not found.");

    }

    public static void customSearch() {

        System.out.println("""
                
                ====== Custom Search ======
                ---------------------------
                """);

        boolean validDate = false;
        boolean validDate1 = false;

        String startDate = "";
        LocalDate localDate = null;
        String endDate = "";
        LocalDate localDate1 = null;

        while (!validDate) {
            try {
                System.out.println("Enter the start date below:");
                startDate = scanner.nextLine();
                localDate = LocalDate.parse(startDate);
                validDate = true; //If successful, set validDate to true to exit the loop
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }

        while (!validDate1) {
            try {
                System.out.println("Enter the end date below:");
                endDate = scanner.nextLine();
                localDate1 = LocalDate.parse(endDate);
                validDate1 = true; // If successful, set validDate1 to true to exit the loop
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter the date in yyyy-MM-dd format.");
            }
        }

        System.out.println("Enter the description:");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor name:");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount:");
        String amount = scanner.nextLine();

        Double amount1 = null;
        if (!amount.isEmpty()) {
            try {
                amount1 = Double.parseDouble(amount);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount entered. Skipping amount filter.");
            }
        }

        boolean input1 = false;

        List<TransactionRecord> list = Deposit.lists();
        for (TransactionRecord transactionRecord : list) {
            boolean input = true;

            // Check if the transaction's date is after the start date
            if (!startDate.isEmpty() && transactionRecord.getDate().isBefore(localDate)) {
                input = false;
            }
            // Check if the transaction's date is before the end date
            if (!endDate.isEmpty() && transactionRecord.getDate().isAfter(localDate1)) {
                input = false;
            }
            // Check if the transaction description contains the user's input
            if (!description.isEmpty() && !transactionRecord.getDescription().contains(description)) {
                input = false;
            }
            // Check if the transaction vendor matches the user's input
            if (!vendor.isEmpty() && !transactionRecord.getVendor().contains(vendor)) {
                input = false;
            }
            // Check if the transaction amount matches the entered amount
            if (amount1 != null && transactionRecord.getAmount() != amount1) {
                input = false;
            }

            // If the transaction matches all criteria, print it
            if (input) {
                System.out.println(transactionRecord);
                input1 = true;
            }
        }

        // If no matching transactions were found, display a message
        if (!input1) {
            System.out.println("No matching transactions were found.");
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
