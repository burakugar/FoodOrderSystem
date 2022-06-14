package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

public class System {

    //Skiplist
    public ArrayList<Restaurant> restaurantList = new ArrayList<>();
    public ArrayList<User> allUsers = new ArrayList<>();
    Admin admin;


    public System() {
        admin = new Admin("Group", "15");
        allUsers.add(admin);
    }

    public Admin getAdmin() {
        return admin;
    }


    public boolean LogIn(User user1) {
        boolean flag = false;
        for (int i = 0; i < allUsers.size(); ++i) {
            if (allUsers.get(i).getMail().equals(user1.getMail())) {
                if (allUsers.get(i).getPassword().equals(user1.getPassword())) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean LogInForRestaurant(User user1) {
        boolean flag = false;
        for (int i = 0; i < restaurantList.size(); ++i) {
            if (restaurantList.get(i).getMail().equals(user1.getMail())) {
                if (restaurantList.get(i).getPassword().equals(user1.getPassword())) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public Restaurant returnRestaurant(User user1) {
        boolean flag = false;
        int a = -1;
        for (int i = 0; i < restaurantList.size(); ++i) {
            if (restaurantList.get(i).getMail().equals(user1.getMail())) {
                if (restaurantList.get(i).getPassword().equals(user1.getPassword())) {
                    a = i;
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            return restaurantList.get(a);
        }
        return null;

    }


    public void SignUp(User user1) {
        boolean flag = false;
        for (int i = 0; i < allUsers.size(); ++i) {
            if (allUsers.get(i).getMail().equals(user1.getMail())) {
                flag = true;
                break;
            }
        }
        if (flag == false) {
            java.lang.System.out.println("You have been signed up");
            allUsers.add(user1);
        } else {
            java.lang.System.out.println("Zaten kayitlisin");
        }

    }

    public ArrayList<Restaurant> getRestaurantList() {
        return restaurantList;
    }


    public void addFood(Restaurant restaurant, Food item) {
        int i;
        boolean flag = false;
        for (i = 0; i < restaurantList.size(); ++i) {
            if (restaurantList.get(i).equals(restaurant)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            restaurant.addFood(item);
        } else {
            java.lang.System.out.println("This food has already been added");
        }

    }

    public void deleteFood(Restaurant restaurant, Food item) {
        int i;
        for (i = 0; i < restaurantList.size(); ++i) {
            if (restaurantList.get(i).equals(restaurant)) {
                restaurant.deleteFood(item);
                break;
            }
        }
    }

    public void updateFood(Restaurant restaurant, Food item) {
        int i;
        for (i = 0; i < restaurantList.size(); ++i) {
            if (restaurantList.get(i).equals(restaurant)) {
                restaurant.UpdateMenu(item, item.getPrice());
            }
        }
    }

    public void addRestaurant(Restaurant restaurant) {
        int i;
        boolean flag = false;
        for (i = 0; i < restaurantList.size(); ++i) {
            if (restaurantList.get(i).equals(restaurant)) {
                flag = true;
                break;
            }
        }
        if (!flag) {

            restaurantList.add(restaurant);
        } else {
            java.lang.System.out.println("This restaurant has already been added");
        }

    }

    public void DeleteRestaurant(String restaurant_mail) {
        if (restaurantList.size() == 0) {
            return;
        } else {
            int i;
            for (i = 0; i < restaurantList.size(); ++i) {
                if (restaurantList.get(i).getMail().equals(restaurant_mail)) {
                    restaurantList.remove(restaurantList.get(i));
                    break;
                }
            }
        }
    }


    public void DeleteCustomer(String customer_mail) {
        if (allUsers.size() == 0) {
            return;
        } else {
            int i;
            int index = 0;
            boolean flag = false;
            for (i = 0; i < allUsers.size(); ++i) {
                if (allUsers.get(i) instanceof Customer) {
                    if (allUsers.get(i).getMail().equals(customer_mail)) {
                        flag = true;
                        index = i;
                        break;
                    }
                }
            }
            if (flag) {
                allUsers.remove(allUsers.get(index));
            }
        }
    }

    public void UpdateCustomer(Customer customer) {
        int i;
        boolean flag = false;
        int size = -1;
        for (i = 0; i < allUsers.size(); ++i) {
            if (allUsers.get(i) instanceof Customer) {
                if (allUsers.get(i).equals(customer)) {
                    size = i;
                    break;
                }
            }
        }
        if (size == -1) {
            java.lang.System.out.println("There is no such a customer");
        } else {
            allUsers.remove(customer);
            allUsers.add(size, customer);
        }
    }

    public void seeRestaurantsStats(String restaurant_mail) {
        int i;
        for (i = 0; i < restaurantList.size(); ++i) {
            if (restaurantList.get(i).getMail().equals(restaurant_mail)) {
                java.lang.System.out.println(restaurantList.get(i).toString());
            }
        }
    }

    public Food findFood(Restaurant restaurant, String name) {
        if (restaurant.allFood.size != 0) {
            Food food1 = new Food(name, 0, 0);
            if (restaurant.allFood.find(food1) != null) {
                return restaurant.allFood.find(food1);
            }
        } else {
            java.lang.System.out.println("There is no such a food");
        }
        return null;
    }

    public void seeCustomerStats(String customer_mail) {
        int i;
        for (i = 0; i < allUsers.size(); ++i) {
            if (allUsers.get(i).getMail().equals(customer_mail)) {
                java.lang.System.out.println(allUsers.get(i).toString());
                break;
            }
        }
    }

    public Customer findCustomer(User user) {
        if (this.allUsers.size() != 0) {
            for (int i = 0; i < allUsers.size(); i++) {
                if (user.getMail().equals(allUsers.get(i).getMail())) {
                    return (Customer) allUsers.get(i);
                }
            }
        }
        return null;
    }


    public Restaurant searchByRestaurantName(String restaurantName) {
        int i, j;
        for (i = 0; i < restaurantList.size(); ++i) {
            if (restaurantList.get(i).getName().equals(restaurantName)) {
                java.lang.System.out.println(this.toString() + " " + restaurantList.get(i).getName());
                return restaurantList.get(i);
            }
        }
        java.lang.System.out.println("There is no such a restaurant");
        return null;
    }

    public void displayAllRestaurant() {
        int i;
        for (i = 0; i < restaurantList.size(); ++i) {
            java.lang.System.out.println(i + 1 + "   " + "Restaurant Name : " + restaurantList.get(i).name);
            java.lang.System.out.println("     " + restaurantList.get(i).toString());
        }
    }

    public void displayAllRestaurantMenus() {
        for (int i = 0; i < restaurantList.size(); i++) {
            java.lang.System.out.println(restaurantList.get(i).getName() + ": ");
            restaurantList.get(i).printMenu();
        }
    }

    public void displayRestaurant(int index) throws Exception {
        if (index < 0 || index > restaurantList.size()) {
            throw new Exception("Out Of Bounds");
        } else {
            java.lang.System.out.println(restaurantList.get(index).toString());

        }
    }

    public Restaurant returnRestaurant(int index) {
        return restaurantList.get(index);

    }

}
