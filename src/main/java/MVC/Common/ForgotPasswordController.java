package MVC.Common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MVC.Services.IAccountServices;
import MVC.Services.Impl.AccountServicesImpl;
@WebServlet(urlPatterns= {"/common/forgotPassword"})
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IAccountServices accountService = new AccountServicesImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean check;
		try {
			resp.setContentType("text/html;charset=UTF-8");
			resp.setContentType("charset=UTF-8");
			String thongBao = "";
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String confirmedPassword = req.getParameter("confirmedPassword");
			if (accountService.checkValidEmail(email) && password.equals(confirmedPassword)) {
				accountService.resetPassword(username, email, confirmedPassword);
				check=true;
				resp.sendRedirect(req.getContextPath() + "/common/login");
				//req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
			} else {
				check=false;
				thongBao ="Reset thất bại, hãy kiểm tra lại tài khoản hoặc email";
				req.setAttribute("thongBao", thongBao);
				req.setAttribute("check", check);
				//resp.sendRedirect(req.getContextPath() + "/common/register");
				req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
