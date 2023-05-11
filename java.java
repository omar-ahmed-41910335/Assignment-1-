package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class CoffeeShop {
    private String name;
    private MenuItem[] menu;
    private String[] orders;

    public CoffeeShop(String name, MenuItem[] menu) {
        this.name = name;
        this.menu = menu;
        this.orders = new String[0];
    }

    public String addOrder(String item) {
        for (MenuItem menuItem : menu) {
            if (menuItem.getItem().equals(item)) {
                String[] newOrders = new String[orders.length + 1];
                System.arraycopy(orders, 0, newOrders, 0, orders.length);
                newOrders[orders.length] = item;
                orders = newOrders;
                return "";
            }
        }
        return "This item is currently unavailable!";
    }

    public String fulfillOrder() {
        if (orders.length > 0) {
            String item = orders[0];
            String[] newOrders = new String[orders.length - 1];
            System.arraycopy(orders, 1, newOrders, 0, orders.length - 1);
            orders = newOrders;
            return "The " + item + " is ready!";
        } else {
            return "All orders have been fulfilled!";
        }
    }

    public String[] listOrders() {
        return orders;
    }

    public double dueAmount() {
        double totalAmount = 0.0;
        for (String order : orders) {
            for (MenuItem menuItem : menu) {
                if (menuItem.getItem().equals(order)) {
                    totalAmount += menuItem.getPrice();
                    break;
                }
            }
        }
        return totalAmount;
    }

    public String cheapestItem() {
        if (menu.length == 0) {
            return "";
        }

        MenuItem cheapestItem = menu[0];
        for (int i = 1; i < menu.length; i++) {
            if (menu[i].getPrice() < cheapestItem.getPrice()) {
                cheapestItem = menu[i];
            }
        }
        return cheapestItem.getItem();
    }

    public String[] drinksOnly() {
        List<String> drinks = new ArrayList<>();
        for (MenuItem menuItem : menu) {
            if (menuItem.getType().equals("drink")) {
                drinks.add(menuItem.getItem());
            }
        }
        return drinks.toArray(new String[0]);
    }

    public String[] foodOnly() {
        List<String> foodItems = new ArrayList<>();
        for (MenuItem menuItem : menu) {
            if (menuItem.getType().equals("food")) {
                foodItems.add(menuItem.getItem());
            }
        }
        return foodItems.toArray(new String[0]);
    }
}

class MenuItem {
    private String item;
    private String type;
    private double price;

    public MenuItem(String item, String type, double price) {
        this.item = item;
        this.type = type;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
public class java {
    public static void main(String[] args) {
        // Creating menu items
        MenuItem[] menu = {
                new MenuItem("Coffee", "drink", 6.99),
                new MenuItem("Sandwich", "food", 5.99),
                new MenuItem("Tea", "drink", 2.99),
                new MenuItem("Cake", "food", 7.99),
                new MenuItem("cinnamon roll", "food", 18.99)
        };

        // Creating a coffee shop
        CoffeeShop coffeeShop = new CoffeeShop("My Coffee Shop", menu);

        // Adding orders
        System.out.println(coffeeShop.addOrder("hot coca"));
        System.out.println(coffeeShop.addOrder("cinnamon roll"));// ""
        System.out.println(coffeeShop.addOrder("Tea")); // ""
        System.out.println(coffeeShop.addOrder("Cake")); // "This item is currently unavailable!"

        // Fulfilling orders
        System.out.println(coffeeShop.fulfillOrder()); // "The Coffee is ready!"
        System.out.println(coffeeShop.fulfillOrder()); // "The Tea is ready!"
        System.out.println(coffeeShop.fulfillOrder()); // "All orders have been fulfilled!"

        // Listing orders
        System.out.println(Arrays.toString(coffeeShop.listOrders())); // []

        // Due amount
        System.out.println(coffeeShop.dueAmount()); // 0.0

        // Cheapest item
        System.out.println(coffeeShop.cheapestItem()); // "Tea"

        // Drinks only
        System.out.println(Arrays.toString(coffeeShop.drinksOnly())); // ["Coffee", "Tea"]

        // Food only
        System.out.println(Arrays.toString(coffeeShop.foodOnly())); // ["Sandwich", "Cake"]
    }
}
