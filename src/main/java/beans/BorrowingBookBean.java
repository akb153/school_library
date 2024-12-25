package beans;

import java.sql.Date;

public class BorrowingBookBean {
	
	//社員属性(プロパティ)
	private String borrowing_id;
	private String user_id;
	private String user_name;
	private String mail_address;
	private String user_belonging_school;
	private String borrowing_book;
	private String title;
	private Date borrowing_start_date;
	private Date borrowing_end_date;
	private String book_belonging_school;
	
	public String getBorrowing_id() {
		return borrowing_id;
	}
	public void setBorrowing_id(String borrowing_id) {
		this.borrowing_id = borrowing_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	public String getUser_belonging_school() {
		return user_belonging_school;
	}
	public void setUser_belonging_school(String user_belonging_school) {
		this.user_belonging_school = user_belonging_school;
	}
	public String getBorrowing_book() {
		return borrowing_book;
	}
	public void setBorrowing_book(String borrowing_book) {
		this.borrowing_book = borrowing_book;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getBorrowing_start_date() {
		return borrowing_start_date;
	}
	public void setBorrowing_start_date(Date borrowing_start_date) {
		this.borrowing_start_date = borrowing_start_date;
	}
	public Date getBorrowing_end_date() {
		return borrowing_end_date;
	}
	public void setBorrowing_end_date(Date borrowing_end_date) {
		this.borrowing_end_date = borrowing_end_date;
	}
	public String getBook_belonging_school() {
		return book_belonging_school;
	}
	public void setBook_belonging_school(String book_belonging_school) {
		this.book_belonging_school = book_belonging_school;
	}
	

}