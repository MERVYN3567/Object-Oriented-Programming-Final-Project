// User.java
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Book> recommendedBooks;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.recommendedBooks = new ArrayList<>();
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public ArrayList<Book> getRecommendedBooks() { return recommendedBooks; }

    public void addBook(Book book) { 
        recommendedBooks.add(book); 
    }

    public void displayRecommendations() {
        System.out.println("----------------------------");
        System.out.println("Recommendations for user: " + username);
        for (Book book : recommendedBooks) {
            book.displayInfo();
            System.out.println();
        }
    }
}