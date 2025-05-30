package library.management;

import java.util.ArrayList;
import java.util.Scanner;

class Book{
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    private String author;
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    private boolean isAvailable;
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    
    
    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", isAvailable=" + isAvailable + "]";
    }
    public Book(String title, String author, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }
    
   
}

class Library{
    private ArrayList<Book> books = new ArrayList<>();
    public void addBook(String title, String author){
        Book newBook = new Book(title, author, true);
        books.add(newBook);
        System.out.println("Book added successfully!");
    }

    public void displayBooks(){
        if (books.isEmpty()) {
            System.out.println("Books are not available.");
            
        } else{
            System.out.println("Following books are available in the library:");
            for(Book book : books){
                System.out.println(book);
            }
        }
    }
    public void borrowBooks(String title){
        for(Book book : books){
            if(book.getTitle().equalsIgnoreCase(title)){
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("You have successfully borrowed the book- " + title);
                } else{
                    System.out.println("Sorry, the book you are looking for is currently borrowed!");
                }
                return;
            }
        }
        System.out.println("Book not found in the library!");
    }
    public void returnBook(String title){
        for(Book book:books){
            if(book.getTitle().equalsIgnoreCase(title)){
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("You have successfully returned the book- " + title);
                } else{
                    System.out.println("This book wasn't borrowed.");
                }
                return;
            }
        }
        System.out.println("Book not found in the library.");
    }
}

public class Management {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Library library = new Library();
        boolean exit = false;
        while(!exit){
            System.out.println("\n===Library Management System===");
            System.out.println("1. Add a new Book");
            System.out.println("2. Display all the Books");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){ 
                case 1:
                addBookToLib(library);
                break;
                case 2:
                library.displayBooks();
                break;
                case 3:
                borrowBookFromLib(library);
                break;
                case 4:
                returnBookToLibrary(library);
                break;
                case 5:
                exit = true;
                System.out.println("Exiting the library");
                break;

            default:
                System.out.println("Invalid choice. Try again later.");
            }
        }
    }

    private static void addBookToLib(Library library){
        System.out.println("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.println("Enter the author's name");
        String author = scanner.nextLine();
        library.addBook(title, author);
    }

    private static void borrowBookFromLib(Library library){
        System.out.println("Enter the title of the book to borrow: ");
        String title = scanner.nextLine();
        library.borrowBooks(title);
    }
    
    private static void returnBookToLibrary(Library library){
        System.out.println("Enter the title of the book to return: ");
        String title = scanner.nextLine();
        library.returnBook(title);
    }
}