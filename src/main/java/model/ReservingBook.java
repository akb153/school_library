package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import beans.ReservingBookBean;

public class ReservingBook
 {
	//パスワードを取得
	public static ArrayList<ReservingBookBean> getReservingBookList(String user_id) throws SQLException, NamingException {

		//ArrayListの初期化
		ArrayList<ReservingBookBean> reservingBookList = new ArrayList<ReservingBookBean>();
		//ログイン中ユーザーの貸出中書籍一覧を選択するSQL
		String sql = "SELECT numbered_reservations.reserving_id,user.user_id, user.user_name, user.mail_address, user_belonging_school.high_school_name AS user_belonging_school, numbered_reservations.reserving_book, book.title,book.call_number,book.book_id ,reserving_date, numbered_reservations.deadline_for_receiving,book_belonging_school.high_school_name AS book_belonging_school, numbered_reservations.row_num,borrowing_status.borrowing_book AS borrowing_flg FROM ( SELECT reserving_id, reserving_date, deadline_for_receiving, reserving_user, reserving_book, ROW_NUMBER() OVER(PARTITION BY reserving_book ORDER BY reserving_date, reserving_id) AS row_num FROM reserving) AS numbered_reservations INNER JOIN book ON numbered_reservations .reserving_book = book.book_id INNER JOIN high_school AS book_belonging_school ON book.belong_high_school_id = book_belonging_school.high_school_id INNER JOIN USER ON numbered_reservations .reserving_user = user.user_id INNER JOIN high_school AS user_belonging_school ON user.belong_high_school_id = user_belonging_school.high_school_id LEFT JOIN  (SELECT borrowing_book FROM borrowing INNER JOIN reserving on borrowing.borrowing_book = reserving.reserving_book) AS borrowing_status ON borrowing_status.borrowing_book = book.book_id  WHERE numbered_reservations .reserving_user = ? ORDER BY numbered_reservations .deadline_for_receiving;";
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
				ReservingBookBean reservingBookBean = new ReservingBookBean();
				//値を取得
				reservingBookBean.setReserving_id(rs.getString("numbered_reservations.reserving_id"));
				reservingBookBean.setUser_id(rs.getString("user.user_id"));
				reservingBookBean.setUser_name(rs.getString("user.user_name"));
				reservingBookBean.setMail_address(rs.getString("user.mail_address"));
				reservingBookBean.setUser_belonging_school(rs.getString("user_belonging_school"));
				reservingBookBean.setReserving_book(rs.getString("numbered_reservations.reserving_book"));
				reservingBookBean.setTitle(rs.getString("book.title"));
				reservingBookBean.setReserving_date(rs.getDate("numbered_reservations.reserving_date"));
				reservingBookBean.setDeadline_for_receiving(rs.getDate("numbered_reservations.deadline_for_receiving"));
				reservingBookBean.setBook_belonging_school(rs.getString("book_belonging_school"));
				reservingBookBean.setRow_num(rs.getInt("numbered_reservations.row_num"));
				reservingBookBean.setCall_number(rs.getString("book.call_number"));
				if(rs.getString("borrowing_flg") != null && rs.getString("borrowing_flg") != "") {
					reservingBookBean.setBorrowing_flg(true);
				}
				//リストに社員Beanの追加
				reservingBookList.add(reservingBookBean);
			}
			return  reservingBookList;
		}
	}



}
