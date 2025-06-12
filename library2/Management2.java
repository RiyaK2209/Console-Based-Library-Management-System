package library2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Book{
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author, boolean isAvailable){
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString(){
        return "Book[title=" + title + ", author =" + author + ", isAvailable= " + isAvailable + "]";
    }
}

class Library{
    private ArrayList<Book> books = new ArrayList<>();
   private HashMap<String, ArrayList<Book>> borrowedBooks = new HashMap<>();
   public void addBook(String title, String author) {
       Book newBook = new Book(title, author, true);
       books.add(newBook);
       System.out.println("Book added successfully!");
   }
   public void displayBooks() {
       if (books.isEmpty()) {
           System.out.println("Books are not available.");
       } else {
           System.out.println("Following books are available in the library:");
           for (Book book : books) {
               System.out.println(book);
           }
       }
   }
   public void borrowBook(String title, String user) {
       for (Book book : books) {
           if (book.getTitle().equalsIgnoreCase(title)) {
               if (book.isAvailable()) {
                   book.setAvailable(false);
                   borrowedBooks.putIfAbsent(user, new ArrayList<>());
                   borrowedBooks.get(user).add(book);
                   System.out.println(user + ", you have successfully borrowed the book - " + title);
               } else {
                   System.out.println("Sorry, the book is currently borrowed.");
               }
               return;
           }
       }
       System.out.println("Book not found in the library.");
   }
   public void returnBook(String title, String user) {
       if (borrowedBooks.containsKey(user)) {
           ArrayList<Book> userBooks = borrowedBooks.get(user);
           for (Book book : userBooks) {
               if (book.getTitle().equalsIgnoreCase(title)) {
                   book.setAvailable(true);
                   userBooks.remove(book);
                   System.out.println(user + ", you have successfully returned the book - " + title);
                   return;
               }
           }
       }
       System.out.println("Either the book wasn't borrowed or the user name is incorrect.");
   }
   public void showBorrowedBooks(String user) {
       if (borrowedBooks.containsKey(user) && !borrowedBooks.get(user).isEmpty()) {
           System.out.println(user + " has borrowed the following books:");
           for (Book book : borrowedBooks.get(user)) {
               System.out.println(book);
           }
       } else {
           System.out.println(user + " has not borrowed any books.");
       }
   }
}
public class Management2 {
   private static Scanner scanner = new Scanner(System.in);
   public static void main(String[] args) {
       Library library = new Library();
       boolean exit = false;
       while (!exit) {
           System.out.println("\n=== Library Management System ===");
           System.out.println("1. Add a New Book");
           System.out.println("2. Display All Books");
           System.out.println("3. Borrow a Book");
           System.out.println("4. Return a Book");
           System.out.println("5. View My Borrowed Books");
           System.out.println("6. Exit");
           System.out.print("Enter your choice: ");
           int choice = scanner.nextInt();
           scanner.nextLine();  // consume newline
           switch (choice) {
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
                   viewUserBorrowedBooks(library);
                   break;
               case 6:
                   exit = true;
                   System.out.println("Exiting the library...");
                   break;
               default:
                   System.out.println("Invalid choice. Try again.");
           }
       }
   }
   private static void addBookToLib(Library library) {
       System.out.print("Enter the title of the book: ");
       String title = scanner.nextLine();
       System.out.print("Enter the author's name: ");
       String author = scanner.nextLine();
       library.addBook(title, author);
   }
   private static void borrowBookFromLib(Library library) {
       System.out.print("Enter your name: ");
       String user = scanner.nextLine();
       System.out.print("Enter the title of the book to borrow: ");
       String title = scanner.nextLine();
       library.borrowBook(title, user);
   }
   private static void returnBookToLibrary(Library library) {
       System.out.print("Enter your name: ");
       String user = scanner.nextLine();
       System.out.print("Enter the title of the book to return: ");
       String title = scanner.nextLine();
       library.returnBook(title, user);
   }
   private static void viewUserBorrowedBooks(Library library) {
       System.out.print("Enter your name: ");
       String user = scanner.nextLine();
       library.showBorrowedBooks(user);
   }
}