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
import model.RentalingBook;
import model.ReturningBook;

/**
 * Servlet implementation class SyainIndex
 */
@WebServlet("/ReturnConfirm")
public class ReturnConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnConfirm() {
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
				request.getRequestDispatcher("/WEB-INF/view/return.jsp?error=no_exist").forward(request, response);
			}
		} catch (SQLException | NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
		//入力されたBOOK IDの書籍が貸出中かどうかチェック
		try {
			boolean rental_check_result = 	ReturningBook.checkRentaling(book_list);
			if(rental_check_result == false) {
				request.getRequestDispatcher("/WEB-INF/view/return.jsp?error=no_rental").forward(request, response);
			}
		} catch (SQLException | NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//諸々Settionに入れておく
		session.setAttribute("returning_book_list",  book_list);
		session.setAttribute("returning_size",  book_list.size());	
		//貸出書籍情報の管理
		try {
			 ArrayList<BookDetailBean> returningBookList = new ArrayList<BookDetailBean>();
			returningBookList = ReturningBook.getReturningBookList(book_list);
			session.setAttribute("returningBookList", returningBookList);	
		} catch (SQLException | NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		// book_reserve_complete.jspへ転送
		request.getRequestDispatcher("/WEB-INF/view/return_confirm.jsp").forward(request, response);

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
