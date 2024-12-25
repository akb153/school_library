package beans;

public class BookDetailBean {
	
	//社員属性(プロパティ)
	private String book_id;
	private String belong_high_school_id;
	private String belong_high_school_name;	
	private String call_number;
	private String nb_no;
	private String isbn;
	private String issn;
	private String title;
	private String ver;
	private String series;
	private String publisher;
	private String size;
	private String book_no;
	private String book_uri;
	private String old_book_id;
	private int reserving_order;
	private int delete_flg;

	public String getBelong_high_school_name() {
		return belong_high_school_name;
	}
	public void setBelong_high_school_name(String belong_high_school_name) {
		this.belong_high_school_name = belong_high_school_name;
	}
	public String getCall_number() {
		return call_number;
	}
	public void setCall_number(String call_number) {
		this.call_number = call_number;
	}
	public int getDelete_flg() {
		return delete_flg;
	}
	public void setDelete_flg(int delete_flg) {
		this.delete_flg = delete_flg;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBelong_high_school_id() {
		return belong_high_school_id;
	}
	public void setBelong_high_school_id(String belong_high_school_id) {
		this.belong_high_school_id = belong_high_school_id;
	}
	public String getNb_no() {
		return nb_no;
	}
	public void setNb_no(String nb_no) {
		this.nb_no = nb_no;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getBook_no() {
		return book_no;
	}
	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}
	public String getBook_uri() {
		return book_uri;
	}
	public void setBook_uri(String book_uri) {
		this.book_uri = book_uri;
	}
	public String getOld_book_id() {
		return old_book_id;
	}
	public void setOld_book_id(String old_book_id) {
		this.old_book_id = old_book_id;
	}
	public int getReserving_order() {
		return reserving_order;
	}
	public void setReserving_order(int reserving_order) {
		this.reserving_order = reserving_order;
	}
}
