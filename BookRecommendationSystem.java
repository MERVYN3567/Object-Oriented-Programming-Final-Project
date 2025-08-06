// BookRecommendationSystem.java
import java.util.*;
import java.io.*;

public class BookRecommendationSystem {
    private ArrayList<User> users;
    private ArrayList<Book> fictionBooks;
    private ArrayList<Book> nonFictionBooks;
    private Scanner input;

    public BookRecommendationSystem() { //Constructor
        users = new ArrayList<>();
        fictionBooks = new ArrayList<>();
        nonFictionBooks = new ArrayList<>();
        input = new Scanner(System.in);
    }

    public void loadUsers(String filename) { //infile users.txt to gain username and password
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 2) {
                    users.add(new User(parts[0], parts[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User data file not found. Starting fresh.");
        }
    }

    public void saveUsers(String filename) { //user signup
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            for (User user : users) {
                writer.println(user.getUsername() + "," + user.getPassword());
            }
        } catch (IOException e) {
            System.out.println("Error saving users.");
        }
    }

    public void loadBooks() {
        fictionBooks.clear();
        nonFictionBooks.clear();

        try (Scanner scanner = new Scanner(new File("fiction.txt"))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 7) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    String genre = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    String publisher = parts[4].trim();
                    double rating = Double.parseDouble(parts[5].trim());
                    String subGenre = parts[6].trim();

                    fictionBooks.add(new Fiction(title, author, genre, year, new Ratings(rating), new Publish(publisher), subGenre));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("fiction.txt not found");
        }

        try (Scanner scanner = new Scanner(new File("nonfiction.txt"))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 7) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    String genre = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    String publisher = parts[4].trim();
                    double rating = Double.parseDouble(parts[5].trim());
                    String field = parts[6].trim();

                    nonFictionBooks.add(new NonFiction(title, author, genre, year, new Ratings(rating), new Publish(publisher), field));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("nonfiction.txt not found");
        }
    }

    public User login() { //user login
        User user = null;
        while (user == null) {
            System.out.print("Enter username: ");
            String username = input.nextLine();
            System.out.print("Enter password: ");
            String password = input.nextLine();

            for (User u : users) {
                if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                    System.out.println("Login successful!");
                    user = u;
                    break;
                }
            }

            if (user == null) {
                System.out.println("Invalid credentials. Try again.");
            }
        }
        return user;
    }

    public void signUp() { //user signup
        System.out.print("Choose a username: ");
        String username = input.nextLine();
        System.out.print("Choose a password: ");
        String password = input.nextLine();

        users.add(new User(username, password));
        System.out.println("Account created successfully!");
    }

    public void recommendFiction(User user) {
    System.out.println("Enter your preferred type:");
    System.out.println("(action, fantasy, mystery, romance, science fiction, horror, dystopian)");
    String type = input.nextLine().trim().toLowerCase();

    boolean found = false;
    for (Book book : fictionBooks) {
        if (book instanceof Fiction) {
            Fiction f = (Fiction) book;
            if (f.getSubGenre().trim().equalsIgnoreCase(type)) {
                user.addBook(f);
                found = true;
            }
        }
    }

    if (!found) {
        System.out.println("No fiction books found for type: " + type);
    }
}

public void recommendNonFiction(User user) {
    System.out.println("Enter your preferred type:");
    System.out.println("(philosophy, biography, religion, spirituality, autobiography, memoir, psychology, success)");
    String type = input.nextLine().trim().toLowerCase();

    boolean found = false;
    for (Book book : nonFictionBooks) {
        if (book instanceof NonFiction) {
            NonFiction nf = (NonFiction) book;
            if (nf.getField().trim().equalsIgnoreCase(type)) {
                user.addBook(nf);
                found = true;
            }
        }
    }

    if (!found) {
        System.out.println("No nonfiction books found for type: " + type);
    }
}


   public void recommendBooks(User user) {
    String genre;

    do {
        System.out.print("Enter your favorite genre (fiction/nonfiction): ");
        genre = input.nextLine().trim().toLowerCase();

        switch (genre) {
            case "fiction":
                recommendFiction(user);
                return; // exit the method after valid input
            case "nonfiction":
                recommendNonFiction(user);
                return;
            default:
                System.out.println("Invalid genre. Please enter 'fiction' or 'nonfiction'.");
        }
    } while (true); // loop continues until a valid genre is entered
}




    public void run() { //main
        loadUsers("users.txt");
        loadBooks();
        System.out.println("Welcome to the Book Recommendation System");
        System.out.print("Do you want to login or sign up? (login/signup): ");
        String choice = input.nextLine();

        User currentUser = null;
        if (choice.equalsIgnoreCase("login")) {
            currentUser = login();
        } else if (choice.equalsIgnoreCase("signup")) {
            signUp();
            saveUsers("users.txt");
            currentUser = login();
        }

        if (currentUser != null) { //Ask if user wants to continue or exit the program
            boolean continueLoop = true;
            while (continueLoop) {
                recommendBooks(currentUser);
                currentUser.displayRecommendations();
                System.out.print("Enter 0 to exit, any other key to continue: ");
                String decision = input.nextLine();

                if (decision.equals("0")) {
                    continueLoop = false;
                    System.out.println("Thank you for using the system");
                }
            }
        }

        saveUsers("users.txt");
    }

    public static void main(String[] args) {
        BookRecommendationSystem system = new BookRecommendationSystem();
        system.run();
    }
}