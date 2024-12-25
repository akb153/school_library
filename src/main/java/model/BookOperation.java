package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

import beans.BookDetailBean;

public class BookOperation
 {
	public static void addNewBook(BookDetailBean addBook) throws SQLException, NamingException {
		//新入生のユーザー情報を登録するSQL
		String sql = "INSERT INTO book (book_id,belong_high_school_id,call_number,nb_no,isbn,issn,title,ver,series,publisher,size,book_no,book_uri,old_book_id,delete_flg) SELECT CONCAT('GIGALIB_', LPAD( CAST( SUBSTRING(MAX(book_id), 9) AS UNSIGNED ) + 1, 10,'0' )) ,?,?,?,?,?,?,?,?,?,?,?,?,NULL,0 FROM book;";
		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			//パラメータをSQLにセット
			pstmt.setString(1, addBook.getBelong_high_school_id());
			pstmt.setString(2, addBook.getCall_number());
			pstmt.setString(3, addBook.getNb_no());
			pstmt.setString(4, addBook.getIsbn());
			pstmt.setString(5, addBook.getIssn());
			pstmt.setString(6, addBook.getTitle());
			pstmt.setString(7, addBook.getVer());
			pstmt.setString(8, addBook.getSeries());
			pstmt.setString(9, addBook.getPublisher());
			pstmt.setString(10, addBook.getSize());
			pstmt.setString(11, addBook.getBook_no());
			pstmt.setString(12, addBook.getBook_uri());
			//SQL文を表示
			System.out.println(pstmt.toString());
			//SQL実行
			pstmt.executeUpdate();

		}
	}

	public static void deleteBook(BookDetailBean deleteBook) throws SQLException, NamingException {
		//資料を廃棄するSQL
		String sql = "UPDATE book SET delete_flg =1 WHERE book_id=?;";
		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, deleteBook.getBook_id());
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			pstmt.executeUpdate();
		}
	}
}
