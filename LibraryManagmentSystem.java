package LibraryProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import LibraryProject.ConnectionDB;

public class LibraryManagmentSystem {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    	LibraryManagmentSystem app = new LibraryManagmentSystem();
        app.menu();
    }

    public void menu() {
        while (true) { // Keep showing menu
            System.out.println("\n=====================Enter your choice:========================");
            System.out.println("======================1: Create books table======================");
            System.out.println("======================2: View all books==========================");
            System.out.println("======================3: Add a book==============================");
            System.out.println("======================4: Delete a book===========================");
            System.out.println("======================5: Update a book===========================");
            System.out.println("======================6: Fetch book by ID========================");
            System.out.println("======================7: Exit====================================");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: createTable(); break;
                case 2: viewAllBooks(); break;
                case 3: addBook(); break;
                case 4: deleteBook(); break;
                case 5: updateBook(); break;
                case 6: fetchBookById(); break;
                case 7: System.out.println("Thank you 😊"); System.exit(0); break;
                default: System.out.println("Invalid choice"); break;
            }
        }
    }

    // Create books table
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS books (" +
                     "bookId VARCHAR(30) PRIMARY KEY," +
                     "title VARCHAR(100)," +
                     "author VARCHAR(50)," +
                     "publisher VARCHAR(50)," +
                     "year INT)";
        try (Connection con = ConnectionDB.dbConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.execute();
            System.out.println("Books table created successfully 😊");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add book
    public void addBook() {
        String sql = "INSERT INTO books VALUES (?,?,?,?,?)";
        try (Connection con = ConnectionDB.dbConnect(); PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.println("Enter Book ID:");
            String bid = sc.next();
            sc.nextLine(); // clear buffer
            System.out.println("Enter Title:");
            String title = sc.nextLine();
            System.out.println("Enter Author:");
            String author = sc.nextLine();
            System.out.println("Enter Publisher:");
            String publisher = sc.nextLine();
            System.out.println("Enter Year:");
            int year = sc.nextInt();

            ps.setString(1, bid);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setString(4, publisher);
            ps.setInt(5, year);

            int count = ps.executeUpdate();
            if (count > 0) System.out.println("Book added successfully!");
            else System.out.println("Failed to add book.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View all books
    public void viewAllBooks() {
        String sql = "SELECT * FROM books";
        try (Connection con = ConnectionDB.dbConnect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getString(1) +
                                   ", Title: " + rs.getString(2) +
                                   ", Author: " + rs.getString(3) +
                                   ", Publisher: " + rs.getString(4) +
                                   ", Year: " + rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete book
    public void deleteBook() {
        String sql = "DELETE FROM books WHERE bookId=?";
        try (Connection con = ConnectionDB.dbConnect(); PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.println("Enter Book ID to delete:");
            String bid = sc.next();
            ps.setString(1, bid);

            int count = ps.executeUpdate();
            if (count > 0) System.out.println("Book deleted successfully!");
            else System.out.println("No book found with that ID.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update book
    public void updateBook() {
        String sql = "UPDATE books SET title=?, author=?, publisher=?, year=? WHERE bookId=?";
        try (Connection con = ConnectionDB.dbConnect(); PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.println("Enter Book ID to update:");
            String bid = sc.next();
            sc.nextLine(); // clear buffer
            System.out.println("Enter new Title:");
            String title = sc.nextLine();
            System.out.println("Enter new Author:");
            String author = sc.nextLine();
            System.out.println("Enter new Publisher:");
            String publisher = sc.nextLine();
            System.out.println("Enter new Year:");
            int year = sc.nextInt();

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, publisher);
            ps.setInt(4, year);
            ps.setString(5, bid);

            int count = ps.executeUpdate();
            if (count > 0) System.out.println("Book updated successfully!");
            else System.out.println("No book found with that ID.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fetch book by ID
    public void fetchBookById() {
        String sql = "SELECT * FROM books WHERE bookId=?";
        try (Connection con = ConnectionDB.dbConnect(); PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.println("Enter Book ID to fetch:");
            String bid = sc.next();
            ps.setString(1, bid);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getString(1) +
                                   ", Title: " + rs.getString(2) +
                                   ", Author: " + rs.getString(3) +
                                   ", Publisher: " + rs.getString(4) +
                                   ", Year: " + rs.getInt(5));
            } else {
                System.out.println("No book found with that ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
