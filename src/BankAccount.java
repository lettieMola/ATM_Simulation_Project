import java.util.ArrayList;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class BankAccount {
    private int accountNumber; // Add account number field
    private double balance;
    private String pin;
    private ArrayList<Transaction> transactionLogs;

    public BankAccount(int accountNumber, double initialBalance, String pin) {
        this.accountNumber = accountNumber; // Initialize account number
        this.balance = initialBalance;
        this.pin = pin;
        this.transactionLogs = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber; // Implement getAccountNumber method
    }

    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionLogs.add(new Transaction("Deposit", amount));
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionLogs.add(new Transaction("Withdrawal", amount));
            System.out.println("Successfully withdrew: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactionLogs.add(new Transaction("Transfer to " + recipient.getAccountNumber(), amount));
            System.out.println("Successfully transferred: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Invalid transfer amount.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactionLogs() {
        return transactionLogs;
    }

    public void printTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionLogs.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction log : transactionLogs) {
                System.out.println(log);
            }
        }
    }
}