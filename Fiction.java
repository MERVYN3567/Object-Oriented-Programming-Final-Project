// Fiction.java
public class Fiction extends Book implements Displayable {
    private String subGenre;

    public Fiction(String title, String author, String genre, int year, Ratings ratings, Publish publish, String subGenre) {
        super(title, author, genre, year, ratings, publish);
        this.subGenre = subGenre;
}


    public String getSubGenre() { return subGenre; }

    public void setSubGenre(String type) { 
        this.subGenre = type; 
    }

    @Override
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Year: " + year);
        System.out.println("Publisher: " + publish.getPublisher());
        System.out.println("Average Rating: " + ratings.getAverageRating());
        System.out.println("Type: " + subGenre);
    }
}