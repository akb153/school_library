package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import beans.BookDetailBean;

public class RentalingBook
 {
	//パスワードを取得
	public static ArrayList<BookDetailBean> getRentalingBookList(String user_id,String password ,String book_id1, String book_id2, String book_id3, String book_id4, String book_id5, String book_id6, String book_id7, String book_id8, String book_id9, String book_id10) throws SQLException, NamingException {

		//ArrayListの初期化
		ArrayList<BookDetailBean> rentalingBookList = new ArrayList<BookDetailBean>();
		//ログイン中ユーザーの貸出中書籍一覧を選択するSQL
		String sql ="SELECT book.book_id,book.belong_high_school_id,book.nb_no,book.isbn,book.issn,book.title,book.ver,book.series,book.publisher,book.size,book.book_no,book.book_uri,book.old_book_id , reserving_order FROM(    select reserving.reserving_book, COUNT(reserving.reserving_id) AS reserving_order FROM reserving GROUP BY reserving.reserving_book ) AS numbered_reservations RIGHT OUTER JOIN book ON book.book_id = numbered_reservations.reserving_book WHERE book.book_id in (?,?,?,?,?,?,?,?,?,?) ;";
		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット

			if(book_id1 != null) {
				pstmt.setString(1, book_id1);
			}
			if(book_id2 != null) {
				pstmt.setString(2, book_id2);
			}
			if(book_id3 != null) {
				pstmt.setString(3, book_id3);
			}
			if(book_id4 != null) {
				pstmt.setString(4, book_id4);
			}
			if(book_id5 != null) {
				pstmt.setString(5, book_id5);
			}
			if(book_id6 != null) {
				pstmt.setString(6, book_id6);
			}
			if(book_id7 != null) {
				pstmt.setString(7, book_id7);
			}
			if(book_id8 != null) {
				pstmt.setString(8, book_id8);
			}
			if(book_id9 != null) {
				pstmt.setString(9, book_id9);
			}
			if(book_id10 != null) {
				pstmt.setString(10, book_id10);
			}
			
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//取得した行数を繰り返す
			while (rs.next()) {
				//社員Beanの初期化
				BookDetailBean rentalingBookBean = new BookDetailBean();
				//値を取得
				rentalingBookBean.setBook_id(rs.getString("book.book_id"));
				rentalingBookBean.setBelong_high_school_id(rs.getString("book.belong_high_school_id"));
				rentalingBookBean.setNb_no(rs.getString("book.nb_no"));
				rentalingBookBean.setIsbn(rs.getString("book.isbn"));
				rentalingBookBean.setIssn(rs.getString("book.issn"));
				rentalingBookBean.setTitle(rs.getString("book.title"));
				rentalingBookBean.setVer(rs.getString("book.ver"));
				rentalingBookBean.setSeries(rs.getString("book.series"));
				rentalingBookBean.setPublisher(rs.getString("book.publisher"));
				rentalingBookBean.setSize(rs.getString("book.size"));
				rentalingBookBean.setBook_no(rs.getString("book.book_no"));
				rentalingBookBean.setBook_uri(rs.getString("book.book_uri"));
				rentalingBookBean.setOld_book_id(rs.getString("book.old_book_id"));
				rentalingBookBean.setReserving_order(rs.getInt("reserving_order"));
				//リストに社員Beanの追加
				rentalingBookList.add(rentalingBookBean);
			}
			return  rentalingBookList;
		}
	}

	public static boolean checkOverdue(String user_id) throws SQLException, NamingException {
		boolean check_result= false;

		//社員を更新するSQL
		String sql = "select count(borrowing_end_date) , CURRENT_DATE from borrowing  where borrowing_user = ? and borrowing_end_date < CURRENT_DATE;";
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, user_id);
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//COUNT数を取得
			int count = 0;
			while (rs.next()) {
				//値を取得
				count = rs.getInt("count(borrowing_end_date)");
			}
			if(count >= 1) {
				check_result = true;
			}
		return check_result;
	}
	}

	public static ArrayList<BookDetailBean> checkReservationStatus(ArrayList<String> book_list, String user_id) throws SQLException, NamingException {
		ArrayList<BookDetailBean> reservedBookList =  new ArrayList<BookDetailBean>() ;
		
		for(int i = 0; i < book_list.size(); i++) {
		String sql = "select book.title from reserving INNER JOIN book ON reserving.reserving_book = book.book_id where reserving_book = ? and reserving_user <> ?;";
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			String book_id = book_list.get(i).toString();
			//パラメータをSQLにセット
			pstmt.setString(1, book_id);
			pstmt.setString(2, user_id);
			//SQL文を表示
			System.out.println(pstmt.toString());
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//COUNT数を取得
			while (rs.next()) {
				//社員Beanの初期化
				BookDetailBean reservedBookBean = new BookDetailBean();
				//値を取得
				if(rs.getString("book.title")!="" && rs.getString("book.title") != null) {
				reservedBookBean.setTitle(rs.getString("book.title"));
				//リストにBookBeanの追加
				reservedBookList.add(reservedBookBean);
				}
			}
		}
	}		return reservedBookList;
}

	public static ArrayList<BookDetailBean> checkBorrowingStatus(ArrayList<String> book_list) throws SQLException, NamingException {
		ArrayList<BookDetailBean> borrowedBookList =  new ArrayList<BookDetailBean>() ;
		
		for(int i = 0; i < book_list.size(); i++) {
		String sql = "select book.title from borrowing INNER JOIN book ON borrowing.borrowing_book = book.book_id where borrowing_book = ? ;";
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			String book_id = book_list.get(i).toString();
			//パラメータをSQLにセット
			pstmt.setString(1, book_id);
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//COUNT数を取得
			while (rs.next()) {
				//社員Beanの初期化
				BookDetailBean borrowedBookBean = new BookDetailBean();
				//値を取得
				borrowedBookBean.setTitle(rs.getString("book.title"));
				//リストにBookBeanの追加
				borrowedBookList.add(borrowedBookBean);
			}
		}
	}		return borrowedBookList;
}
	
	
	public static int checkLimit(String user_id) throws SQLException, NamingException {
		// TODO 自動生成されたメソッド・スタブ
		int borrowing_num= 0;

		//社員を更新するSQL
		String sql = "select COUNT(*) FROM borrowing WHERE borrowing_user = ? ;";
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, user_id);
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//COUNT数を取得
			while (rs.next()) {
				//値を取得
				borrowing_num = rs.getInt("COUNT(*)");
			}
		return borrowing_num;
	}
	}

	public static boolean checkExist(ArrayList<String> book_list) throws SQLException, NamingException {
		// TODO 自動生成されたメソッド・スタブ
		boolean exist_check_result = true;
		for(int i = 0; i < book_list.size(); i++) {
		String sql = "select count(*) from book where book_id = ? ;";
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			String book_id = book_list.get(i).toString();
			//パラメータをSQLにセット
			pstmt.setString(1, book_id);
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//COUNT数を取得
			while (rs.next()) {
				//値を取得
				if(rs.getInt("count(*)")==0) {
					exist_check_result = false;
				};
				
			}
		}
	}		return exist_check_result;
	}
	

}
