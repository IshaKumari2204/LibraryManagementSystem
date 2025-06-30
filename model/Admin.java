package model;

public class Admin extends User {

    public Admin(String userId, String name, String password) {
        super(userId, name, password);
    }

    @Override
    public void showMenu() {
        System.out.println("\n🧑‍💼 Admin Menu");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. View All Books");
        System.out.println("4. Logout");
    }

    // We'll add addBook(), removeBook(), etc. here later
}
