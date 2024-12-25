package beans;

import java.sql.Date;

public class ReservingBookBean {
	
	//社員属性(プロパティ)
	private String reserving_id;
	private String user_id;
	private String user_name;
	private String mail_address;
	private String user_belonging_school;
	private String reserving_book;
	private String title;
	private Date reserving_date;
	private Date deadline_for_receiving;
	private String book_belonging_school;
	private String call_number;
	private boolean borrowing_flg;	
	
	public boolean isBorrowing_flg() {
		return borrowing_flg;
	}
	public void setBorrowing_flg(boolean borrowing_flg) {
		this.borrowing_flg = borrowing_flg;
	}
	public String getCall_number() {
		return call_number;
	}
	public void setCall_number(String call_number) {
		this.call_number = call_number;
	}
	private int row_num;
	
	public int getRow_num() {
		return row_num;
	}
	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}
	public String getReserving_id() {
		return reserving_id;
	}
	public void setReserving_id(String reserving_id) {
		this.reserving_id = reserving_id;
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
	public String getReserving_book() {
		return reserving_book;
	}
	public void setReserving_book(String reserving_book) {
		this.reserving_book = reserving_book;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getReserving_date() {
		return reserving_date;
	}
	public void setReserving_date(Date reserving_date) {
		this.reserving_date = reserving_date;
	}
	public Date getDeadline_for_receiving() {
		return deadline_for_receiving;
	}
	public void setDeadline_for_receiving(Date deadline_for_receiving) {
		this.deadline_for_receiving = deadline_for_receiving;
	}
	public String getBook_belonging_school() {
		return book_belonging_school;
	}
	public void setBook_belonging_school(String book_belonging_school) {
		this.book_belonging_school = book_belonging_school;
	}

}