import java.util.Scanner;

public class CLI_Application {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        homeScreen();
    }

    public static void homeScreen(){

        String choice = "";
        do {
            System.out.println("""
                    Welcome to CLI Application
             D) Add Deposit
             P) Make Payment(Debit)
             L) Ledger
             X) Exit   \s""");
             choice = scanner.nextLine();
        } while (!choice.trim().equalsIgnoreCase("x"));
    }
}
