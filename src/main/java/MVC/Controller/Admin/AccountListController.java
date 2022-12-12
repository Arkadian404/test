package MVC.Controller.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import MVC.Models.AccountModel;
import MVC.Models.ProductModel;
import MVC.Services.IAccountServices;
import MVC.Services.Impl.AccountServicesImpl;

@WebServlet(urlPatterns = { "/admin/account/list" })
public class AccountListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAccountServices accountService = new AccountServicesImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			doGet_All(req, resp);
		} else {
			if (action.equalsIgnoreCase("find")) {
				doGet_Find(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action.equalsIgnoreCase("create")) {
			doPost_Create(req, resp);
		} else if (action.equalsIgnoreCase("delete")) {
			doPost_Delete(req, resp);
		} else if (action.equalsIgnoreCase("update")) {
			doPost_Update(req, resp);
		}else if(action.equalsIgnoreCase("search")) {
			doPost_Search(req, resp);
		}
	}

	protected void doGet_Find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		AccountModel account;
		try {
			int accountId = Integer.parseInt(req.getParameter("id"));
			account = accountService.findById(accountId);
			Gson gson = new Gson();
			PrintWriter writer = resp.getWriter();
			writer.print(gson.toJson(account));
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet_All(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String indexPage = req.getParameter("index");
			if(indexPage == null) {
				indexPage="1";
			}
			int index = Integer.parseInt(indexPage);
			int count = accountService.countAll();
			int endPage = count/3;
			if(count%3!=0) {
				endPage++;
			}
			
			List<AccountModel> taiKhoanList = accountService.pagingAccount(index);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.setAttribute("taiKhoanList", taiKhoanList);
			req.getRequestDispatcher("/views/admin/account/list-account.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost_Create(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		AccountModel account = new AccountModel();
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			// account.setAccountId(Integer.parseInt(req.getParameter("accountId")));
			account.setUserName(req.getParameter("userName"));
			account.setPassWord(req.getParameter("passWord"));
			account.setEmail(req.getParameter("email"));
			String ngayTao = req.getParameter("createdDate");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date(formatter.parse(ngayTao).getTime());
			account.setCreatedDate(date);
			account.setRoleId(Integer.parseInt(req.getParameter("roleId")));
			account.setStatus(Integer.parseInt(req.getParameter("tinhTrang")));
			accountService.insert(account);
			resp.sendRedirect(req.getContextPath() + "/admin/account/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost_Update(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			int accountId = Integer.parseInt(req.getParameter("id"));
			AccountModel account = new AccountModel();
			account = accountService.findById(accountId);
			// account.setAccountId(Integer.parseInt(req.getParameter("accountId")));
			account.setUserName(req.getParameter("userName"));
			account.setPassWord(req.getParameter("passWord"));
			account.setEmail(req.getParameter("email"));
			String ngayTao = req.getParameter("createdDate");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date(formatter.parse(ngayTao).getTime());
			account.setCreatedDate(date);
			account.setRoleId(Integer.parseInt(req.getParameter("roleId")));
			account.setStatus(Integer.parseInt(req.getParameter("status")));
			accountService.edit(account);
			resp.sendRedirect(req.getContextPath() + "/admin/account/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost_Delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			int accountId = Integer.parseInt(req.getParameter("id"));
			System.out.print(accountId);
			accountService.delete(accountId);
			resp.sendRedirect(req.getContextPath() + "/admin/account/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost_Search(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}


	public static void main(String args[]) {
	}
}
