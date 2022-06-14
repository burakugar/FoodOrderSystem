package util;

import java.util.ArrayList;

public class Food implements Comparable<Food> {
    private String name;
    private double price;
    private int count = 0;
    private ArrayList<Restaurant> restaurantArrayList = new ArrayList<>();

    public Food(String name, double price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public Food() {

    }

    public ArrayList<Restaurant> getRestaurantArrayList() {
        return restaurantArrayList;
    }


    public Food(String name) {
        this.name = name;
        this.price = 0;
    }


    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count + '\'' +
                '}';
    }

    public int compareTo(Food obj) {
        return this.name.compareTo(obj.getName());
    }

    /**
     * FOR TESTING METHOD "TS_FOOD_001"
     * Compares food, returns 0 if objects prices equal, returns 1 if parameter price is higher, otherwise returns -1
     *
     * @param obj
     * @return
     */
    public int TestingCompareTo(Food obj) {
        if (this.price == obj.getPrice()) {
            return 0;
        } else if (this.price < obj.getPrice()) {
            return -1;
        } else {
            return 1;
        }
    }
}

