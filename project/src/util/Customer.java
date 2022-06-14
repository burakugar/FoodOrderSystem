package util;

public class Customer extends User {
    private String address;
    private Basket TotalBasket;
    private Order ordered;
    private String name;

    public String getName() {
        return name;
    }

    private final SkipList<Order> pastOrder;


    public Customer(String name, String password, String Address) {
        super(name, password);
        this.name = name;
        this.address = Address;
        pastOrder = new SkipList<Order>();
        TotalBasket = new Basket();
    }


    public void confirmBasket(System system) {
        ordered.setOrderedBasket(TotalBasket);
        pastOrder.add(ordered);
        for (int i = 0; i < system.restaurantList.size(); ++i) {
            if (ordered.restaurant.equals(system.restaurantList.get(i))) {
                system.restaurantList.get(i).takeOrder(this);
            }
        }
        ordered = new Order();
        TotalBasket = new Basket();
    }


    public Basket getTotalBasket() {
        return TotalBasket;
    }

    public Order getOrdered() {
        return ordered;
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

    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    //AVL TREE
    public AVLTree<Restaurant> getOrderedFood(System system, String name) {
        AVLTree<Restaurant> foodList = new AVLTree<>();
        for (int i = 0; i < system.restaurantList.size(); ++i) {
            Restaurant temp = system.restaurantList.get(i);
            Food food = new Food(name);
            if (temp.allFood.contains(food) == true) {
                foodList.add(temp);
            }
        }

        return foodList;
    }

    @Override
    public boolean equals(Object obj) {
        User temp = (((User) obj));
        return temp.getMail() == this.getMail();
    }

}
