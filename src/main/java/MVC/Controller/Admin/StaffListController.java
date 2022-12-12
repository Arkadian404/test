package MVC.Controller.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import MVC.Models.StaffModel;
import MVC.Services.IStaffServices;
import MVC.Services.Impl.StaffServicesImpl;

@WebServlet(urlPatterns = { "/admin/staff/list" })
public class StaffListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IStaffServices staffService = new StaffServicesImpl();

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
		}
	}

	protected void doGet_Find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		StaffModel staff;
		try {
			int MaNV = Integer.parseInt(req.getParameter("id"));
			staff = staffService.findById(MaNV);
			Gson gson = new Gson();
			PrintWriter writer = resp.getWriter();
			writer.print(gson.toJson(staff));
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
			int count = staffService.countAll();
			int endPage = count/3;
			if(count%3!=0) {
				endPage++;
			}
			List<StaffModel> staffList = staffService.pagingStaff(index);
			req.setAttribute("index", index);
			req.setAttribute("endPage", endPage);
			req.setAttribute("staffList", staffList);
			req.getRequestDispatcher("/views/admin/staff/list-staff.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost_Create(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		StaffModel staff = new StaffModel();
		try {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			staff.setStaffName(req.getParameter("staffName"));
			String ngaySinh = req.getParameter("DOB");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date dob = new Date(formatter.parse(ngaySinh).getTime());
			staff.setDOB(dob);
			staff.setSex(req.getParameter("sex"));
			staff.setStaffPhone(req.getParameter("staffPhone"));
			staff.setSalaryID(req.getParameter("salaryID"));
			staff.setAccountID(Integer.parseInt(req.getParameter("accountID")));
			staff.setStatus(Integer.parseInt(req.getParameter("status")));
			staffService.insert(staff);
			resp.sendRedirect(req.getContextPath() + "/admin/staff/list");
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
			int staffID = Integer.parseInt(req.getParameter("id"));
			StaffModel staff = new StaffModel();
			staff = staffService.findById(staffID);
			staff.setStaffName(req.getParameter("staffName"));
			String ngaySinh = req.getParameter("DOB");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date dob = new Date(formatter.parse(ngaySinh).getTime());
			staff.setDOB(dob);
			staff.setSex(req.getParameter("sex"));
			staff.setStaffPhone(req.getParameter("staffPhone"));
			staff.setSalaryID(req.getParameter("salaryID"));
			staff.setAccountID(Integer.parseInt(req.getParameter("accountID")));
			staff.setStatus(Integer.parseInt(req.getParameter("status")));
			staffService.edit(staff);
			resp.sendRedirect(req.getContextPath() + "/admin/staff/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost_Delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			int staffID = Integer.parseInt(req.getParameter("id"));
			staffService.delete(staffID);
			resp.sendRedirect(req.getContextPath() + "/admin/staff/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
