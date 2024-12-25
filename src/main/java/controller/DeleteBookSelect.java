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

import beans.BookSearchBean;
import model.SearchingBook;

/**
 * Servlet implementation class SyainIndex
 */
@WebServlet("/DeleteBookSelect")
public class DeleteBookSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBookSelect() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//SESSIONのSESSION_IDとCOOKIEのSESSION_IDを比較する
		//SESSIONのSESSION_IDを取り出す
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

		String keyword = request.getParameter("keyword");
		String belong_high_school_id = request.getParameter("belong_high_school_id");
		String title = request.getParameter("title");
		String publisher = request.getParameter("publisher");
		String ISBN_ISSN = request.getParameter("ISBN_ISSN");
		
		try {
			//社員リスト取得
			ArrayList<BookSearchBean> bookSearchList = SearchingBook.getBookSearchList(keyword,belong_high_school_id,title,publisher,ISBN_ISSN);
			int list_size = bookSearchList.size();
			
			// 社員リストをセットする
			session.setAttribute("bookSearchList", bookSearchList);
			session.setAttribute("list_size", list_size);
			
			// index.jspへ転送
			request.getRequestDispatcher("/WEB-INF/view/delete_book_select.jsp").forward(request, response);
		
		} catch (SQLException | NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			// エラーページへ転送
			request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
}
