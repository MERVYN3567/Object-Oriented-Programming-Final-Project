// Book.java
public abstract class Book { //abstract class
    protected String title;
    protected String author;
    protected String genre;
    protected int year;
    protected Ratings ratings;
    protected Publish publish;

    public Book() {}

    public Book(String title, String author, String genre, int year, Ratings ratings, Publish publish) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.ratings = ratings;
        this.publish = publish;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public Ratings getRatings() { return ratings; }
    public void setRatings(Ratings ratings) { this.ratings = ratings; }

    public Publish getPublisher() { return publish; }
    public void setPublisher(Publish publish) { this.publish = publish; }

    public abstract void displayInfo();
}