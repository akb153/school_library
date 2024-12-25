package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BorrowingBookBean;
import beans.ReservingBookBean;
import model.BorrowingBook;
import model.ReservingBook;


@WebServlet("/Library_user_home")
public class LibraryUserHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibraryUserHome() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		
		BorrowingBookBean borrowing_book =  (BorrowingBookBean) session.getAttribute("borrowingBookBean");
		request.setAttribute("borrowing_book", borrowing_book);
		String user_id = (String) session.getAttribute("user_id");
		ArrayList<BorrowingBookBean> borrowingBookList;
		ArrayList<ReservingBookBean> reservingBookList;
		
		try {
			borrowingBookList = BorrowingBook.getBorrowingBookList(user_id);
			session.setAttribute("borrowingBookList", borrowingBookList);
			reservingBookList = ReservingBook.getReservingBookList(user_id);
			session.setAttribute("reservingBookList", reservingBookList);
			request.getRequestDispatcher("/WEB-INF/view/library_user_home.jsp").forward(request, response);

		} catch (SQLException e) {
			 //TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (NamingException e) {
			 //TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("TEST_POST");

	}

}
