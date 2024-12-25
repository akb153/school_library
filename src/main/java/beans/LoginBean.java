package beans;

public class LoginBean {
	
	//社員属性(プロパティ)
	private String user_id;
	private String user_name;
	private String password;
	private String belong_high_school_id;
	private String belong_high_school_name;
	private String mail_address;
	private int lock_flg;
	private int admin_flg;
	private int librarian_flg;
	private int library_user_flg;
	private int library_committee_flg;
	private int transporter_flg;
	private int rental_flg;
	private int return_flg;
	private int delete_flg;
	
	public String getBelong_high_school_name() {
		return belong_high_school_name;
	}
	public void setBelong_high_school_name(String belong_high_school_name) {
		this.belong_high_school_name = belong_high_school_name;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBelong_high_school_id() {
		return belong_high_school_id;
	}
	public void setBelong_high_school_id(String belong_high_school_id) {
		this.belong_high_school_id = belong_high_school_id;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}

	public int getLock_flg() {
		return lock_flg;
	}
	public void setLock_flg(int lock_flg) {
		this.lock_flg = lock_flg;
	}
	public int getAdmin_flg() {
		return admin_flg;
	}
	public void setAdmin_flg(int admin_flg) {
		this.admin_flg = admin_flg;
	}
	public int getLibrarian_flg() {
		return librarian_flg;
	}
	public void setLibrarian_flg(int librarian_flg) {
		this.librarian_flg = librarian_flg;
	}
	public int getLibrary_user_flg() {
		return library_user_flg;
	}
	public void setLibrary_user_flg(int library_user_flg) {
		this.library_user_flg = library_user_flg;
	}
	public int getLibrary_committee_flg() {
		return library_committee_flg;
	}
	public void setLibrary_committee_flg(int library_committee_flg) {
		this.library_committee_flg = library_committee_flg;
	}
	public int getTransporter_flg() {
		return transporter_flg;
	}
	public void setTransporter_flg(int transporter_flg) {
		this.transporter_flg = transporter_flg;
	}
	public int getRental_flg() {
		return rental_flg;
	}
	public void setRental_flg(int rental_flg) {
		this.rental_flg = rental_flg;
	}
	public int getReturn_flg() {
		return return_flg;
	}
	public void setReturn_flg(int return_flg) {
		this.return_flg = return_flg;
	}
	public int getDelete_flg() {
		return delete_flg;
	}
	public void setDelete_flg(int delete_flg) {
		this.delete_flg = delete_flg;
	}
}
