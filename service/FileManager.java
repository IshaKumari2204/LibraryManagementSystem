package service;

import model.Book;
import model.BorrowRecord;
import model.Student;

import java.io.*;
import java.util.*;

public class FileManager {
    private static final String BOOKS_FILE = "data/books.txt";

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                books.add(Book.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading books: " + e.getMessage());
        }
        return books;
    }

    public static void saveBooks(List<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book b : books) {
                bw.write(b.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing books: " + e.getMessage());
        }
    }

    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Student s = new Student(parts[0], parts[1], parts[2]); // id, name, pass
                students.add(s);
            }
        } catch (IOException e) {
            System.out.println("Error reading students: " + e.getMessage());
        }
        return students;
    }

    public static void saveStudents(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/students.txt"))) {
            for (Student s : students) {
                bw.write(s.getUserId() + "," + s.getName() + "," + s.getPassword()); // protected access
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public static List<BorrowRecord> loadBorrowRecords() {
        List<BorrowRecord> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/borrow_records.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(BorrowRecord.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading borrow records: " + e.getMessage());
        }
        return records;
    }

    public static void saveBorrowRecords(List<BorrowRecord> records) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/borrow_records.txt"))) {
            for (BorrowRecord r : records) {
                bw.write(r.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving borrow records: " + e.getMessage());
        }
    }

}
