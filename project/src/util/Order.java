package util;

public class Order implements Comparable<Order> {
    public Basket orderedBasket;
    public Restaurant restaurant;
    public String customerName;

    public Order(){

    }

    public Order(Basket OrderBasket,Restaurant restaurant1,String name){

        this.orderedBasket=OrderBasket;
        this.restaurant=restaurant1;
        this.customerName=name;
    }


    public void setOrderedBasket(Basket orderedBasket) {
        this.orderedBasket = orderedBasket;
    }

    @Override
    public int compareTo(Order o) {
        return 0;
    }
}
