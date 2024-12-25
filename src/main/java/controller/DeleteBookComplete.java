package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BookDetailBean;
import model.BookOperation;

/**
 * Servlet implementation class SyainIndex
 */
@WebServlet("/DeleteBookComplete")
public class DeleteBookComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBookComplete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String book_id = (String)session.getAttribute("book_id");
		String user_id = (String) session.getAttribute("user_id");
		BookDetailBean bookDetailBean = (BookDetailBean) session.getAttribute("bookDetailBean");
		int reserving_order = bookDetailBean.getReserving_order();
		

		//SESSIONのSESSION_IDとCOOKIEのSESSION_IDを比較する

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

		//DBのreservingテーブルに予約データを追加する
		try {
			BookOperation.deleteBook(bookDetailBean);
		} catch (SQLException | NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		// book_reserve_complete.jspへ転送
		request.getRequestDispatcher("/WEB-INF/view/delete_book_complete.jsp").forward(request, response);

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
