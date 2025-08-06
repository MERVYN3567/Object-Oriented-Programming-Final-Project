// NonFiction.java
public class NonFiction extends Book implements Displayable {
    private String field;

    public NonFiction(String title, String author, String genre, int year, Ratings ratings, Publish publish, String field) {
        super(title, author, genre, year, ratings, publish);
        this.field = field;
    }

    public String getField() { return field; }
    
    public void setField(String type) { 
        this.field = type; 
    }

    @Override
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Year: " + year);
        System.out.println("Publisher: " + publish.getPublisher());
        System.out.println("Average Rating: " + ratings.getAverageRating());
        System.out.println("Type: " + field);
    }
}