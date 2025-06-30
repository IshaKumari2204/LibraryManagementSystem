package model;

public abstract class User {
    protected String userId;
    protected String name;
    protected String password;

    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
    return password;
    }   

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public abstract void showMenu(); // Will be implemented differently by Student and Admin
}
