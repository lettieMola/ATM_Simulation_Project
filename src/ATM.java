import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private HashMap<Integer, User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        this.users = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.currentUser = null;

        // Initialize some users
        users.put(546, new User(101, "1234", 1000.00));
        users.put(321, new User(102, "5678", 1500.00));
    }

    public void authenticateUser() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        
        if (users.containsKey(accountNumber)) {
            User user = users.get(accountNumber);
            System.out.print("Enter PIN: ");
            String enteredPin = scanner.next();

            if (user.validatePin(enteredPin)) {
                currentUser = user;
                System.out.println("Login successful.");
                showMenu();
            } else {
                System.out.println("Incorrect PIN. Try again.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Switch Account");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    switchAccount();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        currentUser.getAccount().deposit(amount);
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        currentUser.getAccount().withdraw(amount);
    }

    private void transfer() {
        System.out.print("Enter recipient's account number: ");
        int recipientAccountNumber = scanner.nextInt();
        if (users.containsKey(recipientAccountNumber)) {
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();
            currentUser.getAccount().transfer(users.get(recipientAccountNumber).getAccount(), amount);
        } else {
            System.out.println("Recipient account not found.");
        }
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + currentUser.getAccount().getBalance());
    }

    private void viewTransactionHistory() {
        currentUser.getAccount().printTransactionHistory();
    }

    private void switchAccount() {
        System.out.println("Switching account...");
        currentUser = null;
        authenticateUser();
    }
}