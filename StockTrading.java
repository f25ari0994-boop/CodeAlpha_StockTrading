import java.util.ArrayList;
import java.util.Scanner;

// ---------------- STOCK CLASS ----------------
class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// ---------------- TRANSACTION CLASS ----------------
class Transaction {
    String type; // BUY or SELL
    Stock stock;
    int quantity;

    Transaction(String type, Stock stock, int quantity) {
        this.type = type;
        this.stock = stock;
        this.quantity = quantity;
    }
}

// ---------------- USER CLASS ----------------
class User {
    String username;
    ArrayList<Stock> portfolio = new ArrayList<>();
    ArrayList<Transaction> history = new ArrayList<>();

    User(String username) {
        this.username = username;
    }

    void buyStock(Stock stock, int qty) {
        for (int i = 0; i < qty; i++) {
            portfolio.add(stock);
        }
        history.add(new Transaction("BUY", stock, qty));
        System.out.println(qty + " " + stock.name + " bought successfully.");
    }

    void sellStock(String stockName, int qty) {
        int count = 0;

        for (int i = 0; i < portfolio.size(); i++) {
            if (portfolio.get(i).name.equals(stockName) && count < qty) {
                portfolio.remove(i);
                i--;
                count++;
            }
        }

        if (count > 0) {
            System.out.println(count + " " + stockName + " sold successfully.");
            history.add(new Transaction("SELL", new Stock(stockName, 0), count));
        } else {
            System.out.println("Stock not found in portfolio.");
        }
    }

    void showPortfolio() {
        System.out.println("\n--- Portfolio ---");
        if (portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
            return;
        }

        for (Stock s : portfolio) {
            System.out.println("- " + s.name + " | Price: " + s.price);
        }

        System.out.println("Total Stocks: " + portfolio.size());
    }

    void showHistory() {
        System.out.println("\n--- Transaction History ---");

        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction t : history) {
            System.out.println(t.type + " | " + t.stock.name + " | Qty: " + t.quantity);
        }
    }
}

// ---------------- MAIN CLASS ----------------
public class StockTrading {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Market Stocks
        ArrayList<Stock> market = new ArrayList<>();
        market.add(new Stock("Apple", 180));
        market.add(new Stock("Tesla", 250));
        market.add(new Stock("Google", 140));
        market.add(new Stock("Amazon", 130));

        System.out.print("Enter username: ");
        String name = sc.nextLine();

        User user = new User(name);

        int choice;

        do {
            System.out.println("\n===== STOCK TRADING MENU =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n--- Market Stocks ---");
                    for (Stock s : market) {
                        System.out.println(s.name + " | Price: " + s.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock name: ");
                    String buyName = sc.next();
                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();

                    for (Stock s : market) {
                        if (s.name.equalsIgnoreCase(buyName)) {
                            user.buyStock(s, buyQty);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter stock name: ");
                    String sellName = sc.next();
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();

                    user.sellStock(sellName, sellQty);
                    break;

                case 4:
                    user.showPortfolio();
                    break;

                case 5:
                    user.showHistory();
                    break;

                case 0:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}