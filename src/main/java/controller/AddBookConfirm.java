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
import model.HighSchoolOperarion;

/**
 * Servlet implementation class SyainIndex
 */
@WebServlet("/AddBookConfirm")
public class AddBookConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBookConfirm() {
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
		
		String belong_high_school_id  = request.getParameter("belong_high_school_id");
		String call_number = request.getParameter("call_number");
		String title = request.getParameter("title");
		String publisher = request.getParameter("publisher");
		String nb_no = request.getParameter("nb_no");
		String isbn = request.getParameter("isbn");
		String issn = request.getParameter("issn");
		String ver = request.getParameter("ver");
		String series = request.getParameter("series");
		String size = request.getParameter("size");
		String book_no = request.getParameter("book_no");
		String book_uri = request.getParameter("book_uri");
		BookDetailBean addBook = new BookDetailBean();
		addBook.setBelong_high_school_id(belong_high_school_id);
		try {
			addBook.setBelong_high_school_name(HighSchoolOperarion.getHighSchoolName(belong_high_school_id));
		} catch (SQLException | NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		addBook.setCall_number(call_number);
		addBook.setTitle(title);
		addBook.setPublisher(publisher);
		addBook.setNb_no(nb_no);
		addBook.setIsbn(isbn);
		addBook.setIssn(issn);
		addBook.setVer(ver);
		addBook.setSeries(series);
		addBook.setSize(size);
		addBook.setBook_no(book_no);
		addBook.setBook_uri(book_uri);
		
		//Settionに入れておく
		session.setAttribute("add_book",  addBook);

		// admiddion_cofirm.jspへ転送
		request.getRequestDispatcher("/WEB-INF/view/add_book_confirm.jsp").forward(request, response);

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
