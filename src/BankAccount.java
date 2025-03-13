import java.util.ArrayList;

public class BankAccount {
    private double balance;
    private String pin;
    private ArrayList<String> transactionLogs;

    public BankAccount(double initialBalance, String pin) {
        this.balance = initialBalance;
        this.pin = pin;
        this.transactionLogs = new ArrayList<>();
    }

    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionLogs.add("Deposited: $" + amount);
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionLogs.add("Withdrew: $" + amount);
            System.out.println("Successfully withdrew: $" + amount);
            System.out.println("Please take your cash.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void showTransactionLogs() {
        System.out.println("\nTransaction History:");
        if (transactionLogs.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String log : transactionLogs) {
                System.out.println(log);
            }
        }
    }

    public void printSlip(String transactionType, double amount) {
        System.out.println("\n--- Transaction Slip ---");
        System.out.println("Transaction: " + transactionType);
        System.out.println("Amount: $" + amount);
        System.out.println("Current Balance: $" + getBalance());
        System.out.println("------------------------");
    }
}