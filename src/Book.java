import java.io.Serial;
import java.io.Serializable;

public class Book implements Serializable {
    public void setBookStock(int bookStock) {
        this.bookStock = bookStock;
    }

    private String bookTitle;
    private int bookID;
    private String bookCatagory;
    private int bookPrice;
    private int bookStock;
    public Book(){

    }
    public Book(String bookTitle, int bookID, String bookCatagory, int bookPrice, int bookStock) {
        this.bookTitle = bookTitle;
        this.bookID = bookID;
        this.bookCatagory = bookCatagory;
        this.bookPrice = bookPrice;
        this.bookStock = bookStock;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getBookID() {
        return bookID;
    }

    public String getBookCatagory() {
        return bookCatagory;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public int getBookStock() {
        return bookStock;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookID +" | Book Name: " + bookTitle + " | BookCatagory: " + bookCatagory +" | bookPrice: " + bookPrice + " | bookStock=" + bookStock;
    }
}
