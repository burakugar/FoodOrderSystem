package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;


public class Basket {
    PriorityQueue<Food> myBasket;
    ArrayList<Double> priceList;
    ArrayList<Restaurant> restaurantList = new ArrayList<>();
    ListGraph userGraph;
    Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Basket(PriorityQueue<Food> myBasket) {
        this.myBasket = myBasket;
    }

    public Basket() {
        myBasket = new PriorityQueue<>();
    }

    public PriorityQueue<Food> getMyBasket() {
        return myBasket;
    }

    public void setMyBasket(PriorityQueue<Food> myBasket) {
        this.myBasket = myBasket;
    }

    public void displayBasket() {
        Food[] myBasketArray = new Food[999];
        myBasket.toArray(myBasketArray);
        for (int i = 0; i < 999; i++) {
            if (myBasketArray[i] != null) {
                java.lang.System.out.println("Name: " + myBasketArray[i].getName() + " Price " + myBasketArray[i].getPrice() + " Count: " + myBasketArray[i].getCount() + " Restaurant:" + myBasketArray[i].getName());
            }

        }
    }

    public void add(Food food) throws Exception {
        if (food != null) {
            myBasket.add(food);
        } else {
            throw new Exception("not Null");
        }
    }

    public void delete(Food food) throws Exception {

        if (food != null) {
            myBasket.remove(food);
        } else {
            throw new Exception("not Null");
        }

    }

    public void showPrice() {
        int size = 0;
        priceList = new ArrayList<>();
        Iterator<Food> foodIterator = myBasket.iterator();
        Iterator<Food> foodIterator1 = myBasket.iterator();

        ArrayList<Restaurant> foodRestaurantList = new ArrayList<>();
        java.lang.System.out.println(myBasket.size());
        while (foodIterator1.hasNext()) {
            priceList.add(foodIterator1.next().getPrice());
            size++;
        }
        while (foodIterator.hasNext()) {
            foodRestaurantList = foodIterator.next().getRestaurantArrayList();
            for (int i = 0; i < foodRestaurantList.size(); i++) {
                if (!restaurantList.contains(foodRestaurantList.get(i))) {
                    restaurantList.add(foodRestaurantList.get(i));
                }
            }
        }
        userGraph = new ListGraph(size, true);
        for (int i = 0; i < priceList.size() - 1; i++) {
            Edge edge = new Edge(i, i + 1);
            userGraph.insert(edge);
        }
        Edge edge = new Edge(priceList.size() - 1, 0);
        userGraph.insert(edge);
        int sum = 0;
        int[] arr = BreadthFirstSearch.breadthFirstSearch(userGraph, 0);
        arr[0] = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                sum += priceList.get(i);
            }
        }
        String customerAdress = this.customer.getAddress();
        Integer deliveryPrice = 0;
        ArrayList<Restaurant> traversedList = new ArrayList<>();
        for (int i = 0; i < restaurantList.size(); i++) {
            if (traversedList.contains(restaurantList.get(i)) != true) {
                deliveryPrice += restaurantList.get(i).addLocation(customerAdress);
            }
            traversedList.add(restaurantList.get(i));
        }

        java.lang.System.out.println("Delivery price is : " + deliveryPrice);
        sum += deliveryPrice;
        java.lang.System.out.println("Total price is : " + sum);

    }


}