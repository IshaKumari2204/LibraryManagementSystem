package model;

public class BorrowRecord {
    private String studentId;
    private String bookId;
    private String borrowDate;
    private String returnDate; // "null" if not returned

    public BorrowRecord(String studentId, String bookId, String borrowDate, String returnDate) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getStudentId() { return studentId; }
    public String getBookId() { return bookId; }
    public String getBorrowDate() { return borrowDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return studentId + "," + bookId + "," + borrowDate + "," + returnDate;
    }

    public static BorrowRecord fromString(String line) {
        String[] parts = line.split(",");
        return new BorrowRecord(parts[0], parts[1], parts[2], parts[3]);
    }
}
