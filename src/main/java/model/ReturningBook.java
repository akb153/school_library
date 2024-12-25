package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import beans.BookDetailBean;

public class ReturningBook
 {
	//返却資料の詳細情報取得
	public static ArrayList<BookDetailBean> getReturningBookList(ArrayList<String> bookList) throws SQLException, NamingException {

		//ArrayListの初期化
		ArrayList<BookDetailBean> rentalingBookList = new ArrayList<BookDetailBean>();
		//書籍情報を取得
		
		for(int i = 0; i < bookList.size(); i++) {
		String sql ="SELECT book.book_id,book.nb_no,book.isbn,book.issn,book.title,book.ver,book.series,book.publisher,book.size,book.book_no,book.book_uri,book.old_book_id , high_school.high_school_name FROM  book INNER JOIN high_school ON book.belong_high_school_id = high_school.high_school_id WHERE book.book_id = ? ;";
		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, bookList.get(i));
			//SQL文を表示
			System.out.println(pstmt.toString());
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//取得した行数を繰り返す
			while (rs.next()) {
				//社員Beanの初期化
				BookDetailBean rentalingBookBean = new BookDetailBean();
				//値を取得
				rentalingBookBean.setBook_id(rs.getString("book_id"));
				rentalingBookBean.setBelong_high_school_id(rs.getString("high_school.high_school_name"));
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
				//リストに社員Beanの追加
				rentalingBookList.add(rentalingBookBean);
			}		}}
			return  rentalingBookList;
	}

	public static boolean checkRentaling(ArrayList<String> book_list) throws SQLException, NamingException {
		boolean rentaling_check_result = true;
		for(int i = 0; i < book_list.size(); i++) {
		String sql = "select count(*) from borrowing where borrowing_book = ? ;";
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
					rentaling_check_result = false;
				};
				
			}
		}
	}		return rentaling_check_result;
	}
}
