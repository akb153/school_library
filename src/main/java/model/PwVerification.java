package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import beans.LoginBean;

public class PwVerification
 {
	//パスワードを取得
	public static LoginBean getLoginBean(String user_id) throws SQLException, NamingException {

		//社員情報を初期か
		LoginBean loginBean = new LoginBean();
		//パスワードを選択するSQL
		String sql = "select user.user_id,user.user_name,user.password,high_school.high_school_name,user.mail_address,user.lock_flg,user.admin_flg,user.librarian_flg,user.library_user_flg,user.library_committee_flg,user.transporter_flg,user.rental_flg,user.return_flg,user.delete_flg from user inner join high_school on user.belong_high_school_id = high_school.high_school_id where user.user_id=? and user.delete_flg= 0";
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
				//値を取得
				loginBean.setUser_id(rs.getString("user.user_id"));
				loginBean.setUser_name(rs.getString("user.user_name"));
				loginBean.setPassword(rs.getString("user.password"));
				loginBean.setBelong_high_school_id(rs.getString("high_school.high_school_name"));
				loginBean.setMail_address(rs.getString("user.mail_address"));
				loginBean.setLock_flg(rs.getInt("user.lock_flg"));
				loginBean.setAdmin_flg(rs.getInt("user.admin_flg"));
				loginBean.setLibrarian_flg(rs.getInt("user.librarian_flg"));
				loginBean.setLibrary_user_flg(rs.getInt("user.library_user_flg"));
				loginBean.setLibrary_committee_flg(rs.getInt("user.library_committee_flg"));
				loginBean.setTransporter_flg(rs.getInt("user.transporter_flg"));
				loginBean.setRental_flg(rs.getInt("user.rental_flg"));
				loginBean.setReturn_flg(rs.getInt("user.return_flg"));
				loginBean.setDelete_flg(rs.getInt("user.delete_flg"));
			}
			return  loginBean;
		}
	}

	public static boolean checkPw(String user_id, String password) throws SQLException, NamingException {
		{
			boolean check_result= false;
			//社員を更新するSQL
			String sql = "select count(user_id) from user  where user_id=? and password =? and delete_flg = 0";
			try (Connection con = ConnectionBase.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);) {
				//パラメータをSQLにセット
				pstmt.setString(1, user_id);
				pstmt.setString(2, password);
				//SQL文を表示
				//System.out.println(pstmt.toString());
				//SQL実行
				ResultSet rs = pstmt.executeQuery();
				//COUNT数を取得
				int count = 0;
				while (rs.next()) {
					//値を取得
					count = rs.getInt("count(user_id)");
				}
				if(count >= 1) {
					check_result = true;
				}
				return  check_result;
			}
		}
	}
	
	public void updateLock_flg(String user_id) throws SQLException, NamingException {
		{
			//社員を更新するSQL
			String sql = "update user set lock_flg = lock_flg + 1 where user_id=?";
			try (Connection con = ConnectionBase.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);) {
				//パラメータをSQLにセット
				pstmt.setString(1, user_id);
				//SQL文を表示
				//System.out.println(pstmt.toString());
				//SQL実行
				pstmt.executeUpdate();
			}
		}
	}

	public static void changePw(String user_id, String new_password) throws SQLException, NamingException {
		{
			//社員を更新するSQL
			String sql = "update user set password = ? where user_id=?";
			try (Connection con = ConnectionBase.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);) {
				//パラメータをSQLにセット
				pstmt.setString(1, new_password);
				pstmt.setString(2, user_id);
				//SQL文を表示
				System.out.println(pstmt.toString());
				//SQL実行
				pstmt.executeUpdate();
			}
		}
	}

}
