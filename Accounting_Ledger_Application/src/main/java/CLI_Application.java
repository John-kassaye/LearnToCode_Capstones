import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class CLI_Application {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

//        homeScreen();
        startDate();

    }

    public static void homeScreen() {

        String choice = "";
        do {
            System.out.println("""
                           Welcome to CLI Application
                    D) Add Deposit
                    P) Make Payment(Debit)
                    L) Ledger
                    X) Exit""");
            choice = scanner.nextLine().trim();

            switch (choice.toLowerCase()) {
                case "d":
                    addDeposit();
                    optionDisplay();
                    break;
                case "p":
                    addPayment();
                    optionDisplay();
                    break;
                case "l":
                    ledger();
                    break;
                case "x":
                    goodByeDisplay();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (!choice.trim().equalsIgnoreCase("x"));
    }

    public static void optionDisplay() {
        System.out.println("""
                H) Home        x) Exit""");
        String choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("x")) {
            System.out.println("""
                    Thank you for using the CLI App!!
                    """);
            System.exit(0);
        }
    }

    public static void goodByeDisplay() {
        System.out.println("""
                Thank you for using the CLI App!!
                """);
        System.exit(0);
    }

    public static void addDeposit() {

        boolean input = false;
        boolean input2 = false;
        boolean input3 = false;
        boolean input4 = false;
        while (!input) {
            LocalDate date2 = null;
            LocalTime time2 = null;
            Float amount = null;
            while (!input2) try {
                System.out.println("""
                        Please enter the date in the following format (yyyy-MM-dd)""");
                String date = scanner.nextLine();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                date2 = LocalDate.parse(date, dateTimeFormatter);
                input2 = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date or time format. Please use yyyy-MM-dd for date and HH:mm for time.");
            }
            while (!input3) try {
                System.out.println("Please enter the time");
                String time = scanner.nextLine();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
                time2 = LocalTime.parse(time, dtf);
                input3 = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid time format. Please use HH:mm for time.");
            }
            System.out.println("Please enter the description");
            String description = scanner.nextLine();
            System.out.println("Please enter the vendor");
            String vendor = scanner.nextLine();
            while (!input4) try {
                System.out.println("Please enter the amount");
                amount = scanner.nextFloat();
                input4 = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid amount entered. Please enter a numerical value.");
                scanner.nextLine();
            }
            TransactionRecord transactionRecord = new TransactionRecord(date2, time2, description, vendor, amount);
            writingDepositFile(transactionRecord);
            input = true;
            System.out.println("Your deposit is successfully recorded");
        }

    }

    public static void writingDepositFile(TransactionRecord transactionRecord) {
        File file = new File("transactions.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(String.valueOf(transactionRecord));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

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
            writingPaymentFile(transactionRecord);
            input = true;
            System.out.println("Your payment is successfully recorded!!");
        }
    }

    public static void writingPaymentFile(TransactionRecord transactionRecord) {
        File file = new File("transactions.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(String.valueOf(transactionRecord));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static void ledger() {
        String choice;
         System.out.println("""
                A) All Entries
                D) Deposits
                P) Payments
                R) Reports
                H) Home""");
        choice = scanner.nextLine();
        switch (choice.trim().toLowerCase()) {
            case "a":
                allEntries();
                goBackLedger();
                break;
            case "d":
                onlyDeposit();
                goBackLedger();
                break;
            case "p":
                onlyPayment();
                goBackLedger();
                break;
            case "r":
                reports();
                break;
            case "h":
                homeScreen();
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
    }

    public static List<String> lists() {
        List<String> transaction = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"))) {
            while ((line = bufferedReader.readLine()) != null) {
                transaction.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return transaction;
    }

    public static void allEntries() {
        List<String> transaction = lists();
        for (String example : transaction) {
            System.out.println(example);
        }
    }

    public static void onlyDeposit() {
        List<String> list = lists();
        for (int i = 0; i < list.size(); i++) {
            String string = list.get(i);
            String[] deposit = string.split("\\|");
            LocalDate date = LocalDate.parse(deposit[0]);
            LocalTime time = LocalTime.parse(deposit[1]);
            String description = deposit[2];
            String vendor = deposit[3];
            float amount = Float.parseFloat(deposit[4]);

            if (amount > 0) {
                System.out.println(Arrays.toString(deposit));
            }
        }
    }

    public static void onlyPayment() {
        List<String> list = lists();
        for (int i = 0; i < list.size(); i++) {
            String string = list.get(i);
            String[] payment = string.split("\\|");
            LocalDate date = LocalDate.parse(payment[0]);
            LocalTime time = LocalTime.parse(payment[1]);
            String description = payment[2];
            String vendor = payment[3];
            float amount = Float.parseFloat(payment[4]);

            if (amount < 0) {
                System.out.println(Arrays.toString(payment));
            }
        }
    }

    public static void reports() {
        boolean input = false;
        while (!input) try{ System.out.println("""
                1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                0) Back""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1:
                monthToDate();
                goBackReports();
                break;
            case 2:
                previousMonth();
                goBackReports();
                break;
            case 3:
                yearToDate();
                goBackReports();
                break;
            case 4:
                previousYear();
                goBackReports();
                break;
            case 5:
                search();
                goBackReports();
                break;
            case 0:
                ledger();
                input = true;
                break;


        }} catch (InputMismatchException e){
            System.out.println("Please enter a number");
            scanner.nextLine();
        }
    }

    public static void monthToDate(){
        List<String> monthToDate = lists();
       for (int i = 0; i < lists().size(); i++){
           String dateMonth = lists().get(i);
           String[] split = dateMonth.split("\\|");
           String date2 = split[0];
           LocalDate date = LocalDate.now();
           Month month = date.getMonth();
           int year = date.getYear();
           LocalDate listDate = LocalDate.parse(date2);;

           if (listDate.getMonth()==month && listDate.getYear() == year){
               System.out.println(lists().get(i));
           }

       }
    }

    public static void previousMonth(){
        List<String> monthToDate = lists();
        for (int i = 0; i < lists().size(); i++) {
            String dateMonth = lists().get(i);
            String[] split = dateMonth.split("\\|");
            String date2 = split[0];
            LocalDate date = LocalDate.now();
            Month month = date.minusMonths(1).getMonth();
            int year = date.getYear();
            LocalDate listDate = LocalDate.parse(date2);;

            if (listDate.getMonth() == month && listDate.getYear()==year) {
                System.out.println(lists().get(i));
            }

        }
    }

    public static void yearToDate(){
        List<String> monthToDate = lists();
        for (int i = 0; i < lists().size(); i++){
            String dateMonth = lists().get(i);
            String[] split = dateMonth.split("\\|");
            String date2 = split[0];
            LocalDate date = LocalDate.now();
            int year = date.getYear();
            LocalDate listDate = LocalDate.parse(date2);

            if (listDate.getYear() == year){
                System.out.println(lists().get(i));
            }

        }
    }

    public static void previousYear(){
        List<String> monthToDate = lists();
        for (int i = 0; i < lists().size(); i++){
            String dateMonth = lists().get(i);
            String[] split = dateMonth.split("\\|");
            String date2 = split[0];
            LocalDate date = LocalDate.now();
            int year = date.minusYears(1).getYear();
            LocalDate listDate = LocalDate.parse(date2);

            if (listDate.getYear() == year){
                System.out.println(lists().get(i));
            }
        }
    }

    public static void goBackLedger() {
        System.out.println("""
                0) Back
                X) Exit""");
        String choice = scanner.nextLine();
        if (choice.trim().equalsIgnoreCase("0")) {
            ledger();
        } else if (choice.trim().equalsIgnoreCase("x")) {
            System.out.println("""
                    Thank you for using the CLI App!!
                    """);
            System.exit(0);
        }
    }

    public static void goBackReports(){
        System.out.println("""
                        0) Back
                        X) Exit""");
        String choice2 = scanner.nextLine();
        if (choice2.trim().equalsIgnoreCase("0")){
            reports();
        } else if (choice2.trim().equalsIgnoreCase("x")){
            System.out.println("""
                    Thank you for using the CLI App!!
                    """);
            System.exit(0);
        }
    }

    public static void search(){
        List<String> searching = lists();
        System.out.println("Please enter the vendor name");
        String vendor = scanner.nextLine();
        boolean choice = false;
        for (int i = 0; i < lists().size(); i++){
            String name = lists().get(i);
            String[] split = name.split("\\|");
            String part = split[3];

            if (part.trim().equalsIgnoreCase(vendor)){
                System.out.println(lists().get(i));
                choice = true;
            }
        }
       if (!choice){
           System.out.println("Sorry, we couldn't find any entries for that vendor.");}

    }

    public static void startDate(){
        List<String> startDate = lists();
        System.out.println("Start Date\": The beginning date for the search.(yyyy-MM-dd)");
        String date = scanner.nextLine();
        LocalDate userDate = LocalDate.parse(date);

        for (int i = 0; i < lists().size(); i++){
            String date2 = lists().get(i);
            String[] split = date2.split("\\|");
            String startingDate = split[0];
            LocalDate localDate = LocalDate.parse(startingDate);

           if (userDate.isBefore(localDate)){
               System.out.println(lists().get(i));
           }
        }
    }

    public static void endDate(){
        List<String> endDate = lists();
        System.out.println("End Date: The date for the search.(yyyy-MM-dd)");
        String date = scanner.nextLine();
        LocalDate userDate = LocalDate.parse(date);

        for (int i = 0; i < lists().size(); i++){
            String date2 = lists().get(i);
            String[] split = date2.split("\\|");
            String endingDate = split[0];
            LocalDate localDate = LocalDate.parse(endingDate);

            if (userDate.isAfter(localDate)){
                System.out.println(lists().get(i));
            }
        }
    }

    public static void description() {
        List<String> descriptions = lists();
        System.out.println("Please enter the description");
        String detail = scanner.nextLine();

        for (int i = 0; i < lists().size(); i++) {
            String string = lists().get(i);
            String[] split = string.split("\\|");
            String description = split[2];

            if (detail.trim().equalsIgnoreCase(description)) {
                System.out.println(lists().get(i));
            }
        }
    }

    public static void vendor() {
        List<String> vendors = lists();
        System.out.println("Please enter the vendor name");
        String seller = scanner.nextLine();

        for (int i = 0; i < lists().size(); i++) {
            String string = lists().get(i);
            String[] split = string.split("\\|");
            String vendor = split[3];

            if (seller.trim().equalsIgnoreCase(vendor)) {
                System.out.println(lists().get(i));
            }


        }
    }

    public static void amount() {
        List<String> amounts = lists();
        System.out.println("Please enter the amount");
        float userAmount = scanner.nextFloat();

        for (int i = 0; i < lists().size(); i++) {
            String string = lists().get(i);
            String[] split = string.split("\\|");
            float amount = Float.parseFloat(split[4]);

            if (userAmount==amount) {
                System.out.println(lists().get(i));
            }
        }
    }
}



