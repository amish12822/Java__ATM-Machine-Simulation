import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class BankAccount {
    private double balance;
    private int pin;
    private ArrayList<String> transactions;

    public BankAccount(double initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactions = new ArrayList<>();
        transactions.add("Account created initial balance: $" + initialBalance);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful, New balance: $" + balance);
            transactions.add("Deposited: $" + amount + ",New Balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawal successful. New balance: $" + balance);
                transactions.add("Withdrew: $" + amount + ", New Balance: $" + balance);
                return true;
            } else {
                System.out.println("Insufficient. Current balance: $" + balance);
            }
        } else {
            System.out.println("Invalid withdrawal amount. Enter a positive amount.");
        }
        return false;
    }

    public boolean changePin(int oldPin, int newPin) {
        if (this.pin == oldPin) {
            this.pin = newPin;
            System.out.println("PIN Change successful.");
            transactions.add("PIN Changed.");
            return true;
        } else {
            System.out.println("Incorrect Old PIN.");
            return false;
        }
    }

    public void printTransactions() {
        System.out.println("Transaction History:");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }
}

class ATM {
    private BankAccount bankAccount;
    private Scanner scanner;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Account balence inquiry");
        System.out.println("2. Cash Deposit");
        System.out.println("3. Cash Withdraw");
        System.out.println("4. PIN change");
        System.out.println("5. Transactions history");
        System.out.println("6. Exit");
    }

    public void run() {
        while (true) {
            try {
                displayMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Current balance: $" + bankAccount.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter the deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        bankAccount.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter the withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        bankAccount.withdraw(withdrawalAmount);
                        break;
                    case 4:
                        System.out.print("Enter your current PIN: ");
                        int oldPin = scanner.nextInt();
                        System.out.print("Enter your new PIN: ");
                        int newPin = scanner.nextInt();
                        bankAccount.changePin(oldPin, newPin);
                        break;
                    case 5:
                        bankAccount.printTransactions();
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }
}

public class ATMinterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0, 1234); // Initial balance is $1000 and initial PIN is 1234
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}
