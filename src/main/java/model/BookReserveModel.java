package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.naming.NamingException;

import beans.BookDetailBean;

public class BookReserveModel
 {
	//パスワードを取得
	public static BookDetailBean  getBookDetail(String book_id) throws SQLException, NamingException {


		//ログイン中ユーザーの貸出中書籍一覧を選択するSQL
		String sql = "SELECT book.book_id,book.belong_high_school_id,book.call_number,book.nb_no,book.isbn," +
					"book.issn,book.title,book.ver,book.series,book.publisher,book.size,book.book_no,book.book_uri,book.old_book_id , reserving_order FROM(    select reserving.reserving_book, COUNT(reserving.reserving_id) AS reserving_order FROM reserving GROUP BY reserving.reserving_book ) AS numbered_reservations RIGHT OUTER JOIN book ON book.book_id = numbered_reservations.reserving_book WHERE book.book_id =? and delete_flg != 1;";
		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, book_id);
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			ResultSet rs = pstmt.executeQuery();

			//ArrayListの初期化
			BookDetailBean bookDetailBean = new BookDetailBean();
			while (rs.next()) {
			//値を取得
			bookDetailBean.setBook_id(rs.getString("book.book_id"));
			bookDetailBean.setBelong_high_school_id(rs.getString("book.belong_high_school_id"));
			bookDetailBean.setBelong_high_school_name(HighSchoolOperarion.getHighSchoolName(rs.getString("belong_high_school_id")));
			bookDetailBean.setCall_number(rs.getString("book.call_number"));
			bookDetailBean.setNb_no(rs.getString("book.nb_no"));
			bookDetailBean.setIsbn(rs.getString("book.isbn"));
			bookDetailBean.setIssn(rs.getString("book.issn"));
			bookDetailBean.setTitle(rs.getString("book.title"));
			bookDetailBean.setVer(rs.getString("book.ver"));
			bookDetailBean.setSeries(rs.getString("book.series"));
			bookDetailBean.setPublisher(rs.getString("book.publisher"));
			bookDetailBean.setSize(rs.getString("book.size"));
			bookDetailBean.setBook_no(rs.getString("book.book_no"));
			bookDetailBean.setBook_uri(rs.getString("book.book_uri"));
			bookDetailBean.setOld_book_id(rs.getString("book.old_book_id"));
			bookDetailBean.setReserving_order(rs.getInt("reserving_order"));
			}
			return  bookDetailBean;
		}
	}

	public static void  addReserving( String user_id, String book_id, int reserving_order) {
	//ログイン中ユーザーの貸出中書籍一覧を選択するSQL

		boolean chk_flg = false;
		String reserving_id = "";
		
		while(chk_flg==false) {
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			int length = 10;
			Random random = new Random();
			StringBuilder randomString = new StringBuilder(length);
			for (int i = 0; i < length; i++) {
				int index = random.nextInt(characters.length());
				randomString.append(characters.charAt(index));
			}
			reserving_id = randomString.toString();
			String random_check = " select * from reserving where reserving_id = ?;";
        
			//SQL実行の準備
			try (Connection con = ConnectionBase.getConnection();
					PreparedStatement pstmt = con.prepareStatement(random_check);) {
				//パラメータをSQLにセット
				pstmt.setString(1, reserving_id);
				//SQL実行
				ResultSet rs = pstmt.executeQuery();
				if(rs.getRow() ==0 ) {
					chk_flg = true;
				}
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} 
		}
		
		//SQL実行の準備
		String sql = " insert into reserving (reserving_id, reserving_user, reserving_book, reserving_date, deadline_for_receiving) VALUES (? , ?, ? ,CURRENT_TIMESTAMP ,? );";
		try (Connection con = ConnectionBase.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
		//パラメータをSQLにセット
		pstmt.setString(1, reserving_id);
		pstmt.setString(2, user_id);
		pstmt.setString(3, book_id);
		if(reserving_order == 0) {
			LocalDateTime deadline =  LocalDateTime.now().plusDays(7);
			DateTimeFormatter dtf1 =
					DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
				String deadline_for_receiving = dtf1.format(deadline); 
			pstmt.setString(4, deadline_for_receiving);
		}else {
			pstmt.setString(4, null);
		}
		//SQL文を表示
		System.out.println(pstmt.toString());
		//SQL実行
		pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} 
		
	}

	public static boolean checkDuplication(String user_id, String book_id) throws SQLException, NamingException {
		boolean duplication_check_result = true;

		String sql = "select count(*) from reserving where reserving_user = ? and reserving_book = ? ;";
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			//パラメータをSQLにセット
			pstmt.setString(1, user_id);
			pstmt.setString(2, book_id);
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//COUNT数を取得
			while (rs.next()) {
				//値を取得
				if(rs.getInt("count(*)") > 0) {
					duplication_check_result = false;
				};
		}
	}		return duplication_check_result;
	}
}
