package util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Restaurant extends User implements Comparable<Restaurant> {
    Queue<Order> allOrder;
    public BinarySearchTree<Food> allFood = new BinarySearchTree<>();
    private double averageStar = 0.0;
    String name = " ";
    HashMap<String, Integer> data;
    private String adress;

    public Integer addLocation(String location) {
        data = new HashMap<>();
        if (adress.compareTo(adress) == 0) {
            data.put("Besiktas", 20);
            data.put("Bagcilar", 3);
            data.put("Kadikoy", 150);
            data.put("Atasehir", 250);
            data.put("Bebek", 50);
            data.put("Nisantasi", 40);
            data.put("Kartal", 200);
            data.put("Tuzla", 400);
            data.put("Beyoglu", 30);
            data.put("Maltepe", 10);
        }

        if (data.containsKey(location))
            return data.get(location);
        Integer defaultPrice = 10;
        return defaultPrice;
    }

    public Restaurant(String mail, String password, String name, String adress) {
        super(mail, password);
        allOrder = new LinkedList<>();
        this.name = name;
        this.adress = adress;
        addLocation(adress);
    }


    public Restaurant() {
        super.setID("12345");
    }

    public void takeOrder(Customer customer1) {
        allOrder.add(customer1.getOrdered());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void UpdateMenu(Food item1, double newPrice) {
        if (allFood.contains(item1)) {
            allFood.delete(item1);
            item1.setPrice(newPrice);
            allFood.add(item1);
        } else {
            //Update edilecek foodun olmadığını belirten uyarı.
            java.lang.System.out.println("There is an error when update menu.");
        }

    }

    public void addFood(Food item) {
        //add item to util.Restaurant's menu.
        allFood.add(item);

    }

    public void deleteFood(Food item) {
        //remove item from util.Restaurant's menu.
        if (allFood.contains(item)) {
            allFood.remove(item);
        } else {
            java.lang.System.out.println("The food that trying to remove is not exist in menu.");
        }

    }

    public void printMenu() {
        allFood.inOrder();
    }

    @Override
    public String getMail() {
        return super.getMail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getID() {
        return super.getID();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setID(String ID) {
        super.setID(ID);
    }

    @Override
    public void setMail(String mail) {
        super.setMail(mail);
    }


    public boolean equals(Object obj) {
        if (obj instanceof Restaurant) {
            Restaurant object = (Restaurant) obj;
            return object.getMail().equals(getMail());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder str1 = new StringBuilder();
        str1.append(this.name + "\n");
        this.allFood.inOrder();
        return allFood.deneme.toString();
    }

    @Override
    public int compareTo(Restaurant o) {
        return name.compareTo(o.name);
    }
}
