import java.util.Scanner;

public class BankingSystem {

    private String accountHolderName;
    private int accountNumber;
    private double balance;
    
    public BankingSystem(String accountHolderName, int accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    // Deposit money into account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited: " + amount);
        } else {
            System.out.println("Error: Cannot deposit a negative amount.");
        }
    }
    
    // Withdraw money from account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount withdrawn: " + amount);
        } else {
            System.out.println("Error: Insufficient balance or invalid amount.");
        }
    }
    
    // Check balance
    public double getBalance() {
        return balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Account creation
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your account number: ");
        int accountNumber = scanner.nextInt();
        System.out.println("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        BankingSystem account = new BankingSystem(name, accountNumber, initialBalance);

        boolean exit = false;

        // Menu-driven program
        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter amount to deposit:");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.println("Enter amount to withdraw:");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.println("Account Balance: " + account.getBalance());
                    break;

                case 4:
                    System.out.println("Exiting...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

        scanner.close();
    }
}
