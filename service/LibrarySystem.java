package service;

import model.Book;
import model.BorrowRecord;
import model.Student;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LibrarySystem {
    private List<Student> students;

    public LibrarySystem() {
        this.students = FileManager.loadStudents();
    }

    // Register new student
    public void registerStudent(Scanner scanner) {
        System.out.println("\n--- Student Registration ---");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        // Check if ID already exists
        for (Student s : students) {
            if (s.getUserId().equals(id)) {
                System.out.println("❌ ID already registered.");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Student newStudent = new Student(id, name, password);
        students.add(newStudent);
        FileManager.saveStudents(students);
        System.out.println("✅ Registered successfully!");
    }

    // Student Login
    public Student loginStudent(Scanner scanner) {
        System.out.println("\n--- Student Login ---");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (Student s : students) {
            if (s.getUserId().equals(id) && s.checkPassword(password)) {
                System.out.println("✅ Login successful!");
                return s;
            }
        }

        System.out.println("❌ Invalid credentials.");
        return null;
    }

    // Admin Login
    public model.Admin loginAdmin(Scanner scanner) {
        System.out.println("\n--- Admin Login ---");
        System.out.print("Enter Admin ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // For now, hardcode one admin user
        if (id.equals("admin") && password.equals("admin123")) {
            System.out.println("✅ Admin login successful!");
            return new model.Admin(id, "Admin", password);
        }

        System.out.println("❌ Invalid admin credentials");
        return null;
    }

    public void addBook(Scanner scanner) {
        List<Book> books = FileManager.loadBooks();

        System.out.println("\n--- Add Book ---");
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();

        // Check if book already exists
        for (Book b : books) {
            if (b.getId().equals(id)) {
                System.out.println("❌ Book ID already exists.");
                return;
            }
        }

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        Book newBook = new Book(id, title, author, true);
        books.add(newBook);
        FileManager.saveBooks(books);
        System.out.println("✅ Book added successfully!");
    }

    public void removeBook(Scanner scanner) {
        List<Book> books = FileManager.loadBooks();

        System.out.println("\n--- Remove Book ---");
        System.out.print("Enter Book ID to remove: ");
        String id = scanner.nextLine();

        // Step 1: Find the book
        Book toRemove = null;
        for (Book b : books) {
            if (b.getId().equals(id)) {
                toRemove = b;
                break;
            }
        }

        // Step 2: If not found
        if (toRemove == null) {
            System.out.println("❌ Book ID not found.");
            return;
        }

        // Step 3: If book is borrowed (not available)
        if (!toRemove.isAvailable()) {
            System.out.println("⚠️ Cannot remove. Book is currently borrowed by a student.");
            return;
        }

        // Step 4: Remove if it's available
        books.remove(toRemove);
        FileManager.saveBooks(books);
        System.out.println("✅ Book removed successfully.");
    }

    public void viewAllBooks() {
        List<Book> books = FileManager.loadBooks();

        System.out.println("\n📚 All Books:");
        for (Book b : books) {
            System.out.println(
                    b.getId() + " | " + b.getTitle() + " | " + b.getAuthor() + " | Available: " + b.isAvailable());
        }
    }

    // borrow book
    public void borrowBook(Student student, Scanner scanner) {
        List<Book> books = FileManager.loadBooks();
        List<BorrowRecord> records = FileManager.loadBorrowRecords();

        System.out.print("Enter Book ID to borrow: ");
        String bookId = scanner.nextLine();

        for (Book b : books) {
            if (b.getId().equals(bookId)) {
                if (!b.isAvailable()) {
                    System.out.println("❌ Book already borrowed.");
                    return;
                }

                b.setAvailable(false);
                BorrowRecord r = new BorrowRecord(student.getUserId(), bookId, LocalDate.now().toString(), "null");
                records.add(r);
                FileManager.saveBooks(books);
                FileManager.saveBorrowRecords(records);
                System.out.println("✅ Book borrowed successfully!");
                return;
            }
        }

        System.out.println("❌ Book not found.");
    }

    // Return book
    public void returnBook(Student student, Scanner scanner) {
        List<Book> books = FileManager.loadBooks();
        List<BorrowRecord> records = FileManager.loadBorrowRecords();

        System.out.print("Enter Book ID to return: ");
        String bookId = scanner.nextLine();

        for (BorrowRecord r : records) {
            if (r.getBookId().equals(bookId) && r.getStudentId().equals(student.getUserId())
                    && r.getReturnDate().equals("null")) {
                r.setReturnDate(LocalDate.now().toString());

                // Mark book as available again
                for (Book b : books) {
                    if (b.getId().equals(bookId)) {
                        b.setAvailable(true);
                        break;
                    }
                }

                // Calculate fine
                long days = ChronoUnit.DAYS.between(LocalDate.parse(r.getBorrowDate()), LocalDate.now());
                long fine = (days > 14) ? (days - 14) * 5 : 0;

                FileManager.saveBooks(books);
                FileManager.saveBorrowRecords(records);

                System.out.println("✅ Book returned successfully!");
                if (fine > 0) {
                    System.out.println("💸 Fine: ₹" + fine + " (kept for " + days + " days)");
                } else {
                    System.out.println("✅ No fine. Returned on time.");
                }
                return;
            }
        }

        System.out.println("❌ No active borrow record found.");
    }

    public void searchBook(Scanner scanner) {
        List<Book> books = FileManager.loadBooks();
        System.out.print("🔍 Enter book title or author to search: ");
        String query = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(query) || b.getAuthor().toLowerCase().contains(query)) {
                System.out.println("✅ Found: " + b.getId() + " | " + b.getTitle() + " | " + b.getAuthor()
                        + " | Available: " + b.isAvailable());
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ No books matched your search.");
        }
    }

    // view your borrow records
    public void viewStudentBorrowedBooks(Student student) {
        List<BorrowRecord> records = FileManager.loadBorrowRecords();
        List<Book> books = FileManager.loadBooks();

        boolean hasRecords = false;

        System.out.println("\n📘 Books borrowed by " + student.getName() + ":");

        for (BorrowRecord r : records) {
            if (r.getStudentId().equals(student.getUserId()) && r.getReturnDate().equals("null")) {
                hasRecords = true;

                // Get book title from bookId
                String bookTitle = "";
                for (Book b : books) {
                    if (b.getId().equals(r.getBookId())) {
                        bookTitle = b.getTitle();
                        break;
                    }
                }

                String borrowDate = r.getBorrowDate();
                long daysKept = ChronoUnit.DAYS.between(LocalDate.parse(borrowDate), LocalDate.now());
                long fine = (daysKept > 14) ? (daysKept - 14) * 5 : 0;

                System.out.println("📗 Book: " + bookTitle);
                System.out.println("   📅 Borrowed On: " + borrowDate);
                System.out.println("   ⏱️ Days Kept: " + daysKept);
                System.out.println("   💸 Fine So Far: ₹" + fine);
                System.out.println("--------------------------------------------------");
            }
        }

        if (!hasRecords) {
            System.out.println("😇 You have no borrowed books right now!");
        }
    }

}
