import model.Student;
import model.Admin;
import service.LibrarySystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibrarySystem system = new LibrarySystem();

        while (true) {
           System.out.println("\n========== 📚 LIBRARY MANAGEMENT SYSTEM ==========");
            System.out.println("1. Register as Student");
            System.out.println("2. Login as Student");
            System.out.println("3. Login as Admin");
            System.out.println("4. Exit");
            System.out.println("--------------------------------------------------");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    system.registerStudent(scanner);
                    break;
                case "2":
                    Student student = system.loginStudent(scanner);
                    if (student != null) {
                        while (true) {
                            student.showMenu();
                            System.out.print("Choose option: ");
                            String option = scanner.nextLine();

                            switch (option) {
                                case "1":
                                    system.searchBook(scanner);
                                    break;
                                case "2":
                                    system.borrowBook(student, scanner);
                                    break;
                                case "3":
                                    system.returnBook(student, scanner);
                                    break;
                                case "4":
                                    system.viewAllBooks(); // Already existing ✅
                                    break;
                                case "5":
                                    system.viewStudentBorrowedBooks(student); // New method 🆕
                                    break;
                                case "6":
                                    return;
                                default:
                                    System.out.println("❌ Invalid option");
                            }
                        }

                    }
                    break;
                case "3":
                    Admin admin = system.loginAdmin(scanner);
                    if (admin != null) {
                        admin.showMenu();
                        handleAdminActions(system, scanner);
                    }
                    break;
                case "4":
                    System.out.println("👋 Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice");
            }
        }
    }

    public static void handleAdminActions(LibrarySystem system, Scanner scanner) {
        while (true) {
            System.out.print("Admin Action: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    system.addBook(scanner);
                    break;
                case "2":
                    system.removeBook(scanner);
                    break;
                case "3":
                    system.viewAllBooks();
                    break;
                case "4":
                    return; // Logout
                default:
                    System.out.println("❌ Invalid option");
            }
        }
    }
}
