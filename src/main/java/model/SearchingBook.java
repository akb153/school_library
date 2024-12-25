package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import beans.BookSearchBean;
public class SearchingBook
 {
	//パスワードを取得
	public static ArrayList<BookSearchBean> getBookSearchList(String keyword ,String belong_high_school_id ,  String title,String publisher,String isbn) throws SQLException, NamingException {

		//ArrayListの初期化
		ArrayList<BookSearchBean> bookSearchList = new ArrayList<BookSearchBean>();
		//ログイン中ユーザーの貸出中書籍一覧を選択するSQL
		String sql = "SELECT * FROM book WHERE ( book_id LIKE ? OR nb_no LIKE ?  OR isbn LIKE ? OR issn LIKE ? OR title LIKE ? OR ver LIKE ? OR series LIKE ? OR publisher LIKE ? OR size LIKE ? OR book_no LIKE ? OR book_uri LIKE ? OR old_book_id LIKE ?)  AND  delete_flg != 1";
		//String sql = "SELECT book_id,belong_high_school_id,nb_no,isbn,issn,title,ver,series,publisher,size,book_no,book_uri,old_book_id ,COUNT(book_id)  AS list_size FROM book WHERE ( book_id LIKE ? OR nb_no LIKE ?  OR isbn LIKE ? OR issn LIKE ? OR title LIKE ? OR ver LIKE ? OR series LIKE ? OR publisher LIKE ? OR size LIKE ? OR book_no LIKE ? OR book_uri LIKE ? OR old_book_id LIKE ?)";
		
		if(belong_high_school_id != null && belong_high_school_id != "" ) {
			sql = sql + " AND belong_high_school_id LIKE "+ "\"%" + belong_high_school_id +"%\"";
		}
		
		if(title != null && title != "" ) {
			sql = sql + " AND title LIKE " + "\"%" + title + "%\"";
		}
		
		if(publisher != null && publisher != "" ) {
			sql = sql + " AND publisher LIKE " + "\"%" + publisher + "%\"";
		}
		
		/*		if(start_year != null && start_year != "" ) {
					sql = sql + " AND start_year LIKE " + "\"%" + start_year + "%\"";
				}
				
				if(end_year != null && end_year != "" ) {
					sql = sql + " AND title LIKE " + "\"%" + title + "%\"";
				}*/
		
		if(isbn != null && isbn != "" ) {
			sql = sql + " AND isbn LIKE " + "\"%" + isbn + "%\"";
		}
		

		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, "%"+ keyword+ "%");
			pstmt.setString(2, "%"+ keyword+ "%");
			pstmt.setString(3, "%"+ keyword+ "%");
			pstmt.setString(4, "%"+ keyword+ "%");
			pstmt.setString(5, "%"+ keyword+ "%");
			pstmt.setString(6, "%"+ keyword+ "%");
			pstmt.setString(7, "%"+ keyword+ "%");
			pstmt.setString(8, "%"+ keyword+ "%");
			pstmt.setString(9, "%"+ keyword+ "%");
			pstmt.setString(10, "%"+ keyword+ "%");
			pstmt.setString(11, "%"+ keyword+ "%");
			pstmt.setString(12, "%"+ keyword+ "%");
			//SQL文を表示
			//System.out.println(pstmt.toString());
			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//取得した行数を繰り返す
			
			while (rs.next()) {
				//社員Beanの初期化
				BookSearchBean bookSearchBean = new BookSearchBean();
				//値を取得
				bookSearchBean.setBook_id(rs.getString("book_id"));
				bookSearchBean.setBelong_high_school_id(rs.getString("belong_high_school_id"));
				bookSearchBean.setBelong_high_school_name(HighSchoolOperarion.getHighSchoolName(rs.getString("belong_high_school_id")));			
				bookSearchBean.setNb_no(rs.getString("nb_no"));
				bookSearchBean.setIsbn(rs.getString("isbn"));
				bookSearchBean.setIssn(rs.getString("issn"));
				bookSearchBean.setTitle(rs.getString("title"));
				bookSearchBean.setVer(rs.getString("ver"));
				bookSearchBean.setSeries(rs.getString("series"));
				bookSearchBean.setPublisher(rs.getString("publisher"));
				bookSearchBean.setSize(rs.getString("size"));
				bookSearchBean.setBook_no(rs.getString("book_no"));
				bookSearchBean.setBook_uri(rs.getString("book_uri"));
				bookSearchBean.setOld_book_id(rs.getString("old_book_id"));
				//リストに社員Beanの追加
				bookSearchList.add(bookSearchBean);
			}

			return  bookSearchList;
		}
	}
}
