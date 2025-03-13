public class User {
    private int accountNumber;
    private String pin;
    private BankAccount account;

    public User(int accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.account = new BankAccount(accountNumber, initialBalance, pin); // Pass accountNumber to BankAccount
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public BankAccount getAccount() {
        return account;
    }
}