package MVC.Common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MVC.Services.IAccountServices;
import MVC.Services.Impl.AccountServicesImpl;

@WebServlet(urlPatterns = { "/common/login" })
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IAccountServices accountServices = new AccountServicesImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html;charset=UTF-8");		
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			boolean loginSuccessful = accountServices.authenticateAccount(username, password);
			if(loginSuccessful) {
				HttpSession session = req.getSession();
				session.setAttribute("username", username);
				String userRole = (String)accountServices.findRoleAccount(username, password);
				session.setAttribute("userRole", userRole);
				if(userRole.equalsIgnoreCase("Admin")) {
					int adminId = accountServices.findAdminId(username);
					session.setAttribute("adminId", adminId);
				}
				resp.sendRedirect(req.getContextPath()+"/home");
			}
			else {
				resp.sendRedirect(req.getContextPath()+"/views/login.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
