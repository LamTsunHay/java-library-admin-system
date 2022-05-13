public class Book {
    private String title;
    private String ISBN;
    private boolean available;
    private MyQueue<String> reservedQueue;


    public Book(){
        available = true;
        reservedQueue = new MyQueue<>();
    }
    public void setTitle(String bookTitle){
        title = bookTitle;
    }
    public String getTitle(){
        return title;
    }
    public void setISBN(String bookISBN){
        ISBN = bookISBN;
    }
    public String getISBN(){
        return ISBN;
    }
    public void setAvailable(boolean availablity){
        available=availablity;
    }
    public boolean isAvailable(){
        return available;
    }

    public MyQueue<String> getReservedQueue(){
        return reservedQueue;
    }
}
