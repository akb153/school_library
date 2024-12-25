package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.naming.NamingException;

import beans.BorrowingBookBean;

public class BorrowingBook
 {
	//パスワードを取得
	public static ArrayList<BorrowingBookBean> getBorrowingBookList(String user_id) throws SQLException, NamingException {

		//ArrayListの初期化
		ArrayList<BorrowingBookBean> borrowingBookList = new ArrayList<BorrowingBookBean>();
		//ログイン中ユーザーの貸出中書籍一覧を選択するSQL
		String sql = "SELECT borrowing.borrowing_id,user.user_id, user.user_name, user.mail_address, user_belonging_school.high_school_name AS user_belonging_school, borrowing.borrowing_book, book.title,borrowing.borrowing_start_date, borrowing.borrowing_end_date,book_belonging_school.high_school_name AS book_belonging_school from borrowing INNER JOIN book ON borrowing.borrowing_book = book.book_id INNER JOIN high_school AS book_belonging_school ON book.belong_high_school_id = book_belonging_school.high_school_id  INNER JOIN user ON borrowing.borrowing_user = user.user_id INNER JOIN high_school AS user_belonging_school ON user.belong_high_school_id = user_belonging_school.high_school_id WHERE borrowing.borrowing_user = ? ORDER BY borrowing.borrowing_end_date";
		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, user_id);
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//取得した行数を繰り返す
			while (rs.next()) {
				//社員Beanの初期化
				BorrowingBookBean borrowingBookBean = new BorrowingBookBean();
				//値を取得
				borrowingBookBean.setBorrowing_id(rs.getString("borrowing.Borrowing_id"));
				borrowingBookBean.setUser_id(rs.getString("user.user_id"));
				borrowingBookBean.setUser_name(rs.getString("user.user_name"));
				borrowingBookBean.setMail_address(rs.getString("user.mail_address"));
				borrowingBookBean.setUser_belonging_school(rs.getString("user_belonging_school"));
				borrowingBookBean.setBorrowing_book(rs.getString("borrowing.borrowing_book"));
				borrowingBookBean.setTitle(rs.getString("book.title"));
				borrowingBookBean.setBorrowing_start_date(rs.getDate("borrowing.borrowing_start_date"));
				borrowingBookBean.setBorrowing_end_date(rs.getDate("borrowing.borrowing_end_date"));
				borrowingBookBean.setBook_belonging_school(rs.getString("book_belonging_school"));
				//リストに社員Beanの追加
				borrowingBookList.add(borrowingBookBean);
			}
			return  borrowingBookList;
		}
	}

	public static void addBorrowing(ArrayList<String> book_list,String user_id) throws SQLException, NamingException {
		for(int i = 0; i < book_list.size(); i++) {
			
			boolean chk_flg = false;
			String borrowing_id = "";
			
			while(chk_flg==false) {
				String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
				int length = 10;
				Random random = new Random();
				StringBuilder randomString = new StringBuilder(length);
				for (int y = 0; y < length; y++) {
					int index = random.nextInt(characters.length());
					randomString.append(characters.charAt(index));
				}
				borrowing_id = randomString.toString();
				String random_check = " select * from borrowing where borrowing_id = ?;";
	        
				//SQL実行の準備
				try (Connection con = ConnectionBase.getConnection();
						PreparedStatement pstmt = con.prepareStatement(random_check);) {
					//パラメータをSQLにセット
					pstmt.setString(1, borrowing_id);
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
			
			
		String sql = " insert into borrowing (borrowing_id, borrowing_user, borrowing_book, borrowing_start_date, borrowing_end_date) VALUES (? , ?, ? ,CURRENT_DATE ,DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY));";
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			String book_id = book_list.get(i).toString();
			//パラメータをSQLにセット
			pstmt.setString(1, borrowing_id);
			pstmt.setString(2, user_id);
			pstmt.setString(3, book_id);
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			pstmt.executeUpdate();

		}

		}}

	public static void deleteReserving(ArrayList<String> book_list, String user_id) throws SQLException, NamingException {
		// TODO 自動生成されたメソッド・スタブ
		for(int i = 0; i < book_list.size(); i++) {
			String sql = "DELETE FROM reserving WHERE reserving_book = ? AND reserving_user = ?;";
			try (Connection con = ConnectionBase.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);) {
				String book_id = book_list.get(i).toString();
				//パラメータをSQLにセット
				pstmt.setString(1, book_id);
				pstmt.setString(2, user_id);
				//SQL文を表示
				//System.out.println(pstmt.toString());
				//SQL実行
				pstmt.executeUpdate();
		}
	}
	}

	public static void deleteBorrowing(ArrayList<String> book_list) throws SQLException, NamingException {
		// TODO 自動生成されたメソッド・スタブ
		for(int i = 0; i < book_list.size(); i++) {
			String sql = "DELETE FROM borrowing WHERE borrowing_book = ?;";
			try (Connection con = ConnectionBase.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);) {
				String book_id = book_list.get(i).toString();
				//パラメータをSQLにセット
				pstmt.setString(1, book_id);
				//SQL文を表示
				//System.out.println(pstmt.toString());
				//SQL実行
				pstmt.executeUpdate();
		}
	}
	}
}
