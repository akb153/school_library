package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import beans.HighSchoolBean;

public class HighSchoolOperarion
 {
	//パスワードを取得
	public static ArrayList<HighSchoolBean> getHighSchoolList() throws SQLException, NamingException {

		//ArrayListの初期化
		ArrayList<HighSchoolBean> highSchoolList = new ArrayList<HighSchoolBean>();
		//ログイン中ユーザーの貸出中書籍一覧を選択するSQL
		String sql = "SELECT high_school_id,high_school_name,post_code,address,phone_number,opening_time,closing_time from high_school where delete_flg !=1;";
		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//取得した行数を繰り返す
			while (rs.next()) {
				//社員Beanの初期化
				HighSchoolBean highSchoolBean = new HighSchoolBean();
				//値を取得
				highSchoolBean.setHigh_school_id(rs.getString("high_school_id"));
				highSchoolBean.setHigh_school_name(rs.getString("high_school_name"));
				highSchoolBean.setPost_code(rs.getString("post_code"));
				highSchoolBean.setAddress(rs.getString("address"));
				highSchoolBean.setPhone_number(rs.getString("phone_number"));
				highSchoolBean.setOpening_time(rs.getString("opening_time"));
				highSchoolBean.setClosing_time(rs.getString("closing_time"));
				//リストに社員Beanの追加
				highSchoolList.add(highSchoolBean);
			}
			return  highSchoolList;
		}
	}
	public static String getHighSchoolName(String high_school_id) throws SQLException, NamingException {

		//高校名を取得するSQL
		String sql = "SELECT high_school_name from high_school where high_school_id = ?;";
		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, high_school_id);
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//Nameの宣言
			String highSchoolName = null;
			//取得した行数を繰り返す
			while (rs.next()) {
				//社員Beanの初期化
				highSchoolName = rs.getString("high_school_name");
			}
			return  highSchoolName;
		}
	}
}
