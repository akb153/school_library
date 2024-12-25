package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LoginBean;

/**
 * Servlet implementation class SyainIndex
 */
@WebServlet("/Admin_home")
public class AdminHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminHome() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String user_name = ((LoginBean) session.getAttribute("loginBean")).getUser_name();
		String belong_high_school_id = ((LoginBean) session.getAttribute("loginBean")).getBelong_high_school_id();
		String mail_address = ((LoginBean) session.getAttribute("loginBean")).getMail_address();
		int admin_flg = ((LoginBean) session.getAttribute("loginBean")).getAdmin_flg();
		int librarian_flg = ((LoginBean) session.getAttribute("loginBean")).getLibrarian_flg();
		int library_user_flg = ((LoginBean) session.getAttribute("loginBean")).getLibrary_user_flg();
		int library_committee_flg = ((LoginBean) session.getAttribute("loginBean")).getLibrary_committee_flg();
		int transporter_flg = ((LoginBean) session.getAttribute("loginBean")).getTransporter_flg();
		int rental_flg = ((LoginBean) session.getAttribute("loginBean")).getRental_flg();
		int return_flg = ((LoginBean) session.getAttribute("loginBean")).getReturn_flg();

		//System.out.println("session:" +user_name);
		request.setAttribute("user_name", user_name);
		request.setAttribute("belong_high_school_id", belong_high_school_id);
		request.setAttribute("mail_address", mail_address);
		request.setAttribute("admin_flg", admin_flg);
		request.setAttribute("librarian_flg", librarian_flg);
		request.setAttribute("library_user_flg", library_user_flg);
		request.setAttribute("library_committee_flg", library_committee_flg);
		request.setAttribute("transporter_flg", transporter_flg);
		request.setAttribute("rental_flg", rental_flg);
		request.setAttribute("return_flg", return_flg);
		request.getRequestDispatcher("/WEB-INF/view/admin_home.jsp").forward(request, response);
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
