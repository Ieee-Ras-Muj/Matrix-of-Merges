import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystem {
    private String accountHolderName;
    private String accountNumber; // Changed to String to accommodate IBAN
    private double balance;
    private static final double MIN_BALANCE = 100.0;
    private static final double MAX_DEPOSIT_AMOUNT = 1_000_000;
    private static final double MAX_WITHDRAW_AMOUNT = 500_000;
    private static final int MAX_ACCOUNT_DIGITS = 18;
    private static final int MIN_DOMESTIC_ACCOUNT_DIGITS = 6;
    private static final int IBAN_LENGTH = 22; // Length without spaces for GB IBAN format
    private int accountType; // 1 for Domestic, 2 for IBAN

    public BankingSystem(String accountHolderName, String accountNumber, double initialBalance, int accountType) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber; // Store account number as entered
        this.balance = initialBalance;
        this.accountType = accountType; // Store account type
    }

    // Method to deposit money
    public void deposit(double amount, Scanner scanner) {
        if (amount > 0 && amount <= MAX_DEPOSIT_AMOUNT) {
            if (amount > 500_000 && !confirmTransaction("deposit", amount, scanner)) {
                System.out.println("Deposit cancelled.");
                return;
            }
            balance += amount;
            System.out.printf("Amount deposited: %.2f%n", amount);
            System.out.println("Deposit successful!");
        } else if (amount > MAX_DEPOSIT_AMOUNT) {
            System.out.println("Error: Deposit exceeds the maximum limit of 1,000,000.");
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount, Scanner scanner) {
        if (amount > 0 && amount <= balance && amount <= MAX_WITHDRAW_AMOUNT) {
            if ((balance - amount) < MIN_BALANCE && !confirmTransaction("withdraw", amount, scanner)) {
                System.out.println("Withdrawal cancelled.");
                return;
            }
            balance -= amount;
            System.out.printf("Amount withdrawn: %.2f%n", amount);
            System.out.println("Withdrawal successful!");
        } else if (amount > balance) {
            System.out.println("Error: Insufficient balance.");
        } else if (amount > MAX_WITHDRAW_AMOUNT) {
            System.out.println("Error: Withdrawal exceeds the maximum limit of 500,000.");
        } else {
            System.out.println("Error: Withdrawal amount must be positive.");
        }
    }

    // Method to confirm large transactions
    private boolean confirmTransaction(String action, double amount, Scanner scanner) {
        System.out.printf("Confirm %s of %.2f (yes/no): ", action, amount);
        String confirmation = scanner.next().trim().toLowerCase();
        return confirmation.equals("yes");
    }

    private void displayAccountInfo() {
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Account Number: " + formatAccountNumber(accountNumber.trim(), accountType)); // Format based on account type
    }

    private String formatAccountNumber(String number, int accountType) {
        // Format based on account type
        if (accountType == 2 && number.length() == IBAN_LENGTH) { // For IBAN
            return number.replaceAll("\\s+", "").replaceAll("(.{2})(.{2})(.{4})(.{6})(.{8})", "$1 $2 $3 $4 $5");
        }
        // For domestic account numbers, format with spaces in groups
        if (accountType == 1 && number.length() >= MIN_DOMESTIC_ACCOUNT_DIGITS && number.length() <= MAX_ACCOUNT_DIGITS) {
            return number.replaceAll("\\s+", "").replaceAll("(.{4})(.{4})?", "$1 $2").trim();
        }
        return number.trim(); // In case of an invalid format, return trimmed version
    }

    private void clearScreen() {
        System.out.print("\f");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name;
        String accountNumber;
        double initialBalance = 0.0;

        // Input validation for account holder name
        while (true) {
            System.out.print("Enter Account Holder Name: ");
            name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Error: Account holder name cannot be empty.");
                continue;
            }

            if (!name.matches("^[a-zA-Z\\s]+$")) {
                System.out.println("Error: Account holder name must contain only letters and spaces.");
                continue;
            }

            if (name.length() > 50) {
                System.out.println("Error: Account holder name cannot exceed 50 characters.");
                continue;
            }

            name = capitalizeWords(name);
            break;
        }

        // Ask user for account type
        int accountType = 0;
        while (accountType != 1 && accountType != 2) {
            System.out.print("Choose account type (1 for Domestic, 2 for IBAN): ");
            try {
                accountType = Integer.parseInt(scanner.nextLine().trim());
                if (accountType != 1 && accountType != 2) {
                    System.out.println("Error: Please enter 1 for Domestic or 2 for IBAN.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }

        // Input validation for account number based on account type
        while (true) {
            if (accountType == 1) { // Domestic account
                System.out.print("Enter your domestic account number (format: 1234 5678 9012 3456 max 18 digits): ");
                accountNumber = scanner.nextLine().trim();

                // Check for domestic account number format (with optional whitespace)
                if (isValidDomesticAccount(accountNumber)) {
                    break; // Valid domestic account number, exit loop
                } else {
                    System.out.println("Error: Invalid domestic account number format. Must be 6 to 18 digits.");
                }
            } else { // IBAN account
                System.out.print("Enter your IBAN (format: GB 15 ABCD 102030 12345678): ");
                accountNumber = scanner.nextLine().trim();

                // Check for IBAN format (with optional whitespace)
                if (isValidIBAN(accountNumber)) {
                    break; // Valid IBAN, exit loop
                } else {
                    System.out.println("Error: Invalid IBAN format. Ensure it meets the requirements.");
                }
            }
        }

        // Input validation for initial balance
        while (true) {
            System.out.print("Enter initial balance: ");
            try {
                initialBalance = Double.parseDouble(scanner.nextLine());
                if (initialBalance < 0) {
                    System.out.println("Error: Initial balance cannot be negative.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values only.");
            }
        }

        System.out.printf("Initial balance has been set to: %.2f%n", initialBalance); // Display initial balance
        System.out.println("Press Enter to continue..."); // Prompt to continue to options menu
        scanner.nextLine(); // Wait for user to press Enter

        BankingSystem account = new BankingSystem(name, accountNumber, initialBalance, accountType);
        account.clearScreen();

        // Display account info without balance after clearing the screen
        account.displayAccountInfo();

        boolean exit = false;

        // Menu-driven program
        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            String choice = scanner.nextLine().trim();

            switch (choice.toLowerCase()) {
                case "1":
                case "deposit":
                    System.out.print("Enter amount to deposit: ");
                    try {
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        account.deposit(depositAmount, scanner);
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine(); // Wait for user to press Enter
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid amount! Please enter a valid number.");
                        scanner.nextLine(); // Clear invalid input
                    }
                    account.clearScreen(); // Clear screen after confirmation
                    account.displayAccountInfo(); // Show account info without balance
                    break;

                case "2":
                case "withdraw":
                    System.out.print("Enter amount to withdraw: ");
                    try {
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        account.withdraw(withdrawAmount, scanner);
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine(); // Wait for user to press Enter
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid amount! Please enter a valid number.");
                        scanner.nextLine(); // Clear invalid input
                    }
                    account.clearScreen(); // Clear screen after confirmation
                    account.displayAccountInfo(); // Show account info without balance
                    break;

                case "3":
                case "check balance":
                    System.out.printf("Account Balance: %.2f%n%n", account.balance);
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine(); // Wait for user to press Enter
                    account.clearScreen(); // Clear screen after confirmation
                    account.displayAccountInfo(); // Show account info without balance
                    break;

                case "4":
                case "exit":
                    System.out.println("Exiting...Thank You for using our Banking System!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option! Please select again.");
                    break;
            }
        }

        scanner.close(); // Close scanner resource
    }

    // IBAN validation method
    private static boolean isValidIBAN(String iban) {
        String cleanIban = iban.replaceAll("\\s+", ""); // Remove whitespace for validation
        // Validate IBAN length
        if (cleanIban.length() != IBAN_LENGTH) {
            return false; // IBAN must be 22 characters long
        }

        // Validate IBAN format (Country Code, Check Digits, Bank Code, Branch Code, Account Number)
        return cleanIban.matches("^[A-Z]{2}\\d{2}[A-Z]{4}\\d{6}\\d{8}$");
    }

    // Domestic account number validation
    private static boolean isValidDomesticAccount(String number) {
        String cleanNumber = number.replaceAll("\\s+", ""); // Remove whitespace for validation
        return cleanNumber.length() >= MIN_DOMESTIC_ACCOUNT_DIGITS && cleanNumber.length() <= MAX_ACCOUNT_DIGITS && cleanNumber.matches("\\d+");
    }

    private static String capitalizeWords(String name) {
        String[] words = name.split(" ");
        StringBuilder capitalizedWords = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                capitalizedWords.append(Character.toUpperCase(word.charAt(0)))
                                 .append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        return capitalizedWords.toString().trim();
    }
}
