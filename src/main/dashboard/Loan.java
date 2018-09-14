package main.dashboard;

public class Loan {
    private int issueID;
    private int bookID;
    private String userID;
    private String issueDate;

    public Loan(int issueID, int bookID, String userID, String issueDate) {
        this.issueID = issueID;
        this.bookID = bookID;
        this.userID = userID;
        this.issueDate = issueDate;
    }

    public int getIssueID() {
        return issueID;
    }

    public int getBookID() {
        return bookID;
    }

    public String getUserID() {
        return userID;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
}
