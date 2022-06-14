package util;

import java.lang.System;
import java.sql.Array;
import java.sql.SQLOutput;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Menu {
    Scanner sn;
    util.System system;
    Admin admin;
    Customer myCurrentCustomer;
    Basket basket;
    Restaurant currentRestaurant;
    ArrayList<Customer> customerList = new ArrayList<>();

    public Menu() {
        sn = new Scanner(System.in);
        system = new util.System();
        admin = system.getAdmin();
        basket = new Basket();
        currentRestaurant = new Restaurant();
    }

    public Menu(Scanner sn, util.System system) {
        this.sn = sn;
        this.system = system;
    }

    public void driver() {
        System.out.println("Welcome to food order system! ");
        sn = new Scanner(System.in);
        User user = new User();
        Integer menuOption = 0;

        boolean flag = true;
        while (flag) {
            System.out.println("Please select from following options: ");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            menuOption = sn.nextInt();
            int temp = 0;
            if (menuOption == 1) {
                System.out.println("Please choose a user type: ");
                System.out.println("1. Customer");
                System.out.println("2. Restaurant");
                System.out.println("3. Admin");
                temp = sn.nextInt();
                if (temp == 1) {
                    signInForCustomer();
                } else if (temp == 2) {
                    signInForRestaurant();
                } else if (temp == 3) {
                    sn.nextLine();
                    System.out.println("Enter mail");
                    String adminMail = sn.nextLine();
                    System.out.println("Enter password");
                    String adminPassword = sn.nextLine();
                    if (admin.getMail().equals(adminMail) && admin.getPassword().equals(adminPassword)) {
                        adminMenu();
                    } else {
                        System.out.println("Wrong input");
                    }
                }
            } else if (menuOption == 2) {
                System.out.println("Please choose a user type: ");
                System.out.println("1. Customer");
                System.out.println("2. Restaurant");
                temp = sn.nextInt();
                if (temp == 1) {
                    signUpForCustomer();
                } else if (temp == 2) {
                    signUpForRestaurant();
                }
            } else {
                System.out.println("Please choose a valid menu: ");
                System.exit(1);
            }
        }
    }


    private void signInForRestaurant() {
        String mail;
        String password;
        sn.nextLine();
        System.out.println("Enter your mail");
        mail = sn.nextLine();
        System.out.println("Enter your password");
        password = sn.nextLine();
        User newUser = new User(mail, password);
        int flag = 0;
        while (flag != 1) {
            if (system.LogInForRestaurant(newUser)) {
                System.out.println("1-) Add Food");
                System.out.println("2-) Delete Food");
                System.out.println("3-) Update Food");
                System.out.println("4-) See orders status");
                System.out.println("5-) Exit");
                int choice = sn.nextInt();
                Restaurant restaurant = system.returnRestaurant(newUser);
                currentRestaurant = restaurant;
                if (choice == 1) {
                    sn.nextLine();
                    System.out.println("Enter food name");
                    String FoodName = sn.nextLine();
                    System.out.println("Enter a price");
                    Double price = sn.nextDouble();
                    System.out.println("Enter a count");
                    Integer count = sn.nextInt();
                    Food food = new Food(FoodName, price, count);
                    restaurant.addFood(food);
                    food.getRestaurantArrayList().add(restaurant);
                } else if (choice == 2) {
                    restaurant.allFood.inOrder();
                    System.out.println("\nWhich food you want to update");
                    sn.nextLine();
                    String name = sn.nextLine();
                    Food food = system.findFood(restaurant, name);
                    if (food != null) {
                        system.deleteFood(restaurant, food);
                    } else {
                        System.out.println("There is no such a food");
                    }
                    restaurant.allFood.inOrder();
                } else if (choice == 3) {
                    restaurant.allFood.inOrder();
                    System.out.println("Which food you want to update");
                    String name = sn.nextLine();
                    Food food = system.findFood(restaurant, name);
                    if (food != null) {
                        System.out.println("Enter new price");
                        double price = sn.nextDouble();
                        if (food != null) {
                            food.setPrice(price);
                            system.updateFood(restaurant, food);
                            restaurant.allFood.inOrder();
                        }
                    } else {
                        System.out.println("There is no such a food");
                    }
                } else if (choice == 4) {
                    Iterator<Order> order = currentRestaurant.allOrder.iterator();
                    System.out.println(currentRestaurant.name);
                    while (order.hasNext()) {
                        Order orderNew = order.next();
                        Iterator<Food> foodIterator = orderNew.orderedBasket.myBasket.iterator();
                        while (foodIterator.hasNext()) {
                            Food foodItem = foodIterator.next();
                            System.out.println(foodItem.getName() + " " + foodItem.getPrice() + " " + orderNew.customerName);
                        }
                    }

                } else if (choice == 5) {
                    flag = 1;
                }
            }
        }
    }

    private void signInForCustomer() {
        String mail;
        String password;
        sn.nextLine();
        System.out.println("Enter mail address");
        mail = sn.nextLine();
        System.out.println("Enter password");
        password = sn.nextLine();
        User newUser = new User(mail, password);
        Customer newCustomer = system.findCustomer(newUser);
        if (system.LogIn(newUser)) {
            //Customer devam edecek
            this.myCurrentCustomer = newCustomer;
            CustomerMenu();
        } else if (system.LogInForRestaurant(newUser)) {
            //Restaurant devam edecek

        } else {
            System.out.println("There is no such user");

        }

    }

    boolean flagForBasket = false;

    private void CustomerMenu() {
        int menu = 1;
        while (menu != 8) {
            System.out.println("1-) See all restaurants");
            System.out.println("2-) See which restaurants have the searched food");
            System.out.println("3-) Change Password");
            System.out.println("4-) Change Address");
            System.out.println("5-) Display current Basket");
            System.out.println("6-) Show the basket price");
            System.out.println("7-) Print restaurants in order");
            System.out.println("8-) Log out");
            menu = sn.nextInt();
            if (menu == 1) {
                system.displayAllRestaurant();
                int index;
                System.out.println("Choose a restaurant");
                index = sn.nextInt();
                index -= 1;
                try {
                    system.displayRestaurant(index);
                    int flag = 1;
                    Restaurant restaurant = system.returnRestaurant(index);
                    currentRestaurant = restaurant;
                    while (flag == 1) {
                        sn.nextLine();
                        System.out.println("Enter a food name or write 'Exit' if you do not want to continue");
                        String itemForMenu = sn.nextLine();
                        if (itemForMenu.equals("Exit") || itemForMenu.equals("exit")) {
                            flag = 0;
                        } else {
                            Food food1 = restaurant.allFood.find(new Food(itemForMenu));
                            System.out.print("You want to add this item to your basket ? Y or N : ");
                            char yesNo = sn.next().charAt(0);
                            if (yesNo == 'Y' || yesNo == 'y') {
                                myCurrentCustomer.getTotalBasket().add(food1);
                                Basket basket = new Basket();
                                basket.add(food1);
                                basket.setCustomer(myCurrentCustomer);
                                basket.restaurantList.add(currentRestaurant);
                                Order order = new Order(basket, currentRestaurant, myCurrentCustomer.getMail());
                                currentRestaurant.allOrder.add(order);

                            }
                        }
                    }

                } catch (Exception e) {

                }
            } else if (menu == 2) {
                filterTheRestaurantsHaveSelectedFood();
            } else if (menu == 3) {
                changePasswordMenu();
            } else if (menu == 4) {
                changeAddressMenu();
            } else if (menu == 5) {
                approveBasket();
                ;
            } else if (menu == 6) {
                if (flagForBasket == false) {
                    approveBasket();
                }
                if (flagForBasket == true) {
                    myCurrentCustomer.getTotalBasket().showPrice();
                }
            } else if (menu == 7) {
                ArrayList<Restaurant> restaurantArrayList = system.getRestaurantList();
                String[] restaurantArray = new String[restaurantArrayList.size()];
                for (int i = 0; i < restaurantArrayList.size(); i++) {
                    restaurantArray[i] = restaurantArrayList.get(i).getName();
                }
                SelectionSort.sort(restaurantArray);
                System.out.println("After applying selection sort, restaurant names will be: ");
                for (int i = 0; i < restaurantArray.length; i++) {
                    System.out.println("Restaurant name: " + restaurantArray[i]);
                }
            } else if (menu == 8) {
                if (flagForBasket == false) {
                    approveBasket();
                }
                System.out.println("You're successfully logged out.");
            }
        }

    }

    void filterTheRestaurantsHaveSelectedFood() {
        sn.nextLine();
        System.out.println("Enter the name of the food : ");
        String name = sn.nextLine();
        Iterator<Restaurant> restaurantIterator = myCurrentCustomer.getOrderedFood(system, name).iterator();
        while (restaurantIterator.hasNext()) {
            System.out.println("Name of the restaurant is : " + restaurantIterator.next().getName());
        }
    }

    void approveBasket() {
        myCurrentCustomer.getTotalBasket().displayBasket();
        myCurrentCustomer.getTotalBasket().setCustomer(myCurrentCustomer);
        System.out.println("Do you approve your basket Y / N ");
        char decision = sn.next().charAt(0);
        if (decision == 'Y' || decision == 'y') {
            flagForBasket = true;
            myCurrentCustomer.getTotalBasket().displayBasket();
            if (myCurrentCustomer.getTotalBasket().myBasket.size() == 0) {
                System.out.println("Your basket is empty");
                myCurrentCustomer.confirmBasket(system);
            } else {
                System.out.println("Your order is started preparing...");
            }
        } else {

            flagForBasket = false;
            myCurrentCustomer.getTotalBasket().myBasket.remove();
        }

    }

    void changeAddressMenu() {
        sn.nextLine();
        System.out.println("Enter a new Address");
        String newAddress = sn.nextLine();
        myCurrentCustomer.setAddress(newAddress);
        system.UpdateCustomer(myCurrentCustomer);
        System.out.println("New adress is: " + newAddress);
    }

    void changePasswordMenu() {
        sn.nextLine();
        System.out.println("Enter a new password");
        String newPassword = sn.nextLine();
        myCurrentCustomer.setPassword(newPassword);
        system.UpdateCustomer(myCurrentCustomer);
        System.out.println("New password is: " + newPassword);

    }

    private void adminMenu() {
        int option = 0;
        do {
            System.out.println("1- Edit Own Profile Details");
            System.out.println("2- Search Restaurant");
            System.out.println("3- View Restaurants");
            System.out.println("4- View Menus of Restaurants");
            System.out.println("5- Add Food");
            System.out.println("6- Remove Food");
            System.out.println("7- Update Food");
            System.out.println("8- Delete Customer");
            System.out.println("9- Delete Restaurant");
            System.out.println("10- See Statistics");
            System.out.println("11- See all the customers");
            System.out.print("Enter your option: ");
            option = sn.nextInt();
            switch (option) {
                case 1:
                    System.out.println("1- Mail");
                    System.out.println("2- Password");
                    int option2 = sn.nextInt();
                    if (option2 == 1) {
                        System.out.println("Enter the new mail: ");
                        sn.nextLine();
                        String mail = sn.nextLine();

                        admin.setMail(mail);
                    } else if (option2 == 2) {
                        System.out.println("Enter the new password: ");
                        sn.nextLine();
                        String password = sn.nextLine();

                        admin.setPassword(password);
                    } else {
                        System.out.println("Wrong choice.");
                    }
                    break;
                case 2:
                    System.out.print("Enter the name of the restaurant: ");
                    sn.nextLine();
                    String name_of_restaurant = sn.nextLine();

                    system.searchByRestaurantName(name_of_restaurant);
                    break;
                case 3:
                    system.displayAllRestaurant();
                    break;
                case 4:
                    system.displayAllRestaurantMenus();
                    break;
                case 5:
                    System.out.print("Enter the name of the restaurant: ");
                    sn.nextLine();
                    String restaurantName = sn.nextLine();
                    Restaurant restaurant = new Restaurant(restaurantName, "", "", "");

                    System.out.print("Enter the name of the Food");
                    String foodName = sn.nextLine();
                    System.out.print("Enter the food of price");
                    int priceFood = sn.nextInt();
                    Food food = new Food(foodName, priceFood, 10);
                    system.addFood(restaurant, food);
                    break;
                case 6:
                    System.out.print("Enter the name of the restaurant: ");
                    String restaurant1Name = sn.nextLine();
                    Restaurant restaurant1 = new Restaurant(restaurant1Name, "", "", "");
                    System.out.print("Enter the name of the Food");
                    String foodName1 = sn.nextLine();
                    System.out.print("Enter the food of price");
                    int priceFood1 = sn.nextInt();
                    Food food1 = new Food(foodName1, priceFood1, 10);
                    system.deleteFood(restaurant1, food1);
                    break;
                case 7:
                    System.out.print("Enter the name of the restaurant: ");
                    sn.nextLine();
                    String name_of_restaurant2 = sn.nextLine();
                    Restaurant restaurant2 = system.searchByRestaurantName(name_of_restaurant2);
                    if (restaurant2 != null) {
                        System.out.print("Enter the name of the food: ");
                        String name_of_food = sn.nextLine();
                        System.out.print("Enter the price of the food: ");
                        int price_of_food = sn.nextInt();
                        Food food2 = new Food(name_of_food);
                        restaurant2.UpdateMenu(food2, price_of_food);
                    }
                    break;
                case 8:
                    System.out.println("Enter the mail of the customer: ");
                    sn.nextLine();
                    String mail_of_customer = sn.nextLine();
                    system.DeleteCustomer(mail_of_customer);
                    break;

                case 9:
                    System.out.println("Enter the mail of the restaurant: ");
                    sn.nextLine();
                    String mail_of_restaurant = sn.nextLine();
                    system.DeleteRestaurant(mail_of_restaurant);
                    break;
                case 10:
                    System.out.println("1- Stats of a Customer");
                    System.out.println("2- Stats of a Restaurant");
                    int option3 = sn.nextInt();
                    System.out.println("Enter the mail: ");
                    sn.nextLine();
                    String mail = sn.nextLine();
                    if (option3 == 1) {
                        system.seeCustomerStats(mail);
                    } else if (option3 == 2) {
                        system.seeRestaurantsStats(mail);
                    } else {
                        System.out.println("Wrong choice.");
                    }
                    break;
                case 11:
                    for (int i = 0; i < customerList.size(); i++) {
                        Customer customer = customerList.get(i);
                        System.out.println("Customer name:" + customer.getName());
                        System.out.println("Customer e-mail:" + customer.getMail());
                        System.out.println("Customer adress:" + customer.getAddress());
                        System.out.println("Customer password \n:" + customer.getPassword());
                        System.out.println("Customer basket \n:" + customer.getTotalBasket());

                    }
                default:
                    System.out.println("Wrong choice");
                    break;

            }
        } while (0 < option && option < 18);
    }

    private void signIn() {

    }

    // kayit fonksiyonu
    private void signUpForCustomer() {
        String name;
        String password;
        String address;
        sn.nextLine();
        System.out.println("Enter the name of the customer: ");
        name = sn.nextLine();
        System.out.println("Enter the name of the password: ");
        password = sn.nextLine();
        System.out.println("Enter the name of the address: ");
        address = sn.nextLine();
        Customer customer = new Customer(name, password, address);
        system.SignUp(customer);
        customerList.add(customer);
    }

    void signUpForRestaurant() {
        String mail;
        String password;
        String name;
        String adress;
        sn.nextLine();
        System.out.println("Enter the mail of the Restaurant: ");
        mail = sn.nextLine();
        System.out.println("Enter the password: ");
        password = sn.nextLine();
        System.out.println("Enter the name of the Restaurant: ");
        name = sn.nextLine();
        System.out.println("Enter the adress of the Restaurant: ");
        adress = sn.nextLine();

        Restaurant restaurant = new Restaurant(mail, password, name, adress);
        system.addRestaurant(restaurant);
    }

}
