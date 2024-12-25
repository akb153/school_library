package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BookDetailBean;
import model.PwVerification;
import model.RentalingBook;

/**
 * Servlet implementation class SyainIndex
 */
@WebServlet("/RentalConfirm")
public class RentalConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RentalConfirm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//SESSIONのSESSION_IDとCOOKIEのSESSION_IDを比較する
		HttpSession session = request.getSession();
		String settion_id = (String) session.getAttribute("session_id");
		
		//COOKIEのSESSION_IDを取り出す
        Cookie[] cookies = request.getCookies();
		String result = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ((cookie.getName()).equals("JSESSIONID")) {
					result = cookie.getValue();
					break;
				}
			}
		}

		if(settion_id == null) {
			request.getRequestDispatcher("/WEB-INF/view/login.jsp?error=session").forward(request, response);
		}else if (!(settion_id).equals(result)) {
			request.getRequestDispatcher("/WEB-INF/view/login.jsp?error=session").forward(request, response);
		}
		
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String book_id1 = request.getParameter("book_id1");
		String book_id2 = request.getParameter("book_id2");
		String book_id3 = request.getParameter("book_id3");
		String book_id4 = request.getParameter("book_id4");
		String book_id5 = request.getParameter("book_id5");
		String book_id6 = request.getParameter("book_id6");
		String book_id7 = request.getParameter("book_id7");
		String book_id8 = request.getParameter("book_id8");
		String book_id9 = request.getParameter("book_id9");
		String book_id10 = request.getParameter("book_id10");
		ArrayList<String> book_list = new ArrayList<String>() ;
		if( book_id1 != null && book_id1 != "") {book_list.add(book_id1);}
		if( book_id2 != null && book_id2 != "") {book_list.add(book_id2);}
		if( book_id3 != null && book_id3 != "") {book_list.add(book_id3);}
		if( book_id4 != null && book_id4 != "") {book_list.add(book_id4);}
		if( book_id5 != null && book_id5 != "") {book_list.add(book_id5);}
		if( book_id6 != null && book_id6 != "") {book_list.add(book_id6);}
		if( book_id7 != null && book_id7 != "") {book_list.add(book_id7);}
		if( book_id8 != null && book_id8 != "") {book_list.add(book_id8);}
		if( book_id9 != null && book_id9 != "") {book_list.add(book_id9);}
		if( book_id10 != null && book_id10 != "") {book_list.add(book_id10);}
		

		//入力されたBOOK IDの書籍が存在するかどうかのチェック
		try {
			boolean exist_check_result = RentalingBook.checkExist(book_list);
			if(exist_check_result == false) {
				request.getRequestDispatcher("/WEB-INF/view/rental.jsp?error=no_exist").forward(request, response);
			}
		} catch (SQLException | NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
		//貸出書籍の重複チェック
		for(int i = 0; i < book_list.size(); i++) {
			for (int y = book_list.size()-1 ; i <  y ; y--) {
				if(book_list.get(i).toString().equals(book_list.get(y).toString())){
					request.getRequestDispatcher("/WEB-INF/view/rental.jsp?error=duplicate").forward(request, response);
			}
			}
		}
		
		//ユーザーID、PWのチェック
		try {
			boolean pw_check_result = PwVerification.checkPw(user_id, password);
			if(pw_check_result == false) {
				request.getRequestDispatcher("/WEB-INF/view/rental.jsp?error=mistake").forward(request, response);
			}
		} catch (SQLException | NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		//延滞チェック
		try {
			boolean overdue_check_result = RentalingBook.checkOverdue(user_id);
			if(overdue_check_result == true) {
				request.getRequestDispatcher("/WEB-INF/view/rental.jsp?error=overdue").forward(request, response);
			}
		} catch (SQLException | NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
		//書籍の予約状況チェック
		try {
			ArrayList<BookDetailBean> reservation_check_result = RentalingBook.checkReservationStatus(book_list, user_id);
			if(reservation_check_result.size() > 0) {
				session.setAttribute("reservation_check_result", reservation_check_result);
				request.getRequestDispatcher("/WEB-INF/view/rental.jsp?error=reserved").forward(request, response);
			}
		} catch (SQLException | NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
		//書籍の貸出状況チェック（念のため）
		try {
			ArrayList<BookDetailBean> borrowing_check_result = RentalingBook.checkBorrowingStatus(book_list);
			if(borrowing_check_result.size() > 0) {
				session.setAttribute("borrowing_check_result", borrowing_check_result);
				request.getRequestDispatcher("/WEB-INF/view/rental.jsp?error=borrowed").forward(request, response);
			}
		} catch (SQLException | NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
		//貸出上限数チェック

		try {
			int borrowing_num = RentalingBook.checkLimit(user_id);
				if(borrowing_num + book_list.size() > 10 ) {
				session.setAttribute("borrowing_num", borrowing_num);
				request.getRequestDispatcher("/WEB-INF/view/rental.jsp?error=limit_over").forward(request, response);
				}
		} catch (SQLException | NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
		//セッションをまたいで受け渡ししたい値の管理
		session.setAttribute("rentaling_size", book_list.size());
		session.setAttribute("borrowing_book_list", book_list);
		session.setAttribute("borrowing_user", user_id);		
		
		//貸出書籍情報の管理
		 ArrayList<BookDetailBean> rentalingBookList = new ArrayList<BookDetailBean>();
		try {
			rentalingBookList = RentalingBook.getRentalingBookList(user_id,password,book_id1,book_id2,book_id3,book_id4,book_id5,book_id6,book_id7,book_id8,book_id9,book_id10);
			session.setAttribute("rentalingBookList", rentalingBookList);	
		} catch (SQLException | NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		// book_reserve_complete.jspへ転送
		request.getRequestDispatcher("/WEB-INF/view/rental_confirm.jsp").forward(request, response);

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
