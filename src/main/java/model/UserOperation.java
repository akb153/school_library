package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import beans.LoginBean;

public class UserOperation
 {
	public static void addNewStudent(LoginBean addUser) throws SQLException, NamingException {
		//新入生のユーザー情報を登録するSQL
		String sql = "insert into user (user_id,user_name,password,belong_high_school_id,mail_address,lock_flg,admin_flg,librarian_flg,library_user_flg,library_committee_flg,transporter_flg,rental_flg,return_flg,delete_flg) VALUES (?,?,?,?,?,0,0,0,1,0,0,0,0,0);";
		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, addUser.getUser_id());
			pstmt.setString(2, addUser.getUser_name());
			pstmt.setString(3, addUser.getPassword());
			pstmt.setString(4, addUser.getBelong_high_school_id());
			pstmt.setString(5, addUser.getMail_address());
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			pstmt.executeUpdate();
		}
	}

	public static ArrayList<LoginBean> getUserList() throws SQLException, NamingException {
		//ArrayListの初期化
		ArrayList<LoginBean> userList = new ArrayList<LoginBean>();
		
		// TODO 自動生成されたメソッド・スタブ
		String sql = "SELECT user.user_id, user.user_name, high_school.high_school_name FROM user inner join high_school on user.belong_high_school_id = high_school.high_school_id where user.delete_flg = 0;";
		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
		//SQL実行
		ResultSet rs = pstmt.executeQuery();
		//取得した行数を繰り返す
		
		while (rs.next()) {
			//社員Beanの初期化
			LoginBean userBean = new LoginBean();
			//値を取得
			userBean.setUser_id(rs.getString("user.user_id"));
			userBean.setUser_name(rs.getString("user.user_name"));
			userBean.setBelong_high_school_id(rs.getString("high_school.high_school_name"));
			//リストに社員Beanの追加
			userList.add(userBean);
		}}
		return userList;
	}

	public static void deleteStudent(LoginBean deleteUser) throws SQLException, NamingException {
		//卒業・退学したユーザー情報を削除するSQL
		String sql = "UPDATE user SET delete_flg =1 WHERE user_id=?;";
		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, deleteUser.getUser_id());
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			pstmt.executeUpdate();
		}
	}
}
