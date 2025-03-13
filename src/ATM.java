import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private HashMap<Integer, BankAccount> accounts;
    private BankAccount currentAccount;
    private Scanner scanner;

    public ATM() {
        this.accounts = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.currentAccount = null;

        // Initialize some accounts
        accounts.put(101, new BankAccount(1000.00, "1234")); // Account 101, PIN 1234
        accounts.put(102, new BankAccount(1500.00, "5678")); // Account 102, PIN 5678
    }

    public void authenticateUser() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        
        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            System.out.print("Enter PIN: ");
            String enteredPin = scanner.next();

            if (account.validatePin(enteredPin)) {
                currentAccount = account;
                System.out.println("Login successful.");
                start();
            } else {
                System.out.println("Incorrect PIN. Try again.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction Logs");
            System.out.println("5. Switch Account");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    viewTransactionLogs();
                    break;
                case 5:
                    switchAccount();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        currentAccount.withdraw(amount);

        System.out.print("Would you like to print a slip? (yes/no): ");
        String printSlip = scanner.next();
        if (printSlip.equalsIgnoreCase("yes")) {
            currentAccount.printSlip("Withdrawal", amount);
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        System.out.print("Are you sure you want to deposit $" + amount + "? (yes/no): ");
        String confirmDeposit = scanner.next();

        if (confirmDeposit.equalsIgnoreCase("yes")) {
            currentAccount.deposit(amount);
        } else {
            System.out.println("Deposit canceled.");
        }
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + currentAccount.getBalance());
    }

    private void viewTransactionLogs() {
        currentAccount.showTransactionLogs();
    }

    private void switchAccount() {
        System.out.println("Switching account...");
        currentAccount = null;
        authenticateUser();
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.authenticateUser();
    }
}