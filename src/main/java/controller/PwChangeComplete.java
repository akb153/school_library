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

import beans.LoginBean;
import model.PwVerification;

/**
 * Servlet implementation class SyainIndex
 */
@WebServlet("/PwChangeComplete")
public class PwChangeComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PwChangeComplete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 HttpSession session = request.getSession(true);
		String password = (String) (request.getParameter("password"));
		String new_password = (String) (request.getParameter("newpassword"));
		String new_password2 = (String) (request.getParameter("newpassword2"));		
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");


				//SESSIONのSESSION_IDとCOOKIEのSESSION_IDを比較する
				//SESSIONのSESSION_IDを取り出す
		
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
		
		
				//現在のPWチェック
				if(!(loginBean.getPassword().equals(password))) {
					//request.getRequestDispatcher("PwChange?error=mistake");
					request.getRequestDispatcher("/WEB-INF/view/pw_change.jsp?error=mistake").forward(request, response);
				}else {
				
				//新パスワードと新パスワード（確認用）が一致するかチェック
				if(!(new_password.equals(new_password2))) {
					request.getRequestDispatcher("/WEB-INF/view/pw_change.jsp?error=not_match").forward(request, response);
				}else {
				
				//パスワードをDB上で更新
					try {
						PwVerification.changePw(loginBean.getUser_id(),new_password);
					} catch (SQLException | NamingException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
		
				// book_reserve.jspへ転送
				request.getRequestDispatcher("/WEB-INF/view/pw_change_complete.jsp").forward(request, response);
				}}
	
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
