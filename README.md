# 📚 Library Management System (Java OOP Project)

A simple console-based **Library Management System** built using **Java**. It supports user registration, login, book borrowing, return, and fine calculation. Designed with clean **Object-Oriented Programming principles** and file-based data persistence.

---

## 🔧 Features

### 👩‍🎓 Student Functionality
- Register and login securely
- Search books by title or author
- Borrow available books
- Return books with fine calculated (₹5/day after 14 days)
- View all available books in library
- View your borrowed books and fine status

### 👨‍💼 Admin Functionality
- Login using secure credentials
- Add new books to the library
- Remove available books from the library
- View complete book inventory

---

## 🛠 Tech Stack

- **Language:** Java
- **Concepts:** OOP (Abstraction, Encapsulation, Inheritance, Polymorphism)
- **Storage:** File Handling (`BufferedReader`, `BufferedWriter`)
- **Date Operations:** `LocalDate`, `ChronoUnit` for fine calculation
- **Design:** Clean separation of concerns (Model, Service, Utility)

---

## 📁 Project Structure

LibraryManagementSystem/
├── model/ # Data classes like Book, User, Student, Admin, BorrowRecord
├── service/ # Core system logic and file management
├── data/ # Text files storing users, books, and borrow records
├── Main.java # Program entry point
└── README.md # This file

## 🧪Admin Login
Used a single admin system , for now its hardcoded .
Admin ID: admin
Password : admin123


## 🧪 Sample Student Login Flow

Enter Name: Isha Kumari
Enter ID: stu1001
Enter Password: *****
✅ Student registered successfully!

--- Student Menu ---

1.Search Book
2.Borrow Book
3.Return Book
4.View All Books
5.View Your Borrowed Books
6.Logout

## 📂 Data Files (auto-generated if not present)

- `data/books.txt`
- `data/students.txt`
- `data/borrow_records.txt`

Each is a simple comma-separated file for persistent storage.

---

## 💬 Future Improvements (optional ideas)

- GUI using JavaFX or Swing
- Admin dashboard with statistics
- Password encryption
- Multiple user roles (Teacher, Librarian)

---

## 🚀 How to Run

```bash
javac Main.java
java Main
Or use VS Code or IntelliJ to run Main.java.
