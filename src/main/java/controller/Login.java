package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LoginBean;
import model.PwVerification;

/**
 * Servlet implementation class SyainIndex
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//社員ロジックの作成

	
		PwVerification pwVerification = new PwVerification();
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		try {
					
			//ログイン情報取得
			LoginBean loginBean = PwVerification.getLoginBean(user_id);
			String user_id_check = loginBean.getUser_id();
			if(user_id_check  == null) {
				request.getRequestDispatcher("/WEB-INF/view/login.jsp?error=mistake").forward(request, response);
			}
			
			// main.jspへ転送
			if(loginBean.getLock_flg() >= 5){
				request.getRequestDispatcher("/WEB-INF/view/login.jsp?error=locked").forward(request, response);
				}
			else if(loginBean.getPassword().equals(password)) {
				
			    //Cookieの作成
				
					String value = CookieController.getCookie(request, "JSESSIONID");
					 HttpSession session = request.getSession(true);
					session.setAttribute("user_id", user_id);
					session.setAttribute("session_id", value);
					session.setAttribute("loginBean", loginBean);
					if (loginBean.getAdmin_flg()==1){
						response.sendRedirect("Admin_home");
					}else if(loginBean.getLibrarian_flg()==1) {
						response.sendRedirect("LibrarianHome");
					}else if(loginBean.getLibrary_user_flg()==1) {
						response.sendRedirect("Library_user_home");
					}else if(loginBean.getLibrary_committee_flg()==1) {
						response.sendRedirect("libraryCommitteeHome");
					}else if(loginBean.getTransporter_flg()==1) {
						response.sendRedirect("TransporterHome");
					}else if(loginBean.getRental_flg()==1) {
						response.sendRedirect("Rental");
					}else if(loginBean.getReturn_flg()==1) {
						response.sendRedirect("Return");
					}else {
						request.getRequestDispatcher("/WEB-INF/view/no_auth_error.jsp").forward(request, response);
					}
					
					
			}else {
				pwVerification.updateLock_flg(user_id);
				request.getRequestDispatcher("/WEB-INF/view/login.jsp?error=mistake").forward(request, response);
				}
		
		} catch (SQLException | NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			// エラーページへ転送
			request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);

		}
	}

}
