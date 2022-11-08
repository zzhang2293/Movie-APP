package com.run;

import com.bean.Business;
import com.bean.Customer;
import com.bean.Movie;
import com.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiConsumer;

public class MovieSystem {
    /**
     * restore information use list
     *
     */
    public static final List<User> ALL_USERS = new ArrayList<>();
    /**
     * current user
     */
    public static User currentUser;
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final Logger LOGGER = LoggerFactory.getLogger("MovieSystem.class");

    /**
     *
     * store all movie information for each company
     */
    public static Map<Business, List<Movie>> ALL_MOVIES = new HashMap<>();

    static{
        Customer c = new Customer();
        c.setLoginName("zyf888");
        c.setPassWord("123456");
        c.setUserName("John");
        c.setPhone("12345678");
        ALL_USERS.add(c);
        Business b = new Business();
        b.setLoginName("zzhang2293");
        b.setPassWord("123123");
        b.setUserName("Ziye");
        b.setGender("Male");
        ALL_USERS.add(b);
        ALL_MOVIES.put(b, new ArrayList<>());


    }
    public static final Scanner SYS_SC = new Scanner(System.in);

    /**
     * welcome page
     *
     */
    private static void showMain(){
        while (true) {
        System.out.println("=====================================");
        System.out.println("1. Login");
        System.out.println("2. Customer setup");
        System.out.println("3. Business setup");
        System.out.println("4. quit");
        System.out.println("Type command: ");
            String command = SYS_SC.nextLine();
            switch(command){
                case "1":
                    // login
                    login();
                    break;
                case "2":
                    customerSetUp();
                    break;
                case "3":
                    businessSetUp();
                    break;
                case"4":
                    return;
                default:
                    System.out.println("Command error, try it again");
                    break;
            }
        }

    }

    private static void businessSetUp() {
        System.out.println("Login name: ");
        String name = SYS_SC.nextLine();
        System.out.println("User real Name: ");
        String realName = SYS_SC.nextLine();
        System.out.println("Gender: ");
        String gender = SYS_SC.nextLine();
        System.out.println("Phone number: ");
        String phone = SYS_SC.nextLine();
        System.out.println("Add how much money: ");
        String money = SYS_SC.nextLine();
        //String loginName, String userName, String gender, String phone, double money, String shopName, String address
        System.out.println("Shop name: ");
        String shopName = SYS_SC.nextLine();
        System.out.println("Address: ");
        String address = SYS_SC.nextLine();
        Business business = new Business(name, realName, gender, phone, Double.parseDouble(money), shopName, address);
        System.out.println("Set how much money");
        System.out.println("Password");
        String password = SYS_SC.nextLine();
        business.setPassWord(password);
        ALL_USERS.add(business);
        ALL_MOVIES.put(business, new ArrayList<>());
        System.out.println("create Successfully");



    }

    private static void customerSetUp() {
        //User(String loginName, String userName, String gender, String phone, double money)
        System.out.println("Start setup a new customer");
        System.out.println("Login name: ");
        String name = SYS_SC.nextLine();
        System.out.println("User real Name: ");
        String realName = SYS_SC.nextLine();
        System.out.println("Gender: ");
        String gender = SYS_SC.nextLine();
        System.out.println("Phone number: ");
        String phone = SYS_SC.nextLine();
        System.out.println("Add how much money: ");
        String money = SYS_SC.nextLine();
        Customer customer = new Customer(name, realName, gender, phone, Double.parseDouble(money));
        System.out.println("set password");
        String password = SYS_SC.nextLine();
        customer.setPassWord(password);
        ALL_USERS.add(customer);
        

    }

    private static void login() {
        User user;
        do {
            System.out.println("Login name: ");
            String loginName = SYS_SC.nextLine();
            System.out.println("Password: ");
            String password = SYS_SC.nextLine();
            user = getUserInfo(loginName, password);
        } while (user == null);
        currentUser = user;
        LOGGER.info(user.getUserName() + "login the system");
        if(user instanceof  Customer){
            showCustomerMain();
        }else{
            showBusinessMain();
        }

        // use the loginName to find the user object (Customer / Business)

    }

    /**
     * business UI
     */
    private static void showBusinessMain() {
        LOGGER.info(currentUser.getUserName() + "checking his movies...");
        System.out.println("==============Business UI==================");
        System.out.println((currentUser.getGender().equals("Male") ? "Mr. " : "Miss. ") + currentUser.getUserName()
                + "Welcome!");
        System.out.println("Choose operation");
        System.out.println("1. show detail");
        System.out.println("2. upload a movie");
        System.out.println("3. Remove a movie");
        System.out.println("4. Quit");
        while (true) {
            System.out.println("Please select the operation");
            String command = SYS_SC.nextLine();
            switch (command){
                case "1":
                    showBusinessInfo();
                    break;
                case "2":
                    addMovie();
                    break;
                case "3":
                    removeMovie();
                case "4":
                    System.out.println("Quit successfully");
                    return;
                default:
                    System.out.println("Command error, try again");
                    break;
            }
        }


    }

    private static void removeMovie() {
        List<Movie> movieLists = ALL_MOVIES.get((Business) currentUser);
        if (movieLists.size() == 0){
            System.out.println("Current movie list is empty");
            return;
        }
        System.out.println("Which movie do you want to remove?");
        String movieName = SYS_SC.nextLine();
        for (Movie movie : movieLists) {
            if (movie.getName().equals(movieName)){
                System.out.println("Remove Successfully");
                movieLists.remove(movie);
                return;
            }
        }
        System.out.println("Did not find the movie");
    }

    /**
     * add a new movie
     */
    private static void addMovie() {
        List<Movie> moveList = ALL_MOVIES.get((Business) currentUser);
        System.out.println("New movie name" );
        String name = SYS_SC.nextLine();
        System.out.println("Actor: ");
        String actor = SYS_SC.nextLine();
        System.out.println("How long: ");
        String time = SYS_SC.nextLine();
        System.out.println("Price: ");
        String price = SYS_SC.nextLine();
        System.out.println("Num of tickets");
        String totalNumber = SYS_SC.nextLine();
        while (true) {
            try {
                System.out.println("Start time");
                String stime = SYS_SC.nextLine();
                Movie movie = new Movie(name, actor, Integer.parseInt(time), Double.parseDouble(price)
                        , Integer.parseInt(totalNumber), simpleDateFormat.parse(stime));
                System.out.println("success upload" + movie.getName());
                moveList.add(movie);
                return;
            }catch (ParseException e){
                e.printStackTrace();
                LOGGER.error("error on get the start date when user wants to add a new movie");
            }
        }
    }

    /**
     * show current business info
     */
    private static void showBusinessInfo() {
        // use User object as key from the movies info ALL_MOVIES
        Business business = (Business) currentUser;
        System.out.println(business.getShopName() + "   phone number: " + business.getPhone() + "   address: " + business.getAddress());
        List<Movie> movies = ALL_MOVIES.get(business);
        if (movies.size() > 0) {
            for (Movie movie : movies) {
                System.out.println(movie.getName() + "   " + movie.getActor() + "   " + movie.getTime() +
                        "   " + movie.getSocre() + "   " + movie.getPrice() + "   " + movie.getNumber() + "   "
                        + simpleDateFormat.format(movie.getStartTime()));
            }
        }else{
            System.out.println("Movie is empty");
        }

    }

    /**
     * customer UI
     */
    private static void showCustomerMain() {
        System.out.println("=============Customer login===============");
        System.out.println("Choose services");
        System.out.println("1. show all movies");
        System.out.println("2. show specific movie by movie name");
        System.out.println("3. logout");
        while(true){
            System.out.println("Please select an option");
            String command = SYS_SC.nextLine();
            switch (command){
                case "1":
                    queryAllMovies();
                    break;
                case "2":
                    queryByName();
                case "3":
                    return;
                default:
                    System.out.println("Option does not exist");
            }
        }
    }



    private static void queryByName() {
        System.out.println("Movie Name: ");
        String name = SYS_SC.nextLine();
        for (Map.Entry<Business, List<Movie>> pairs : ALL_MOVIES.entrySet() ){
            for (Movie movies : pairs.getValue()){
                if (movies.getName().equals(name)){
                    while (true) {
                        System.out.println("Find the movie");
                        System.out.println("1. Check the characters");
                        System.out.println("2. Check the start time");
                        System.out.println("3. Check the time period");
                        System.out.println("4. buy a tickets");
                        System.out.println("5. rate the movie: ");
                        System.out.println("6. Quit");
                        System.out.println("Choose the operation: ");
                        String option = SYS_SC.nextLine();
                        switch (option){
                            case "1":
                                System.out.println("Actor name: " + movies.getActor());
                                break;
                            case "2":
                                System.out.println("Movie start time: " + movies.getStartTime().toString());
                                break;
                            case "3":
                                System.out.println("Time last: " + movies.getTime());
                                break;
                            case "4":
                                if ( currentUser.getMoney() >= movies.getPrice()){
                                    System.out.println("Buy ticket successfully");
                                    Customer current = (Customer) currentUser;
                                    current.setMoney(currentUser.getMoney() - movies.getPrice());
                                    current.addTickets(movies);
                                    pairs.getKey().setMoney(movies.getPrice());
                                }else{
                                    System.out.println("Do not have enough money");
                                }
                                break;
                            case "5":
                                System.out.println("Type the rate: ");
                                String rate = SYS_SC.nextLine();
                                movies.editScore(Double.parseDouble(rate));
                            case "6":
                                return;
                            default:
                                System.out.println("Did not find the operation, try again");
                        }
                    }

                }
            }
        }

    }

    private static void queryAllMovies() {
        int number = 1;
        for (Map.Entry<Business, List<Movie>> set : ALL_MOVIES.entrySet()) {
            for (Movie movie : set.getValue()) {
                System.out.println(number + " " + movie.getName());
                number ++;
            }
        }
    }

    private static User getUserInfo(String loginName, String password){
        User[] find = ALL_USERS.stream().filter(user -> user.getLoginName().equals(loginName)).toArray(User[]::new);
        if (find.length == 0){
            System.out.println("User does not exist");
            return null;
        }
        if (find[0].getPassWord().equals(password)){
            System.out.println("login success");
            return find[0];
        }else{
            System.out.println("Password does not match");
            return null;
        }
    }

    public static void main(String[] args) {

        showMain();


    }
}
