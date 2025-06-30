package model;

public class Student extends User {

    public Student(String userId, String name, String password) {
        super(userId, name, password);
    }

    @Override
    public void showMenu() {
    System.out.println("\n--- 👩‍🎓 Student Menu ---");
    System.out.println("1. Search Book");
    System.out.println("2. Borrow Book");
    System.out.println("3. Return Book");
    System.out.println("4. View All Books");         
    System.out.println("5. View Your Borrowed Books");
    System.out.println("6. Logout");
    System.out.print("Choose option: ");
    }

   
}
