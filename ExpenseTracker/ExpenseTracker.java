import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    int id;
    String category;
    String description;
    double amount;

    Expense(int id, String category, String description, double amount) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    void display() {
        System.out.printf("%-5d %-15s %-25s ₹%.2f%n",
                id, category, description, amount);
    }
}

public class ExpenseTracker {
    static ArrayList<Expense> expenses = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextId = 1;

    static void addExpense() {
        System.out.println("\n--- Add Expense ---");

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        expenses.add(new Expense(nextId++, category, description, amount));
        System.out.println("Expense added successfully!");
    }

    static void viewExpenses() {
        System.out.println("\n--- All Expenses ---");

        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.printf("%-5s %-15s %-25s %s%n",
                "ID", "Category", "Description", "Amount");
        System.out.println("-------------------------------------------------------------");

        for (Expense e : expenses) {
            e.display();
        }
    }

    static void deleteExpense() {
        System.out.println("\n--- Delete Expense ---");

        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
            return;
        }

        System.out.print("Enter Expense ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).id == id) {
                expenses.remove(i);
                System.out.println("Expense deleted successfully!");
                return;
            }
        }

        System.out.println("Expense ID not found.");
    }

    static void calculateTotal() {
        double total = 0;

        for (Expense e : expenses) {
            total += e.amount;
        }

        System.out.printf("\nTotal Expense: ₹%.2f%n", total);
    }

    static void categoryExpense() {
        System.out.println("\n--- Category-wise Expense ---");

        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        double total = 0;
        boolean found = false;

        for (Expense e : expenses) {
            if (e.category.equalsIgnoreCase(category)) {
                total += e.amount;
                found = true;
            }
        }

        if (found) {
            System.out.printf("Total expense for %s: ₹%.2f%n", category, total);
        } else {
            System.out.println("No expenses found in this category.");
        }
    }

    static void searchExpense() {
        System.out.println("\n--- Search Expense ---");

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;

        for (Expense e : expenses) {
            if (e.category.toLowerCase().contains(keyword)
                    || e.description.toLowerCase().contains(keyword)) {

                if (!found) {
                    System.out.printf("%-5s %-15s %-25s %s%n",
                            "ID", "Category", "Description", "Amount");
                    System.out.println("-------------------------------------------------------------");
                }

                e.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching expense found.");
        }
    }

    public static void main(String[] args) {
        int choice;

        System.out.println("======================================");
        System.out.println("       JAVA EXPENSE TRACKER");
        System.out.println("======================================");

        do {
            System.out.println("\n------------- MENU ----------------");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Calculate Total Expense");
            System.out.println("5. Category-wise Expense");
            System.out.println("6. Search Expense");
            System.out.println("7. Exit");
            System.out.println("------------------------------------");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> deleteExpense();
                case 4 -> calculateTotal();
                case 5 -> categoryExpense();
                case 6 -> searchExpense();
                case 7 -> System.out.println("\nThank you for using Expense Tracker!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);

        sc.close();
    }
}
